package io.mykit.weixin.sdk.mp.utils.json;

import com.google.gson.*;
import io.mykit.weixin.sdk.common.utils.json.GsonHelper;
import io.mykit.weixin.sdk.mp.bean.material.WxMpMaterialVideoInfoResult;

import java.lang.reflect.Type;

/**
 * @author liuyazhuang
 */
public class WxMpMaterialVideoInfoResultAdapter implements JsonDeserializer<WxMpMaterialVideoInfoResult> {

    @Override
    public WxMpMaterialVideoInfoResult deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
        WxMpMaterialVideoInfoResult uploadResult = new WxMpMaterialVideoInfoResult();
        JsonObject uploadResultJsonObject = json.getAsJsonObject();

        if (uploadResultJsonObject.get("title") != null && !uploadResultJsonObject.get("title").isJsonNull()) {
            uploadResult.setTitle(GsonHelper.getAsString(uploadResultJsonObject.get("title")));
        }
        if (uploadResultJsonObject.get("description") != null && !uploadResultJsonObject.get("description").isJsonNull()) {
            uploadResult.setDescription(GsonHelper.getAsString(uploadResultJsonObject.get("description")));
        }
        if (uploadResultJsonObject.get("down_url") != null && !uploadResultJsonObject.get("down_url").isJsonNull()) {
            uploadResult.setDownUrl(GsonHelper.getAsString(uploadResultJsonObject.get("down_url")));
        }
        return uploadResult;
    }

}
