package io.mykit.weixin.sdk.common.utils;

import io.mykit.weixin.sdk.common.api.WxErrorExceptionHandler;
import io.mykit.weixin.sdk.common.error.WxErrorException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @Author: liuyazhuang
 * @Date: 2018/7/15 23:46
 * @Description: 打印异常的处理器
 */

public class LogExceptionHandler implements WxErrorExceptionHandler {
    private Logger logger = LoggerFactory.getLogger(LogExceptionHandler.class);
    @Override
    public void handle(WxErrorException e) {
        this.logger.error("Error happens", e);
    }
}
