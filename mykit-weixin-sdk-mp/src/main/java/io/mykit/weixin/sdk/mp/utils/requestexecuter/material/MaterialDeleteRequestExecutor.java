package io.mykit.weixin.sdk.mp.utils.requestexecuter.material;

import io.mykit.weixin.sdk.common.utils.http.RequestExecutor;
import io.mykit.weixin.sdk.common.utils.http.RequestHttp;

/**
 * @Author: liuyazhuang
 * @Date: 2018/7/16 15:20
 * @Description: 执行删除操作的抽象执行器类
 */

public abstract class MaterialDeleteRequestExecutor <H, P> implements RequestExecutor<Boolean, String> {
    protected RequestHttp<H, P> requestHttp;

    public MaterialDeleteRequestExecutor(RequestHttp requestHttp) {
        this.requestHttp = requestHttp;
    }

    public static RequestExecutor<Boolean, String> create(RequestHttp requestHttp) {
        switch (requestHttp.getRequestType()) {
            case APACHE_HTTP:
                return new MaterialDeleteApacheHttpRequestExecutor(requestHttp);
            case JODD_HTTP:
                return new MaterialDeleteJoddHttpRequestExecutor(requestHttp);
            case OK_HTTP:
                return new MaterialDeleteOkhttpRequestExecutor(requestHttp);
            default:
                return null;
        }
    }
}
