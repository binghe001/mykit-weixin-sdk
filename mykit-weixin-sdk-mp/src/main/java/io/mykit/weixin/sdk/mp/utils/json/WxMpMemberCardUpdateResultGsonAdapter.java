package io.mykit.weixin.sdk.mp.utils.json;

import com.google.gson.*;
import io.mykit.weixin.sdk.common.utils.json.GsonHelper;
import io.mykit.weixin.sdk.mp.bean.membercard.WxMpMemberCardUpdateResult;

import java.lang.reflect.Type;

/**
 * Json to WxMpMemberCardUpdateResult 的转换适配器
 *
 * @author liuyazhuang
 */
public class WxMpMemberCardUpdateResultGsonAdapter implements JsonDeserializer<WxMpMemberCardUpdateResult> {

    @Override
    public WxMpMemberCardUpdateResult deserialize(JsonElement jsonElement, Type type, JsonDeserializationContext
            jsonDeserializationContext) throws JsonParseException {

        WxMpMemberCardUpdateResult result = new WxMpMemberCardUpdateResult();

        JsonObject jsonObject = jsonElement.getAsJsonObject();

        result.setOpenId(GsonHelper.getString(jsonObject, "openid"));
        result.setErrorCode(GsonHelper.getString(jsonObject, "errcode"));
        result.setErrorMsg(GsonHelper.getString(jsonObject, "errmsg"));
        result.setResultBalance(GsonHelper.getDouble(jsonObject, "result_balance"));
        result.setResultBonus(GsonHelper.getInteger(jsonObject, "result_bonus"));

        return result;
    }
}
