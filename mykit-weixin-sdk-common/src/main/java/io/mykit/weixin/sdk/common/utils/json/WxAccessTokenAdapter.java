package io.mykit.weixin.sdk.common.utils.json;

import com.google.gson.*;
import io.mykit.weixin.sdk.common.bean.WxAccessToken;

import java.lang.reflect.Type;

/**
 * @Author: liuyazhuang
 * @Date: 2018/7/15 23:18
 * @Description: WxAccessTokenAdapter
 */
public class WxAccessTokenAdapter implements JsonDeserializer<WxAccessToken> {
    @Override
    public WxAccessToken deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
        WxAccessToken accessToken = new WxAccessToken();
        JsonObject accessTokenJsonObject = json.getAsJsonObject();

        if (accessTokenJsonObject.get("access_token") != null && !accessTokenJsonObject.get("access_token").isJsonNull()) {
            accessToken.setAccessToken(GsonHelper.getAsString(accessTokenJsonObject.get("access_token")));
        }
        if (accessTokenJsonObject.get("expires_in") != null && !accessTokenJsonObject.get("expires_in").isJsonNull()) {
            accessToken.setExpiresIn(GsonHelper.getAsPrimitiveInt(accessTokenJsonObject.get("expires_in")));
        }
        return accessToken;
    }
}
