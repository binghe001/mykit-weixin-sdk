package io.mykit.weixin.sdk.common.utils.http;

import io.mykit.weixin.sdk.common.utils.http.apache.ApacheHttpClientSimpleGetRequestExecutor;
import io.mykit.weixin.sdk.common.utils.http.jodd.JoddHttpSimpleGetRequestExecutor;
import io.mykit.weixin.sdk.common.utils.http.okhttp.OkHttpSimpleGetRequestExecutor;

/**
 * @Author: liuyazhuang
 * @Date: 2018/7/16 11:38
 * @Description: 简单Get请求执行器，传递的参数是Spring, 返回结果是Spring
 */

public abstract class SimpleGetRequestExecutor<H, P> implements RequestExecutor<String, String> {

    protected RequestHttp<H, P> requestHttp;

    public SimpleGetRequestExecutor(RequestHttp<H, P> requestHttp) {
        this.requestHttp = requestHttp;
    }

    public static RequestExecutor<String, String> create(RequestHttp requestHttp) {
        switch (requestHttp.getRequestType()) {
            case APACHE_HTTP:
                return new ApacheHttpClientSimpleGetRequestExecutor(requestHttp);
            case JODD_HTTP:
                return new JoddHttpSimpleGetRequestExecutor(requestHttp);
            case OK_HTTP:
                return new OkHttpSimpleGetRequestExecutor(requestHttp);
            default:
                throw new IllegalArgumentException("非法请求参数");
        }
    }
}
