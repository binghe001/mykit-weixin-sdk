package io.mykit.weixin.sdk.common.utils.xml;

import com.thoughtworks.xstream.converters.basic.StringConverter;

/**
 * @Author: liuyazhuang
 * @Date: 2018/7/16 10:33
 * @Description: 带有CDataXML信息的转换器
 */

public class XStreamCDataConverter extends StringConverter {
    @Override
    public String toString(Object obj) {
        return "<![CDATA[" + super.toString(obj) + "]]>";
    }
}
