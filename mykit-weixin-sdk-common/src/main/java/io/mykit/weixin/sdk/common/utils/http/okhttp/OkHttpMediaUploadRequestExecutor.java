package io.mykit.weixin.sdk.common.utils.http.okhttp;

import io.mykit.weixin.sdk.common.bean.result.WxMediaUploadResult;
import io.mykit.weixin.sdk.common.error.WxError;
import io.mykit.weixin.sdk.common.error.WxErrorException;
import io.mykit.weixin.sdk.common.utils.http.MediaUploadRequestExecutor;
import io.mykit.weixin.sdk.common.utils.http.RequestHttp;
import okhttp3.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.IOException;

/**
 * @Author: liuyazhuang
 * @Date: 2018/7/16 12:31
 * @Description 多媒体上传执行器
 */

public class OkHttpMediaUploadRequestExecutor extends MediaUploadRequestExecutor<OkHttpClient, OkHttpProxyInfo> {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    public OkHttpMediaUploadRequestExecutor(RequestHttp requestHttp) {
        super(requestHttp);
    }

    @Override
    public WxMediaUploadResult execute(String uri, File file) throws WxErrorException, IOException {
        logger.debug("OkHttpMediaUploadRequestExecutor is running");
        //得到httpClient
        OkHttpClient client = requestHttp.getRequestHttpClient();

        RequestBody body = new MultipartBody.Builder()
                .setType(MediaType.parse("multipart/form-data"))
                .addFormDataPart("media",
                        file.getName(),
                        RequestBody.create(MediaType.parse("application/octet-stream"), file))
                .build();
        Request request = new Request.Builder().url(uri).post(body).build();

        Response response = client.newCall(request).execute();
        String responseContent = response.body().string();
        WxError error = WxError.fromJson(responseContent);
        if (error.getErrorCode() != 0) {
            throw new WxErrorException(error);
        }
        return WxMediaUploadResult.fromJson(responseContent);
    }
}
