package com.jing.workflow.util;

import java.time.LocalDateTime;

/**
 * @Author: huangjingyan
 * @Date: 2021/4/28 17:57
 * @Mail: citrine5576@163.com
 * @Description: TODO
 * @Version: 1.0
 **/
public class KeyGeneratorUtil {

    public static synchronized String getKey(){
        String timestamp = DateHelper.getDateStr(LocalDateTime.now(),"yyyyMMddHHmmss");
        String key = "param"+timestamp+"_"+SnowFlakeUtil.nextId();
        return key;
    }

    public static synchronized String getProcessKey(){
        String timestamp = DateHelper.getDateStr(LocalDateTime.now(),"yyyyMMddHHmmss");
        String key = "process-"+timestamp+"-"+SnowFlakeUtil.nextId();
        return key;
    }
}
