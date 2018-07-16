package io.mykit.weixin.sdk.mp.utils.requestexecuter.material;

import io.mykit.weixin.sdk.common.utils.http.RequestExecutor;
import io.mykit.weixin.sdk.common.utils.http.RequestHttp;

import java.io.File;
import java.io.InputStream;

/**
 * @Author: liuyazhuang
 * @Date: 2018/7/16 15:42
 * @Description: 下载视频和图片的基础执行器抽象类
 */

public abstract class MaterialVoiceAndImageDownloadRequestExecutor <H, P> implements RequestExecutor<InputStream, String> {
    protected RequestHttp<H, P> requestHttp;
    protected File tmpDirFile;

    public MaterialVoiceAndImageDownloadRequestExecutor(RequestHttp requestHttp, File tmpDirFile) {
        this.requestHttp = requestHttp;
        this.tmpDirFile = tmpDirFile;
    }

    public static RequestExecutor<InputStream, String> create(RequestHttp requestHttp, File tmpDirFile) {
        switch (requestHttp.getRequestType()) {
            case APACHE_HTTP:
                return new MaterialVoiceAndImageDownloadApacheHttpRequestExecutor(requestHttp, tmpDirFile);
            case JODD_HTTP:
                return new MaterialVoiceAndImageDownloadJoddHttpRequestExecutor(requestHttp, tmpDirFile);
            case OK_HTTP:
                return new MaterialVoiceAndImageDownloadOkhttpRequestExecutor(requestHttp, tmpDirFile);
            default:
                return null;
        }
    }
}
