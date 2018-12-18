package com.duia.dubbo.common.http.repsonse;
/**
 * Http返回的信息
 * 此类只应用于所有Http服务器公用的
 * 各自的业务还需要另行扩展，规范同Dubbo的返回错误接口一致
 * 1001500
 * 10:项目
 * 01:模块1
 * 500:错误码
 * @author wangdong
 * @date 2018/12/17
 * @version 1.0.0
 */
public enum ResponseCommon {
    C_200(200,"请求成功"),
    C_500(500,"请求失败"),
    C_501(501,"登录失败"),
    C_502(502,"登录超时"),
    C_503(503,"用户账号密码不正确");
    ResponseCommon(Integer code, String msg){
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
