package io.mykit.weixin.sdk.common.utils.xml;

/**
 * @Author: liuyazhuang
 * @Date: 2018/7/16 10:47
 * @Description: 微信多媒体转换器
 */

public class XStreamMediaIdConverter extends XStreamCDataConverter{
    @Override
    public String toString(Object obj) {
        return "<MediaId>" + super.toString(obj) + "</MediaId>";
    }
}
