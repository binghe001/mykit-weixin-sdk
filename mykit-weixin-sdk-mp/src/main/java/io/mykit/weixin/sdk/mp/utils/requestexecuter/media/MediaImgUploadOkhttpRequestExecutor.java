package io.mykit.weixin.sdk.mp.utils.requestexecuter.media;

import io.mykit.weixin.sdk.common.error.WxError;
import io.mykit.weixin.sdk.common.error.WxErrorException;
import io.mykit.weixin.sdk.common.type.WxType;
import io.mykit.weixin.sdk.common.utils.http.RequestHttp;
import io.mykit.weixin.sdk.common.utils.http.okhttp.OkHttpProxyInfo;
import io.mykit.weixin.sdk.mp.bean.material.WxMediaImgUploadResult;
import okhttp3.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.IOException;

/**
 * @Author: liuyazhuang
 * @Date: 2018/7/16 16:14
 * @Description: 上传图片成功的执行器
 */

public class MediaImgUploadOkhttpRequestExecutor  extends MediaImgUploadRequestExecutor<OkHttpClient, OkHttpProxyInfo> {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    public MediaImgUploadOkhttpRequestExecutor(RequestHttp requestHttp) {
        super(requestHttp);
    }

    @Override
    public WxMediaImgUploadResult execute(String uri, File file) throws WxErrorException, IOException {
        logger.debug("MediaImgUploadOkhttpRequestExecutor is running");
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
        WxError error = WxError.fromJson(responseContent, WxType.MP);
        if (error.getErrorCode() != 0) {
            throw new WxErrorException(error);
        }

        return WxMediaImgUploadResult.fromJson(responseContent);
    }
}
