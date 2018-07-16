package io.mykit.weixin.sdk.mp.utils.requestexecuter.media;

import io.mykit.weixin.sdk.common.error.WxError;
import io.mykit.weixin.sdk.common.error.WxErrorException;
import io.mykit.weixin.sdk.common.type.WxType;
import io.mykit.weixin.sdk.common.utils.http.RequestHttp;
import io.mykit.weixin.sdk.mp.bean.material.WxMediaImgUploadResult;
import jodd.http.HttpConnectionProvider;
import jodd.http.HttpRequest;
import jodd.http.HttpResponse;
import jodd.http.ProxyInfo;
import jodd.util.StringPool;

import java.io.File;
import java.io.IOException;

/**
 * @Author: liuyazhuang
 * @Date: 2018/7/16 16:13
 * @Description: 上传图片HTTP请求执行器
 */

public class MediaImgUploadHttpRequestExecutor  extends MediaImgUploadRequestExecutor<HttpConnectionProvider, ProxyInfo> {
    public MediaImgUploadHttpRequestExecutor(RequestHttp requestHttp) {
        super(requestHttp);
    }

    @Override
    public WxMediaImgUploadResult execute(String uri, File data) throws WxErrorException, IOException {
        if (data == null) {
            throw new WxErrorException(WxError.builder().errorCode(-1).errorMsg("文件对象为空").build());
        }

        HttpRequest request = HttpRequest.post(uri);
        if (requestHttp.getRequestHttpProxy() != null) {
            requestHttp.getRequestHttpClient().useProxy(requestHttp.getRequestHttpProxy());
        }
        request.withConnectionProvider(requestHttp.getRequestHttpClient());

        request.form("media", data);
        HttpResponse response = request.send();
        response.charset(StringPool.UTF_8);
        String responseContent = response.bodyText();
        WxError error = WxError.fromJson(responseContent, WxType.MP);
        if (error.getErrorCode() != 0) {
            throw new WxErrorException(error);
        }

        return WxMediaImgUploadResult.fromJson(responseContent);
    }
}
