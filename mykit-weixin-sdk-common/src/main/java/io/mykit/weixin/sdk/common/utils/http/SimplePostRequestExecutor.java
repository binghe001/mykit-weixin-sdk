package io.mykit.weixin.sdk.common.utils.http;

import io.mykit.weixin.sdk.common.utils.http.apache.ApacheSimplePostRequestExecutor;
import io.mykit.weixin.sdk.common.utils.http.jodd.JoddHttpSimplePostRequestExecutor;
import io.mykit.weixin.sdk.common.utils.http.okhttp.OkHttpSimplePostRequestExecutor;

/**
 * @Author: liuyazhuang
 * @Date: 2018/7/16 11:31
 * @Description: 简单POST请求的执行器抽象类，请求的参数是Spring,返回的结果也是Spring
 */

public abstract class SimplePostRequestExecutor<H, P> implements RequestExecutor<String, String> {
    protected RequestHttp<H, P> requestHttp;

    public SimplePostRequestExecutor(RequestHttp requestHttp) {
        this.requestHttp = requestHttp;
    }

    public static RequestExecutor<String, String> create(RequestHttp requestHttp) {
        switch (requestHttp.getRequestType()) {
            case APACHE_HTTP:
                return new ApacheSimplePostRequestExecutor(requestHttp);
            case JODD_HTTP:
                return new JoddHttpSimplePostRequestExecutor(requestHttp);
            case OK_HTTP:
                return new OkHttpSimplePostRequestExecutor(requestHttp);
            default:
                return null;
        }
    }
}
