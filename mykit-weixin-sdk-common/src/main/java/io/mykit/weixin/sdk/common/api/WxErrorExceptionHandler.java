package io.mykit.weixin.sdk.common.api;

import io.mykit.weixin.sdk.common.error.WxErrorException;

/**
 * @Author: liuyazhuang
 * @Date: 2018/7/15 22:37
 * @Description: 微信异常处理器接口
 */
public interface WxErrorExceptionHandler {

    void handle(WxErrorException e);
}
