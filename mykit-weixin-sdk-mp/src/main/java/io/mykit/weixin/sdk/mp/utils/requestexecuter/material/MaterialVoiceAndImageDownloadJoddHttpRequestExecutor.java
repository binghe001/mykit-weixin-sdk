package io.mykit.weixin.sdk.mp.utils.requestexecuter.material;

import io.mykit.weixin.sdk.common.error.WxError;
import io.mykit.weixin.sdk.common.error.WxErrorException;
import io.mykit.weixin.sdk.common.utils.http.RequestHttp;
import io.mykit.weixin.sdk.common.utils.json.WxGsonBuilder;
import jodd.http.HttpConnectionProvider;
import jodd.http.HttpRequest;
import jodd.http.HttpResponse;
import jodd.http.ProxyInfo;
import jodd.util.StringPool;
import org.apache.commons.io.IOUtils;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;

/**
 * @Author: liuyazhuang
 * @Date: 2018/7/16 15:40
 * @Description: 下载视频和图片的Jodd 执行器
 */

public class MaterialVoiceAndImageDownloadJoddHttpRequestExecutor  extends MaterialVoiceAndImageDownloadRequestExecutor<HttpConnectionProvider, ProxyInfo> {
    public MaterialVoiceAndImageDownloadJoddHttpRequestExecutor(RequestHttp requestHttp, File tmpDirFile) {
        super(requestHttp, tmpDirFile);
    }

    @Override
    public InputStream execute(String uri, String materialId) throws WxErrorException, IOException {
        HttpRequest request = HttpRequest.post(uri);
        if (requestHttp.getRequestHttpProxy() != null) {
            requestHttp.getRequestHttpClient().useProxy(requestHttp.getRequestHttpProxy());
        }
        request.withConnectionProvider(requestHttp.getRequestHttpClient());

        request.query("media_id", materialId);
        HttpResponse response = request.send();
        response.charset(StringPool.UTF_8);
        try (InputStream inputStream = new ByteArrayInputStream(response.bodyBytes())) {
            // 下载媒体文件出错
            byte[] responseContent = IOUtils.toByteArray(inputStream);
            String responseContentString = new String(responseContent, StandardCharsets.UTF_8);
            if (responseContentString.length() < 100) {
                try {
                    WxError wxError = WxGsonBuilder.create().fromJson(responseContentString, WxError.class);
                    if (wxError.getErrorCode() != 0) {
                        throw new WxErrorException(wxError);
                    }
                } catch (com.google.gson.JsonSyntaxException ex) {
                    return new ByteArrayInputStream(responseContent);
                }
            }
            return new ByteArrayInputStream(responseContent);
        }
    }
}
