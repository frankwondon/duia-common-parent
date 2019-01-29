package com.duia.bean.common.dubbo.exception;

public class SentinelInitException extends RuntimeException {
    public SentinelInitException() {
        super();
    }
    public SentinelInitException(String message) {
        super(message);
    }
}
