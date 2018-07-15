package io.mykit.weixin.sdk.common.utils;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * @Author: liuyazhuang
 * @Date: 2018/7/15 20:16
 * @Description: 自定义的ToString方法，用于产生去掉空值属性的字符串
 */

public class ToStringUtils {

    public static final ToStringStyle THE_STYLE = new SimpleMultiLineToStringStyle();

    /**
     * 用于产生去掉空值属性并以换行符分割各个属性键值的toString字符串
     * @param obj : 需要转换的对象obj
     * @return 返回结果字符串
     */
    public static String toSimpleString(Object obj){
        String toStringResult = ToStringBuilder.reflectionToString(obj, THE_STYLE);
        String[] split = toStringResult.split(SimpleMultiLineToStringStyle.LINE_SEPARATOR);
        StringBuilder result = new StringBuilder();
        for(String s : split){
            if(s.endsWith(SimpleMultiLineToStringStyle.NULL_TEXT)){
                continue;
            }
            result.append(s + SimpleMultiLineToStringStyle.LINE_SEPARATOR);
        }
        if(result.length() == 0){
            return "";
        }

        //如果没有非空属性，就输出<all null properties>
        if(StringUtils.countMatches(result, SimpleMultiLineToStringStyle.LINE_SEPARATOR) == 2){
            return result.toString().split(SimpleMultiLineToStringStyle.LINE_SEPARATOR)[0]
                    + "<all null values>";
        }
        return result.deleteCharAt(result.length() - 1).toString();
    }


    /**
     * 私有静态内部类，继承ToStringStyle，提供简单的字符串样式风格
     */
    private static class SimpleMultiLineToStringStyle extends ToStringStyle{
        private static final long serialVersionUID = 8259631821513851080L;
        private static final String LINE_SEPARATOR = "\n";
        private static final String NULL_TEXT = "<null>";

        public SimpleMultiLineToStringStyle(){
            super();
            this.setContentStart("[");
            this.setFieldSeparator(LINE_SEPARATOR + "  ");
            this.setFieldSeparatorAtStart(true);
            this.setContentEnd(LINE_SEPARATOR + "]");
            this.setNullText(NULL_TEXT);
            this.setUseShortClassName(true);
            this.setUseIdentityHashCode(false);
        }
    }
}
