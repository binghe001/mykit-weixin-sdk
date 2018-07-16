package io.mykit.weixin.sdk.common.utils.http.apache;

import io.mykit.weixin.sdk.common.bean.result.WxMediaUploadResult;
import io.mykit.weixin.sdk.common.error.WxError;
import io.mykit.weixin.sdk.common.error.WxErrorException;
import io.mykit.weixin.sdk.common.utils.http.MediaUploadRequestExecutor;
import io.mykit.weixin.sdk.common.utils.http.RequestHttp;
import org.apache.http.HttpEntity;
import org.apache.http.HttpHost;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.mime.HttpMultipartMode;
import org.apache.http.entity.mime.MultipartEntityBuilder;
import org.apache.http.impl.client.CloseableHttpClient;

import java.io.File;
import java.io.IOException;

/**
 * @Author: liuyazhuang
 * @Date: 2018/7/16 12:54
 * @Description: Apache 多媒体上传执行器
 */

public class ApacheMediaUploadRequestExecutor extends MediaUploadRequestExecutor<CloseableHttpClient, HttpHost> {
    public ApacheMediaUploadRequestExecutor(RequestHttp requestHttp) {
        super(requestHttp);
    }

    @Override
    public WxMediaUploadResult execute(String uri, File file) throws WxErrorException, IOException {
        HttpPost httpPost = new HttpPost(uri);
        if (requestHttp.getRequestHttpProxy() != null) {
            RequestConfig config = RequestConfig.custom().setProxy(requestHttp.getRequestHttpProxy()).build();
            httpPost.setConfig(config);
        }
        if (file != null) {
            HttpEntity entity = MultipartEntityBuilder
                    .create()
                    .addBinaryBody("media", file)
                    .setMode(HttpMultipartMode.RFC6532)
                    .build();
            httpPost.setEntity(entity);
        }
        try (CloseableHttpResponse response = requestHttp.getRequestHttpClient().execute(httpPost)) {
            String responseContent = Utf8ResponseHandler.INSTANCE.handleResponse(response);
            WxError error = WxError.fromJson(responseContent);
            if (error.getErrorCode() != 0) {
                throw new WxErrorException(error);
            }
            return WxMediaUploadResult.fromJson(responseContent);
        } finally {
            httpPost.releaseConnection();
        }
    }
}
