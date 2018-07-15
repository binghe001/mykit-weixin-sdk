package io.mykit.weixin.sdk.common.utils.json;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import io.mykit.weixin.sdk.common.bean.WxAccessToken;
import io.mykit.weixin.sdk.common.bean.menu.WxMenu;
import io.mykit.weixin.sdk.common.bean.result.WxMediaUploadResult;
import io.mykit.weixin.sdk.common.error.WxError;

/**
 * @Author: liuyazhuang
 * @Date: 2018/7/15 22:50
 * @Description: 构建微信json字符串的工具类
 */

public class WxGsonBuilder {
    /**
     * GsonBuilder实例
     */
    public static final GsonBuilder INSTANCE = new GsonBuilder();

    static {
        INSTANCE.disableHtmlEscaping();
        INSTANCE.registerTypeAdapter(WxAccessToken.class, new WxAccessTokenAdapter());
        INSTANCE.registerTypeAdapter(WxError.class, new WxErrorAdapter());
        INSTANCE.registerTypeAdapter(WxMenu.class, new WxMenuGsonAdapter());
        INSTANCE.registerTypeAdapter(WxMediaUploadResult.class, new WxMediaUploadResultAdapter());
    }

    /**
     * 构建Gson对象
     * @return 返回Gson对象
     */
    public static Gson create(){
        return INSTANCE.create();
    }

}
