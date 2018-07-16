package io.mykit.weixin.sdk.mp.utils.requestexecuter.material;

import com.google.common.collect.ImmutableMap;
import io.mykit.weixin.sdk.common.error.WxError;
import io.mykit.weixin.sdk.common.error.WxErrorException;
import io.mykit.weixin.sdk.common.type.WxType;
import io.mykit.weixin.sdk.common.utils.http.RequestHttp;
import io.mykit.weixin.sdk.common.utils.http.apache.Utf8ResponseHandler;
import io.mykit.weixin.sdk.common.utils.json.WxGsonBuilder;
import io.mykit.weixin.sdk.mp.bean.material.WxMpMaterialNews;
import io.mykit.weixin.sdk.mp.utils.json.WxMpGsonBuilder;
import org.apache.http.HttpHost;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;

/**
 * @Author: liuyazhuang
 * @Date: 2018/7/16 15:21
 * @Description: Apache删除图文消息的执行器
 */

public class MaterialNewsInfoApacheHttpRequestExecutor extends MaterialNewsInfoRequestExecutor<CloseableHttpClient, HttpHost> {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    public MaterialNewsInfoApacheHttpRequestExecutor(RequestHttp requestHttp) {
        super(requestHttp);
    }

    @Override
    public WxMpMaterialNews execute(String uri, String materialId) throws WxErrorException, IOException {
        HttpPost httpPost = new HttpPost(uri);
        if (requestHttp.getRequestHttpProxy() != null) {
            RequestConfig config = RequestConfig.custom().setProxy(requestHttp.getRequestHttpProxy()).build();
            httpPost.setConfig(config);
        }

        httpPost.setEntity(new StringEntity(WxGsonBuilder.create().toJson(ImmutableMap.of("media_id", materialId))));
        try (CloseableHttpResponse response = requestHttp.getRequestHttpClient().execute(httpPost)) {
            String responseContent = Utf8ResponseHandler.INSTANCE.handleResponse(response);
            this.logger.debug("响应原始数据：{}", responseContent);
            WxError error = WxError.fromJson(responseContent, WxType.MP);
            if (error.getErrorCode() != 0) {
                throw new WxErrorException(error);
            } else {
                return WxMpGsonBuilder.create().fromJson(responseContent, WxMpMaterialNews.class);
            }
        } finally {
            httpPost.releaseConnection();
        }
    }
}
