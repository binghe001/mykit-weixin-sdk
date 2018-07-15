package io.mykit.weixin.sdk.common.utils;

import org.apache.commons.lang3.StringUtils;

/**
 * @Author: liuyazhuang
 * @Date: 2018/7/15 23:48
 * @Description: 数据处理工具类
 */

public class DataUtils {
    /**
     * 将数据中包含的secret字符使用星号替换，防止日志打印时被输出
     */
    public static <T> T handleDataWithSecret(T data){
        T dataForLog = data;
        if(data instanceof String && StringUtils.contains((String)data, "&secret=")){
            dataForLog = (T) StringUtils.replaceAll((String)data,"&secret=\\w+&","&secret=******&");
        }
        return dataForLog;
    }
}
