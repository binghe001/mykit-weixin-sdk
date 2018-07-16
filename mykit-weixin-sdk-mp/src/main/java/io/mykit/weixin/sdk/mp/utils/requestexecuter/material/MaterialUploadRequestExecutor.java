package io.mykit.weixin.sdk.mp.utils.requestexecuter.material;

import io.mykit.weixin.sdk.common.utils.http.RequestExecutor;
import io.mykit.weixin.sdk.common.utils.http.RequestHttp;
import io.mykit.weixin.sdk.mp.bean.material.WxMpMaterial;
import io.mykit.weixin.sdk.mp.bean.material.WxMpMaterialUploadResult;

/**
 * @Author: liuyazhuang
 * @Date: 2018/7/16 15:31
 * @Description: 上传操作基础抽象类
 */

public abstract class MaterialUploadRequestExecutor <H, P> implements RequestExecutor<WxMpMaterialUploadResult, WxMpMaterial> {
    protected RequestHttp<H, P> requestHttp;

    public MaterialUploadRequestExecutor(RequestHttp requestHttp) {
        this.requestHttp = requestHttp;
    }

    public static RequestExecutor<WxMpMaterialUploadResult, WxMpMaterial> create(RequestHttp requestHttp) {
        switch (requestHttp.getRequestType()) {
            case APACHE_HTTP:
                return new MaterialUploadApacheHttpRequestExecutor(requestHttp);
            case JODD_HTTP:
                return new MaterialUploadJoddHttpRequestExecutor(requestHttp);
            case OK_HTTP:
                return new MaterialUploadOkhttpRequestExecutor(requestHttp);
            default:
                return null;
        }
    }
}
