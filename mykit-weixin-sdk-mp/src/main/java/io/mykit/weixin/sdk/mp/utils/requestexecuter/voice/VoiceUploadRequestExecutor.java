package io.mykit.weixin.sdk.mp.utils.requestexecuter.voice;

import io.mykit.weixin.sdk.common.utils.http.RequestExecutor;
import io.mykit.weixin.sdk.common.utils.http.RequestHttp;

import java.io.File;

/**
 * @Author: liuyazhuang
 * @Date: 2018/7/16 16:25
 * @Description: Voice上传基础抽象类
 */

public abstract class VoiceUploadRequestExecutor <H, P> implements RequestExecutor<Boolean, File> {
    protected RequestHttp<H, P> requestHttp;

    public VoiceUploadRequestExecutor(RequestHttp requestHttp) {
        this.requestHttp = requestHttp;
    }

    public static RequestExecutor<Boolean, File> create(RequestHttp requestHttp) {
        switch (requestHttp.getRequestType()) {
            case APACHE_HTTP:
                return new VoiceUploadApacheHttpRequestExecutor(requestHttp);
            case JODD_HTTP:
            case OK_HTTP:
            default:
                return null;
        }
    }
}
