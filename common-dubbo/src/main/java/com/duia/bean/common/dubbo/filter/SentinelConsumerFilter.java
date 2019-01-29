package com.duia.bean.common.dubbo.filter;

import com.alibaba.csp.sentinel.Entry;
import com.alibaba.csp.sentinel.EntryType;
import com.alibaba.csp.sentinel.SphU;
import com.alibaba.csp.sentinel.Tracer;
import com.alibaba.csp.sentinel.context.Context;
import com.alibaba.csp.sentinel.context.ContextUtil;
import com.alibaba.csp.sentinel.slots.block.BlockException;
import com.alibaba.csp.sentinel.slots.block.RuleConstant;
import com.alibaba.csp.sentinel.slots.block.degrade.DegradeRule;
import com.alibaba.csp.sentinel.slots.block.degrade.DegradeRuleManager;
import com.alibaba.dubbo.rpc.*;
import com.duia.bean.common.dubbo.exception.CommonException;
import com.duia.bean.common.dubbo.sentinel.SentinelContext;
import com.google.common.base.Stopwatch;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.util.Arrays;
import java.util.concurrent.TimeUnit;

/**
 * 拦截所有调用正式环境不考虑配置，为了在测试环境预发布下排查慢业务
 *
 * @author wangdong
 * @version 1.0.0
 * @date 2018/12/29
 */
public class SentinelConsumerFilter extends DubboAbstractFilter {
    private final Logger logger = LoggerFactory.getLogger(SentinelConsumerFilter.class);

    @Override
    public Result invoke(Invoker<?> invoker, Invocation invocation) throws RpcException {
        Entry methodEntry = null;
        Stopwatch stopwatch = Stopwatch.createStarted();
        Result result;
        String resourceName = this.getResourceName(invoker, invocation);
        try {
            methodEntry = SphU.entry(resourceName, EntryType.OUT);
            resourceRule(resourceName);
            result = invoker.invoke(invocation);
            if (result.hasException()) {
                Tracer.trace(result.getException());
            }
            return result;
        } catch (BlockException var12) {
            throw new RpcException(CommonException.E_502.getCode(),resourceName);
        } catch (RpcException var13) {
            Tracer.trace(var13);
            throw var13;
        } finally {
            if (methodEntry != null) {
                methodEntry.exit();
            }
            stopwatch.stop();
            logger.info("Dubbo Method [{}] execute time {}ms", this.getResourceName(invoker, invocation), stopwatch.elapsed(TimeUnit.MILLISECONDS));
        }
    }

    private void resourceRule(String resourceName) {
        if (!DegradeRuleManager.hasConfig(resourceName)) {
            DegradeRule ruleRT = new DegradeRule();
            ruleRT.setResource(resourceName);
            // set threshold RT, 10 ms
            ruleRT.setCount(SentinelContext.getAvgAnswerTime());
            ruleRT.setGrade(RuleConstant.DEGRADE_GRADE_RT);
            ruleRT.setTimeWindow(SentinelContext.getAvgAnswerWindowTime());
            DegradeRule ruleEC = new DegradeRule();
            ruleEC.setResource(resourceName);
            ruleEC.setCount(SentinelContext.geExceptionCount());
            ruleEC.setGrade(RuleConstant.DEGRADE_GRADE_EXCEPTION_COUNT);
            ruleEC.setTimeWindow(SentinelContext.getExceptionWindowTime());
            DegradeRuleManager.loadRules(Arrays.asList(ruleEC,ruleRT));
        } else {
            logger.info("has be rule resource {}",resourceName);
        }
    }
}
