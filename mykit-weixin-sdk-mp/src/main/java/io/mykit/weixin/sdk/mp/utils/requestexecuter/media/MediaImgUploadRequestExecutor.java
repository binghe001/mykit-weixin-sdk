package io.mykit.weixin.sdk.mp.utils.requestexecuter.media;

import io.mykit.weixin.sdk.common.utils.http.RequestExecutor;
import io.mykit.weixin.sdk.common.utils.http.RequestHttp;
import io.mykit.weixin.sdk.mp.bean.material.WxMediaImgUploadResult;

import java.io.File;

/**
 * @Author: liuyazhuang
 * @Date: 2018/7/16 16:16
 * @Description: 上传图片基础执行器抽象类
 */

public abstract class MediaImgUploadRequestExecutor <H, P> implements RequestExecutor<WxMediaImgUploadResult, File> {
    protected RequestHttp<H, P> requestHttp;

    public MediaImgUploadRequestExecutor(RequestHttp requestHttp) {
        this.requestHttp = requestHttp;
    }

    public static RequestExecutor<WxMediaImgUploadResult, File> create(RequestHttp requestHttp) {
        switch (requestHttp.getRequestType()) {
            case APACHE_HTTP:
                return new MediaImgUploadApacheHttpRequestExecutor(requestHttp);
            case JODD_HTTP:
                return new MediaImgUploadHttpRequestExecutor(requestHttp);
            case OK_HTTP:
                return new MediaImgUploadOkhttpRequestExecutor(requestHttp);
            default:
                return null;
        }
    }
}
