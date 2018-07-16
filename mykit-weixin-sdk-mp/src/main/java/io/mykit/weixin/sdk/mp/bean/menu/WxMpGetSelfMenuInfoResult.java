package io.mykit.weixin.sdk.mp.bean.menu;

import com.google.gson.annotations.SerializedName;
import io.mykit.weixin.sdk.common.utils.ToStringUtils;
import io.mykit.weixin.sdk.common.utils.json.WxGsonBuilder;
import lombok.Data;

import java.io.Serializable;

/**
 * <pre>
 * Created by Binary Wang on 2016-11-25.
 * </pre>
 *
 * @author liuyazhuang
 */
@Data
public class WxMpGetSelfMenuInfoResult implements Serializable {
  private static final long serialVersionUID = -5612495636936835166L;

  @SerializedName("selfmenu_info")
  private WxMpSelfMenuInfo selfMenuInfo;

  @SerializedName("is_menu_open")
  private Integer isMenuOpen;

  public static WxMpGetSelfMenuInfoResult fromJson(String json) {
    return WxGsonBuilder.create().fromJson(json, WxMpGetSelfMenuInfoResult.class);
  }

  @Override
  public String toString() {
    return ToStringUtils.toSimpleString(this);
  }

}
