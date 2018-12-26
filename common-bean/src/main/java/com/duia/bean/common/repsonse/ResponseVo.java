package com.duia.bean.common.repsonse;

import java.io.Serializable;
/**
 * httpResponse封装对象
 * 默认返回成功
 * @author wangdong
 * @date 2018/12/17
 * @version 1.0.0
 */
public class ResponseVo<T> implements Serializable {
    /**状态码*/
    private Integer code= ResponseCode.C_200.getCode();
    /**提示信息*/
    private String msg= ResponseCode.C_200.getMsg();
    /**系统时间戳*/
    private Long timeStamp=System.currentTimeMillis();
    /**返回对象*/
    private T result;

    public ResponseVo() {
    }

    public ResponseVo(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public ResponseVo(Integer code, String msg, T result) {
        this.code = code;
        this.msg = msg;
        this.result = result;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public T getResult() {
        return result;
    }

    public void setResult(T result) {
        this.result = result;
    }

    public Long getTimeStamp() {
        return timeStamp;
    }
}
