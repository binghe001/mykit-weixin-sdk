package io.mykit.weixin.sdk.common.utils.json;

import com.google.gson.*;
import io.mykit.weixin.sdk.common.error.WxError;

import java.lang.reflect.Type;

/**
 * @Author: liuyazhuang
 * @Date: 2018/7/15 22:59
 * @Description: 微信异常信息的Adapter类
 */

public class WxErrorAdapter implements JsonDeserializer<WxError> {
    @Override
    public WxError deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
        WxError.WxErrorBuilder errorBuilder = WxError.builder();
        JsonObject wxErrorJsonObject = json.getAsJsonObject();
        if (wxErrorJsonObject.get("errcode") != null && !wxErrorJsonObject.get("errcode").isJsonNull()) {
            errorBuilder.errorCode(GsonHelper.getAsPrimitiveInt(wxErrorJsonObject.get("errcode")));
        }
        if (wxErrorJsonObject.get("errmsg") != null && !wxErrorJsonObject.get("errmsg").isJsonNull()) {
            errorBuilder.errorMsg(GsonHelper.getAsString(wxErrorJsonObject.get("errmsg")));
        }

        errorBuilder.json(json.toString());

        return errorBuilder.build();
    }
}
