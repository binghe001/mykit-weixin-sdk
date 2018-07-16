package io.mykit.weixin.sdk.common.utils.http;

import io.mykit.weixin.sdk.common.utils.http.apache.ApacheMediaDownloadRequestExecutor;
import io.mykit.weixin.sdk.common.utils.http.jodd.JoddHttpMediaDownloadRequestExecutor;
import io.mykit.weixin.sdk.common.utils.http.okhttp.OkHttpMediaDownloadRequestExecutor;

import java.io.File;

/**
 * @Author: liuyazhuang
 * @Date: 2018/7/16 11:49
 * @Description: 下载媒体文件请求执行器，请求的参数是String, 返回的结果是File, 视频文件不支持下载
 */

public abstract class BaseMediaDownloadRequestExecutor<H, P> implements RequestExecutor<File, String>{

    protected RequestHttp<H, P> requestHttp;
    protected File tmpDirFile;

    public BaseMediaDownloadRequestExecutor(RequestHttp<H, P> requestHttp, File tmpDirFile) {
        this.requestHttp = requestHttp;
        this.tmpDirFile = tmpDirFile;
    }

    public static RequestExecutor<File, String> create(RequestHttp requestHttp, File tmpDirFile) {
        switch (requestHttp.getRequestType()) {
            case APACHE_HTTP:
                return new ApacheMediaDownloadRequestExecutor(requestHttp, tmpDirFile);
            case JODD_HTTP:
                return new JoddHttpMediaDownloadRequestExecutor(requestHttp, tmpDirFile);
            case OK_HTTP:
                return new OkHttpMediaDownloadRequestExecutor(requestHttp, tmpDirFile);
            default:
                return null;
        }
    }
}
