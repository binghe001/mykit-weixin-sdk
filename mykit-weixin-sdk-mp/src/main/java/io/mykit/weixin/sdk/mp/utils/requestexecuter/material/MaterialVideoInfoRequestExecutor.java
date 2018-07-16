package io.mykit.weixin.sdk.mp.utils.requestexecuter.material;

import io.mykit.weixin.sdk.common.utils.http.RequestExecutor;
import io.mykit.weixin.sdk.common.utils.http.RequestHttp;
import io.mykit.weixin.sdk.mp.bean.material.WxMpMaterialVideoInfoResult;

/**
 * @Author: liuyazhuang
 * @Date: 2018/7/16 15:36
 * @Description:  视频操作基础抽象执行器类
 */

public abstract class MaterialVideoInfoRequestExecutor <H, P> implements RequestExecutor<WxMpMaterialVideoInfoResult, String> {
    protected RequestHttp<H, P> requestHttp;

    public MaterialVideoInfoRequestExecutor(RequestHttp requestHttp) {
        this.requestHttp = requestHttp;
    }

    public static RequestExecutor<WxMpMaterialVideoInfoResult, String> create(RequestHttp requestHttp) {
        switch (requestHttp.getRequestType()) {
            case APACHE_HTTP:
                return new MaterialVideoInfoApacheHttpRequestExecutor(requestHttp);
            case JODD_HTTP:
                return new MaterialVideoInfoJoddHttpRequestExecutor(requestHttp);
            case OK_HTTP:
                return new MaterialVideoInfoOkhttpRequestExecutor(requestHttp);
            default:
                return null;
        }
    }
}
