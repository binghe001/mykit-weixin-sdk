package io.mykit.weixin.sdk.mp.utils.json;

import com.google.gson.*;
import io.mykit.weixin.sdk.common.utils.json.GsonHelper;
import io.mykit.weixin.sdk.mp.bean.result.WxMpUserBlacklistGetResult;

import java.lang.reflect.Type;

/**
 * @author liuyazhuang
 */
public class WxUserBlacklistGetResultGsonAdapter implements JsonDeserializer<WxMpUserBlacklistGetResult> {
  @Override
  public WxMpUserBlacklistGetResult deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
    JsonObject o = json.getAsJsonObject();
    WxMpUserBlacklistGetResult wxMpUserBlacklistGetResult = new WxMpUserBlacklistGetResult();
    wxMpUserBlacklistGetResult.setTotal(GsonHelper.getInteger(o, "total"));
    wxMpUserBlacklistGetResult.setCount(GsonHelper.getInteger(o, "count"));
    wxMpUserBlacklistGetResult.setNextOpenid(GsonHelper.getString(o, "next_openid"));
    if (o.get("data") != null && !o.get("data").isJsonNull() && !o.get("data").getAsJsonObject().get("openid").isJsonNull()) {
      JsonArray data = o.get("data").getAsJsonObject().get("openid").getAsJsonArray();
      for (int i = 0; i < data.size(); i++) {
        wxMpUserBlacklistGetResult.getOpenidList().add(GsonHelper.getAsString(data.get(i)));
      }
    }
    return wxMpUserBlacklistGetResult;
  }
}
