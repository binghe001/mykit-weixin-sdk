package io.mykit.weixin.sdk.mp.bean.device;

import com.google.gson.annotations.SerializedName;
import io.mykit.weixin.sdk.common.utils.json.WxGsonBuilder;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 *
 * @author liuyazhuang
 * @date 14/12/2016
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class TransMsgResp extends AbstractDeviceBean {
  private static final long serialVersionUID = 5386954916622816491L;

  private Integer ret;
  @SerializedName("ret_info")
  private String retInfo;
  @SerializedName("errcode")
  private Integer errCode;
  @SerializedName("errmsg")
  private String errMsg;

  public static TransMsgResp fromJson(String json) {
    return WxGsonBuilder.create().fromJson(json, TransMsgResp.class);
  }
}
