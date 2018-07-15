package io.mykit.weixin.sdk.common.bean;

import io.mykit.weixin.sdk.common.utils.json.WxGsonBuilder;
import lombok.Data;

import java.io.Serializable;

/**
 * @Author: liuyazhuang
 * @Date: 2018/7/15 22:52
 * @Description: 微信access_token
 */
@Data
public class WxAccessToken implements Serializable {
    private static final long serialVersionUID = 6060904211511893339L;
    /**
     * 微信返回的access_token
     */
    private String accessToken;
    /**
     * access_token失效时长
     */
    private long expiresIn = -1;

    public static WxAccessToken fromJson(String json){
        return WxGsonBuilder.create().fromJson(json, WxAccessToken.class);
    }
}
