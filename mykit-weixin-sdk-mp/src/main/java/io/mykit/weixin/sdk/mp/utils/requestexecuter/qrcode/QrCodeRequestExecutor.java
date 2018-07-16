package io.mykit.weixin.sdk.mp.utils.requestexecuter.qrcode;

import io.mykit.weixin.sdk.common.error.WxError;
import io.mykit.weixin.sdk.common.error.WxErrorException;
import io.mykit.weixin.sdk.common.utils.http.RequestExecutor;
import io.mykit.weixin.sdk.common.utils.http.RequestHttp;
import io.mykit.weixin.sdk.mp.bean.result.WxMpQrCodeTicket;

import java.io.File;

/**
 * @Author: liuyazhuang
 * @Date: 2018/7/16 16:22
 * @Description: 二维码执行器基础抽象类
 */

public abstract class QrCodeRequestExecutor <H, P> implements RequestExecutor<File, WxMpQrCodeTicket> {
    protected RequestHttp<H, P> requestHttp;

    public QrCodeRequestExecutor(RequestHttp requestHttp) {
        this.requestHttp = requestHttp;
    }

    public static RequestExecutor<File, WxMpQrCodeTicket> create(RequestHttp requestHttp) throws WxErrorException {
        switch (requestHttp.getRequestType()) {
            case APACHE_HTTP:
                return new QrCodeApacheHttpRequestExecutor(requestHttp);
            case JODD_HTTP:
                return new QrCodeJoddHttpRequestExecutor(requestHttp);
            case OK_HTTP:
                return new QrCodeOkhttpRequestExecutor(requestHttp);
            default:
                throw new WxErrorException(WxError.builder().errorCode(-1).errorMsg("不支持的http框架").build());
        }
    }
}
