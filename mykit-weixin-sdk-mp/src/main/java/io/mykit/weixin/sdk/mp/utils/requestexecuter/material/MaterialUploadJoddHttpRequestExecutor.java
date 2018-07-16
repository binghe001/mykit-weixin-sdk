package io.mykit.weixin.sdk.mp.utils.requestexecuter.material;

import io.mykit.weixin.sdk.common.error.WxError;
import io.mykit.weixin.sdk.common.error.WxErrorException;
import io.mykit.weixin.sdk.common.type.WxType;
import io.mykit.weixin.sdk.common.utils.http.RequestHttp;
import io.mykit.weixin.sdk.common.utils.json.WxGsonBuilder;
import io.mykit.weixin.sdk.mp.bean.material.WxMpMaterial;
import io.mykit.weixin.sdk.mp.bean.material.WxMpMaterialUploadResult;
import jodd.http.HttpConnectionProvider;
import jodd.http.HttpRequest;
import jodd.http.HttpResponse;
import jodd.http.ProxyInfo;
import jodd.util.StringPool;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Map;

/**
 * @Author: liuyazhuang
 * @Date: 2018/7/16 15:29
 * @Description: Jodd上传执行器
 */

public class MaterialUploadJoddHttpRequestExecutor extends MaterialUploadRequestExecutor<HttpConnectionProvider, ProxyInfo> {
    public MaterialUploadJoddHttpRequestExecutor(RequestHttp requestHttp) {
        super(requestHttp);
    }

    @Override
    public WxMpMaterialUploadResult execute(String uri, WxMpMaterial material) throws WxErrorException, IOException {
        HttpRequest request = HttpRequest.post(uri);
        if (requestHttp.getRequestHttpProxy() != null) {
            requestHttp.getRequestHttpClient().useProxy(requestHttp.getRequestHttpProxy());
        }
        request.withConnectionProvider(requestHttp.getRequestHttpClient());

        if (material == null) {
            throw new WxErrorException(WxError.builder().errorCode(-1).errorMsg("非法请求，material参数为空").build());
        }

        File file = material.getFile();
        if (file == null || !file.exists()) {
            throw new FileNotFoundException();
        }
        request.form("media", file);
        Map<String, String> form = material.getForm();
        if (material.getForm() != null) {
            request.form("description", WxGsonBuilder.create().toJson(form));
        }

        HttpResponse response = request.send();
        response.charset(StringPool.UTF_8);
        String responseContent = response.bodyText();
        WxError error = WxError.fromJson(responseContent, WxType.MP);
        if (error.getErrorCode() != 0) {
            throw new WxErrorException(error);
        } else {
            return WxMpMaterialUploadResult.fromJson(responseContent);
        }
    }
}
