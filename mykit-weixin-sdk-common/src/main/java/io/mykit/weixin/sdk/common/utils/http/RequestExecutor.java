package io.mykit.weixin.sdk.common.utils.http;

import io.mykit.weixin.sdk.common.error.WxErrorException;

import java.io.IOException;

/**
 * @Author: liuyazhuang
 * @Date: 2018/7/16 11:32
 * @Description: 请求执行器接口
 * @param T 返回值类型
 * @param E 请求参数类型
 */
public interface RequestExecutor <T,  E> {
    /**
     * @param uri  uri
     * @param data 数据
     */
    T execute(String uri, E data) throws WxErrorException, IOException;
}
