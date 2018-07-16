package io.mykit.weixin.sdk.mp.utils.requestexecuter.material;

import io.mykit.weixin.sdk.common.error.WxError;
import io.mykit.weixin.sdk.common.error.WxErrorException;
import io.mykit.weixin.sdk.common.type.WxType;
import io.mykit.weixin.sdk.common.utils.http.RequestHttp;
import io.mykit.weixin.sdk.common.utils.http.okhttp.OkHttpProxyInfo;
import okhttp3.*;
import okio.BufferedSink;
import okio.Okio;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;

/**
 * @Author: liuyazhuang
 * @Date: 2018/7/16 15:41
 * @Description: 下载视频和图片成功的执行器
 */

public class MaterialVoiceAndImageDownloadOkhttpRequestExecutor extends MaterialVoiceAndImageDownloadRequestExecutor<OkHttpClient, OkHttpProxyInfo> {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    public MaterialVoiceAndImageDownloadOkhttpRequestExecutor(RequestHttp requestHttp, File tmpDirFile) {
        super(requestHttp, tmpDirFile);
    }

    @Override
    public InputStream execute(String uri, String materialId) throws WxErrorException, IOException {
        logger.debug("MaterialVoiceAndImageDownloadOkhttpRequestExecutor is running");
        OkHttpClient client = requestHttp.getRequestHttpClient();
        RequestBody requestBody = new FormBody.Builder().add("media_id", materialId).build();
        Request request = new Request.Builder().url(uri).get().post(requestBody).build();
        Response response = client.newCall(request).execute();
        String contentTypeHeader = response.header("Content-Type");
        if ("text/plain".equals(contentTypeHeader)) {
            String responseContent = response.body().string();
            throw new WxErrorException(WxError.fromJson(responseContent, WxType.MP));
        }
        try (ByteArrayOutputStream outputStream = new ByteArrayOutputStream(); BufferedSink sink = Okio.buffer(Okio.sink(outputStream))) {
            sink.writeAll(response.body().source());
            return new ByteArrayInputStream(outputStream.toByteArray());
        }
    }

}
