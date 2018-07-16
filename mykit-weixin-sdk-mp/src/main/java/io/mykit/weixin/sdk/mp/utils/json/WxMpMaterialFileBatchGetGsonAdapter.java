package io.mykit.weixin.sdk.mp.utils.json;

import com.google.gson.*;
import io.mykit.weixin.sdk.common.utils.json.GsonHelper;
import io.mykit.weixin.sdk.mp.bean.material.WxMpMaterialFileBatchGetResult;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

/**
 * @author liuyazhuang
 */
public class WxMpMaterialFileBatchGetGsonAdapter implements JsonDeserializer<WxMpMaterialFileBatchGetResult> {

    @Override
    public WxMpMaterialFileBatchGetResult deserialize(JsonElement jsonElement, Type type, JsonDeserializationContext jsonDeserializationContext) throws JsonParseException {
        WxMpMaterialFileBatchGetResult wxMpMaterialFileBatchGetResult = new WxMpMaterialFileBatchGetResult();
        JsonObject json = jsonElement.getAsJsonObject();
        if (json.get("total_count") != null && !json.get("total_count").isJsonNull()) {
            wxMpMaterialFileBatchGetResult.setTotalCount(GsonHelper.getAsInteger(json.get("total_count")));
        }
        if (json.get("item_count") != null && !json.get("item_count").isJsonNull()) {
            wxMpMaterialFileBatchGetResult.setItemCount(GsonHelper.getAsInteger(json.get("item_count")));
        }
        if (json.get("item") != null && !json.get("item").isJsonNull()) {
            JsonArray item = json.getAsJsonArray("item");
            List<WxMpMaterialFileBatchGetResult.WxMaterialFileBatchGetNewsItem> items = new ArrayList<>();
            for (JsonElement anItem : item) {
                JsonObject articleInfo = anItem.getAsJsonObject();
                items.add(WxMpGsonBuilder.create().fromJson(articleInfo, WxMpMaterialFileBatchGetResult.WxMaterialFileBatchGetNewsItem.class));
            }
            wxMpMaterialFileBatchGetResult.setItems(items);
        }
        return wxMpMaterialFileBatchGetResult;
    }
}
