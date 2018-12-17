package com.duia.dubbo.common.http.util;

import com.duia.dubbo.common.http.repsonse.ResponseCommon;
import com.duia.dubbo.common.http.repsonse.ResponseVo;

import java.util.Objects;

/**
 * http 返回工厂
 *
 * @author wangdong
 * @version 1.0.0
 * @date 2018/12/17
 */
public class HttpResultFactory {
    /**
     * http返回结果 自定义
     * @param code 返回码
     * @param msg  返回信息
     * @param result 返回对象
     * @return 结果集
     */
    public static <T> ResponseVo<T> result(Integer code, String msg, T result) {
        if (Objects.isNull(code)) {
            throw new NullPointerException("code is null");
        } else if (Objects.isNull(msg)) {
            throw new NullPointerException("msg is null");
        } else {
            ResponseVo<T> vo = new ResponseVo<>();
            vo.setCode(code);
            vo.setMsg(msg);
            if (result != null) {
                vo.setResult(result);
            }
            return vo;
        }
    }

    /**
     * http返回结果 统一失败
     * @return 结果集
     */
    public static <T> ResponseVo<T> failCommon() {
        ResponseVo<T> vo = new ResponseVo<>();
        vo.setCode(ResponseCommon.C_500.getCode());
        vo.setMsg(ResponseCommon.C_500.getMsg());
        return vo;
    }

    /**
     * http返回结果 统一成功
     * @return 结果集
     */
    public static <T> ResponseVo<T> successCommon(T result) {
        ResponseVo<T> vo = new ResponseVo<>();
        vo.setCode(ResponseCommon.C_200.getCode());
        vo.setMsg(ResponseCommon.C_200.getMsg());
        if (result != null) {
            vo.setResult(result);
        }
        return vo;
    }


}
