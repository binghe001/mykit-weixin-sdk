package io.mykit.weixin.sdk.common.utils.http;

import io.mykit.weixin.sdk.common.bean.result.WxMediaUploadResult;
import io.mykit.weixin.sdk.common.utils.http.apache.ApacheMediaUploadRequestExecutor;
import io.mykit.weixin.sdk.common.utils.http.jodd.JoddHttpMediaUploadRequestExecutor;
import io.mykit.weixin.sdk.common.utils.http.okhttp.OkHttpMediaUploadRequestExecutor;

import java.io.File;

/**
 * @Author: liuyazhuang
 * @Date: 2018/7/16 11:41
 * @Description: 多媒体上传执行器，传递的参数类型是File，返回的结果是WxMediaUploadResult
 */

public abstract class MediaUploadRequestExecutor<H, P> implements RequestExecutor<WxMediaUploadResult, File>{
    protected RequestHttp<H, P> requestHttp;

    public MediaUploadRequestExecutor(RequestHttp requestHttp) {
        this.requestHttp = requestHttp;
    }

    public static RequestExecutor<WxMediaUploadResult, File> create(RequestHttp requestHttp) {
        switch (requestHttp.getRequestType()) {
            case APACHE_HTTP:
                return new ApacheMediaUploadRequestExecutor(requestHttp);
            case JODD_HTTP:
                return new JoddHttpMediaUploadRequestExecutor(requestHttp);
            case OK_HTTP:
                return new OkHttpMediaUploadRequestExecutor(requestHttp);
            default:
                return null;
        }
    }
}
