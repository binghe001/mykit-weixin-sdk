package io.mykit.weixin.sdk.common.utils.http.okhttp;

import io.mykit.weixin.sdk.common.error.WxError;
import io.mykit.weixin.sdk.common.error.WxErrorException;
import io.mykit.weixin.sdk.common.utils.http.RequestHttp;
import io.mykit.weixin.sdk.common.utils.http.SimplePostRequestExecutor;
import okhttp3.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;

/**
 * @Author: liuyazhuang
 * @Date: 2018/7/16 12:37
 * @Description: 简单POST请求执行器
 */

public class OkHttpSimplePostRequestExecutor extends SimplePostRequestExecutor<OkHttpClient, OkHttpProxyInfo> {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    public OkHttpSimplePostRequestExecutor(RequestHttp requestHttp) {
        super(requestHttp);
    }

    @Override
    public String execute(String uri, String postEntity) throws WxErrorException, IOException {
        logger.debug("OkHttpSimplePostRequestExecutor running");
        //得到httpClient
        OkHttpClient client = requestHttp.getRequestHttpClient();

        MediaType mediaType = MediaType.parse("text/plain; charset=utf-8");
        RequestBody body = RequestBody.create(mediaType, postEntity);

        Request request = new Request.Builder().url(uri).post(body).build();

        Response response = client.newCall(request).execute();
        String responseContent = response.body().string();
        WxError error = WxError.fromJson(responseContent);
        if (error.getErrorCode() != 0) {
            throw new WxErrorException(error);
        }
        return responseContent;
    }
}
