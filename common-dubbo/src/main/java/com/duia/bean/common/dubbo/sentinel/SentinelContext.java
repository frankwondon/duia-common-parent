package com.duia.bean.common.dubbo.sentinel;

import com.duia.bean.common.dubbo.exception.SentinelInitException;
import org.apache.commons.lang3.math.NumberUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.ClassPathResource;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class SentinelContext {
    private SentinelContext(){}
    private static final Logger logger = LoggerFactory.getLogger(SentinelContext.class);
    private static SentinelProperites properites;
    private static volatile boolean init=false;

    public static SentinelContext build(){
        initProperties();
        return new SentinelContext();
    }

    private synchronized static void initProperties() {
        Properties prop = null;
        try {
            ClassPathResource classPathResource=new ClassPathResource("sentinel.properties");
            InputStream inputStream = classPathResource.getInputStream();
            if (inputStream == null){
                logger.error("sentinel.properties not found!");
            }
            prop = new Properties();
            prop.load(inputStream);
        } catch (IOException e) {
            logger.error("sentinel.properties not found!");
        }
        properites=new SentinelProperites();
        if (prop!=null){
            int ec=NumberUtils.toInt(prop.getProperty("sentinel.exception_count"),0);
            if (ec>0){
                properites.setExceptionCount(ec);
            }
            long ewt=NumberUtils.toLong(prop.getProperty("sentinel.exception_window_time"),0);
            if (ewt>0) {
                properites.setExceptionWindowTime(ewt);
            }
            long aat=NumberUtils.toLong(prop.getProperty("sentinel.avg_answer_time"),0);
            if (aat>0){
                properites.setAvgAnswerTime(aat);
            }
            long aawt=NumberUtils.toLong(prop.getProperty("sentinel.avg_answer_window_time"),0);
            if (aawt>0){
                properites.setAvgAnswerWindowTime(aawt);
            }
        }
        init=true;
    }

    public static int geExceptionCount(){
        if (!init)
            initProperties();
        return properites.getExceptionCount();
    }

    public static int getExceptionWindowTime(){
        if (!init)
            initProperties();
        return properites.getExceptionWindowTime().intValue();
    }
    public static int getAvgAnswerWindowTime(){
        if (!init)
            initProperties();
        return properites.getAvgAnswerWindowTime().intValue();
    }

    public static long getAvgAnswerTime(){
        if (!init)
            initProperties();
        return properites.getAvgAnswerTime();
    }
}
