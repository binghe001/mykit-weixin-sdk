package io.mykit.weixin.sdk.common.utils.http.jodd;

import io.mykit.weixin.sdk.common.bean.result.WxMediaUploadResult;
import io.mykit.weixin.sdk.common.error.WxError;
import io.mykit.weixin.sdk.common.error.WxErrorException;
import io.mykit.weixin.sdk.common.utils.http.MediaUploadRequestExecutor;
import io.mykit.weixin.sdk.common.utils.http.RequestHttp;
import jodd.http.HttpConnectionProvider;
import jodd.http.HttpRequest;
import jodd.http.HttpResponse;
import jodd.http.ProxyInfo;
import jodd.util.StringPool;

import java.io.File;
import java.io.IOException;

/**
 * @Author: liuyazhuang
 * @Date: 2018/7/16 12:42
 * @Description: jodd多媒体上传执行器
 */

public class JoddHttpMediaUploadRequestExecutor extends MediaUploadRequestExecutor<HttpConnectionProvider, ProxyInfo> {
    public JoddHttpMediaUploadRequestExecutor(RequestHttp requestHttp) {
        super(requestHttp);
    }

    @Override
    public WxMediaUploadResult execute(String uri, File file) throws WxErrorException, IOException {
        HttpRequest request = HttpRequest.post(uri);
        if (requestHttp.getRequestHttpProxy() != null) {
            requestHttp.getRequestHttpClient().useProxy(requestHttp.getRequestHttpProxy());
        }
        request.withConnectionProvider(requestHttp.getRequestHttpClient());
        request.form("media", file);
        HttpResponse response = request.send();
        response.charset(StringPool.UTF_8);

        String responseContent = response.bodyText();
        WxError error = WxError.fromJson(responseContent);
        if (error.getErrorCode() != 0) {
            throw new WxErrorException(error);
        }
        return WxMediaUploadResult.fromJson(responseContent);
    }
}
