package io.mykit.weixin.sdk.common.utils.http.jodd;

import io.mykit.weixin.sdk.common.error.WxError;
import io.mykit.weixin.sdk.common.error.WxErrorException;
import io.mykit.weixin.sdk.common.utils.http.RequestHttp;
import io.mykit.weixin.sdk.common.utils.http.SimpleGetRequestExecutor;
import jodd.http.HttpConnectionProvider;
import jodd.http.HttpRequest;
import jodd.http.HttpResponse;
import jodd.http.ProxyInfo;
import jodd.util.StringPool;

import java.io.IOException;

/**
 * @Author: liuyazhuang
 * @Date: 2018/7/16 12:43
 * @Description: Jodd简单Get请求执行器
 */

public class JoddHttpSimpleGetRequestExecutor extends SimpleGetRequestExecutor<HttpConnectionProvider, ProxyInfo> {

    public JoddHttpSimpleGetRequestExecutor(RequestHttp requestHttp) {
        super(requestHttp);
    }

    @Override
    public String execute(String uri, String queryParam) throws WxErrorException, IOException {
        if (queryParam != null) {
            if (uri.indexOf('?') == -1) {
                uri += '?';
            }
            uri += uri.endsWith("?") ? queryParam : '&' + queryParam;
        }

        HttpRequest request = HttpRequest.get(uri);
        if (requestHttp.getRequestHttpProxy() != null) {
            requestHttp.getRequestHttpClient().useProxy(requestHttp.getRequestHttpProxy());
        }
        request.withConnectionProvider(requestHttp.getRequestHttpClient());
        HttpResponse response = request.send();
        response.charset(StringPool.UTF_8);

        String responseContent = response.bodyText();

        WxError error = WxError.fromJson(responseContent);
        if (error.getErrorCode() != 0) {
            throw new WxErrorException(error);
        }
        return responseContent;
    }
}
