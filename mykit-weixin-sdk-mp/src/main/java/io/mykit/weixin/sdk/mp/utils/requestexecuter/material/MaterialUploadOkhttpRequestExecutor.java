package io.mykit.weixin.sdk.mp.utils.requestexecuter.material;

import io.mykit.weixin.sdk.common.error.WxError;
import io.mykit.weixin.sdk.common.error.WxErrorException;
import io.mykit.weixin.sdk.common.type.WxType;
import io.mykit.weixin.sdk.common.utils.http.RequestHttp;
import io.mykit.weixin.sdk.common.utils.http.okhttp.OkHttpProxyInfo;
import io.mykit.weixin.sdk.common.utils.json.WxGsonBuilder;
import io.mykit.weixin.sdk.mp.bean.material.WxMpMaterial;
import io.mykit.weixin.sdk.mp.bean.material.WxMpMaterialUploadResult;
import okhttp3.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Map;

/**
 * @Author: liuyazhuang
 * @Date: 2018/7/16 15:30
 * @Description: 上传执行成功的执行器
 */

public class MaterialUploadOkhttpRequestExecutor extends MaterialUploadRequestExecutor<OkHttpClient, OkHttpProxyInfo> {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    public MaterialUploadOkhttpRequestExecutor(RequestHttp requestHttp) {
        super(requestHttp);
    }

    @Override
    public WxMpMaterialUploadResult execute(String uri, WxMpMaterial material) throws WxErrorException, IOException {
        logger.debug("MaterialUploadOkhttpRequestExecutor is running");
        if (material == null) {
            throw new WxErrorException(WxError.builder().errorCode(-1).errorMsg("非法请求，material参数为空").build());
        }
        File file = material.getFile();
        if (file == null || !file.exists()) {
            throw new FileNotFoundException();
        }

        //得到httpClient

        OkHttpClient client = requestHttp.getRequestHttpClient();

        MultipartBody.Builder bodyBuilder = new MultipartBody.Builder()
                .setType(MediaType.parse("multipart/form-data"))
                .addFormDataPart("media",
                        file.getName(),
                        RequestBody.create(MediaType.parse("application/octet-stream"), file));
        Map<String, String> form = material.getForm();
        if (form != null) {
            bodyBuilder.addFormDataPart("description", WxGsonBuilder.create().toJson(form));
        }

        Request request = new Request.Builder().url(uri).post(bodyBuilder.build()).build();
        Response response = client.newCall(request).execute();
        String responseContent = response.body().string();
        WxError error = WxError.fromJson(responseContent, WxType.MP);
        if (error.getErrorCode() != 0) {
            throw new WxErrorException(error);
        } else {
            return WxMpMaterialUploadResult.fromJson(responseContent);
        }
    }
}
