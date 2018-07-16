package io.mykit.weixin.sdk.mp.utils.json;

import com.google.gson.*;
import io.mykit.weixin.sdk.common.utils.json.GsonHelper;
import io.mykit.weixin.sdk.mp.bean.material.WxMediaImgUploadResult;

import java.lang.reflect.Type;

/**
 * @author liuyazhuang
 */
public class WxMediaImgUploadResultGsonAdapter implements JsonDeserializer<WxMediaImgUploadResult> {
    @Override
    public WxMediaImgUploadResult deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
        WxMediaImgUploadResult wxMediaImgUploadResult = new WxMediaImgUploadResult();
        JsonObject jsonObject = json.getAsJsonObject();
        if (null != jsonObject.get("url") && !jsonObject.get("url").isJsonNull()) {
            wxMediaImgUploadResult.setUrl(GsonHelper.getAsString(jsonObject.get("url")));
        }
        return wxMediaImgUploadResult;
    }
}
