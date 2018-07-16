package io.mykit.weixin.sdk.common.session;

import java.util.Enumeration;

/**
 * @Author: liuyazhuang
 * @Date: 2018/7/16 13:06
 * @Description: WxSession接口
 */
public interface WxSession {

    Object getAttribute(String name);

    Enumeration<String> getAttributeNames();

    void setAttribute(String name, Object value);

    void removeAttribute(String name);

    void invalidate();
}
