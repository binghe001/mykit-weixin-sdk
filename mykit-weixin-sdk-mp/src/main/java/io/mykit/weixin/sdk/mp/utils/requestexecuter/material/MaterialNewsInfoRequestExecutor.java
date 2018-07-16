package io.mykit.weixin.sdk.mp.utils.requestexecuter.material;

import io.mykit.weixin.sdk.common.utils.http.RequestExecutor;
import io.mykit.weixin.sdk.common.utils.http.RequestHttp;
import io.mykit.weixin.sdk.mp.bean.material.WxMpMaterialNews;

/**
 * @Author: liuyazhuang
 * @Date: 2018/7/16 15:26
 * @Description: 图文消息执行器的基础抽象类
 */

public abstract class MaterialNewsInfoRequestExecutor <H, P> implements RequestExecutor<WxMpMaterialNews, String> {
    protected RequestHttp<H, P> requestHttp;

    public MaterialNewsInfoRequestExecutor(RequestHttp requestHttp) {
        this.requestHttp = requestHttp;
    }

    public static RequestExecutor<WxMpMaterialNews, String> create(RequestHttp requestHttp) {
        switch (requestHttp.getRequestType()) {
            case APACHE_HTTP:
                return new MaterialNewsInfoApacheHttpRequestExecutor(requestHttp);
            case JODD_HTTP:
                return new MaterialNewsInfoJoddHttpRequestExecutor(requestHttp);
            case OK_HTTP:
                return new MaterialNewsInfoOkhttpRequestExecutor(requestHttp);
            default:
                //TODO 需要优化抛出异常
                return null;
        }
    }
}
