package com.duia.bean.common.dubbo.sentinel;

public class SentinelProperites {
    private Integer exceptionCount=10;
    private Long exceptionWindowTime=60L;
    private Long avgAnswerTime=5000L;
    private Long avgAnswerWindowTime=60L;

    public Integer getExceptionCount() {
        return exceptionCount;
    }

    public void setExceptionCount(Integer exceptionCount) {
        this.exceptionCount = exceptionCount;
    }

    public Long getExceptionWindowTime() {
        return exceptionWindowTime;
    }

    public void setExceptionWindowTime(Long exceptionWindowTime) {
        this.exceptionWindowTime = exceptionWindowTime;
    }

    public Long getAvgAnswerTime() {
        return avgAnswerTime;
    }

    public void setAvgAnswerTime(Long avgAnswerTime) {
        this.avgAnswerTime = avgAnswerTime;
    }

    public Long getAvgAnswerWindowTime() {
        return avgAnswerWindowTime;
    }

    public void setAvgAnswerWindowTime(Long avgAnswerWindowTime) {
        this.avgAnswerWindowTime = avgAnswerWindowTime;
    }
}
