package com.duia.dubbo.common.dubbo.exception;
/**
 *   Dubbo公用异常信息
 *  各自的业务还需要另行扩展，规范同Dubbo的返回错误接口一致
 *  1001500
 *  10:项目
 *  01:模块1
 *  500:错误码
 * @author wangdong
 * @date 2018/12/17
 * @version 1.0.0
 */
public enum  CommonException {
    E_500(500,"请求失败"),
    E_501(501,"参数%s为NULL");
    CommonException(Integer code, String msg){
        this.code=code;
        this.msg=msg;
    }
    private Integer code;
    private String msg;
    public Integer getCode() {
        return code;
    }
    public String getMsg() {
        return msg;
    }
}
