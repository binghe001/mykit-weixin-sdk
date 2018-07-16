package io.mykit.weixin.sdk.mp.utils.requestexecuter.material;

import com.google.common.collect.ImmutableMap;
import io.mykit.weixin.sdk.common.error.WxError;
import io.mykit.weixin.sdk.common.error.WxErrorException;
import io.mykit.weixin.sdk.common.type.WxType;
import io.mykit.weixin.sdk.common.utils.http.RequestHttp;
import io.mykit.weixin.sdk.common.utils.http.okhttp.OkHttpProxyInfo;
import io.mykit.weixin.sdk.common.utils.json.WxGsonBuilder;
import io.mykit.weixin.sdk.mp.bean.material.WxMpMaterialNews;
import io.mykit.weixin.sdk.mp.utils.json.WxMpGsonBuilder;
import okhttp3.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;

/**
 * @Author: liuyazhuang
 * @Date: 2018/7/16 15:24
 * @Description:  请求图文信息的执行器
 */

public class MaterialNewsInfoOkhttpRequestExecutor extends MaterialNewsInfoRequestExecutor<OkHttpClient, OkHttpProxyInfo> {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    public MaterialNewsInfoOkhttpRequestExecutor(RequestHttp requestHttp) {
        super(requestHttp);
    }

    @Override
    public WxMpMaterialNews execute(String uri, String materialId) throws WxErrorException, IOException {

        //得到httpClient
        OkHttpClient client = requestHttp.getRequestHttpClient();

        RequestBody requestBody = RequestBody.create(MediaType.parse("application/json"),
                WxGsonBuilder.create().toJson(ImmutableMap.of("media_id", materialId)));
        Request request = new Request.Builder().url(uri).post(requestBody).build();

        Response response = client.newCall(request).execute();
        String responseContent = response.body().string();
        this.logger.debug("响应原始数据：{}", responseContent);

        WxError error = WxError.fromJson(responseContent, WxType.MP);
        if (error.getErrorCode() != 0) {
            throw new WxErrorException(error);
        } else {
            return WxMpGsonBuilder.create().fromJson(responseContent, WxMpMaterialNews.class);
        }
    }
}
