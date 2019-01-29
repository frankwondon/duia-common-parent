package com.duia.bean.common.dubbo.filter;

import com.alibaba.dubbo.rpc.Filter;
import com.alibaba.dubbo.rpc.Invocation;
import com.alibaba.dubbo.rpc.Invoker;

abstract class DubboAbstractFilter implements Filter {
    protected String getResourceName(Invoker<?> invoker, Invocation invocation) {
        StringBuilder buf = new StringBuilder(64);
        buf.append(invoker.getInterface().getName()).append(":").append(invocation.getMethodName()).append("(");
        boolean isFirst = true;
        Class[] var5 = invocation.getParameterTypes();
        int var6 = var5.length;

        for(int var7 = 0; var7 < var6; ++var7) {
            Class<?> clazz = var5[var7];
            if (!isFirst) {
                buf.append(",");
            }

            buf.append(clazz.getName());
            isFirst = false;
        }

        buf.append(")");
        return buf.toString();
    }
}
