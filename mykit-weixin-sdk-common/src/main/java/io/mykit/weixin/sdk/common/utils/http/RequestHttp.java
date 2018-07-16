package io.mykit.weixin.sdk.common.utils.http;

/**
 * @Author: liuyazhuang
 * @Date: 2018/7/16 11:36
 * @Description: HTTP请求的基础接口
 */
public interface RequestHttp <H, P>{
    /**
     * 返回httpClient
     *
     */
    H getRequestHttpClient();

    /**
     * 返回httpProxy
     *
     */
    P getRequestHttpProxy();


    HttpType getRequestType();
}
