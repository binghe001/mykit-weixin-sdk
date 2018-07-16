package io.mykit.weixin.sdk.mp.bean.result;

import lombok.Data;
import io.mykit.weixin.sdk.common.utils.ToStringUtils;
import io.mykit.weixin.sdk.mp.utils.json.WxMpGsonBuilder;

import java.io.Serializable;

/**
 * <pre>
 * 上传群发用的素材的结果
 * 视频和图文消息需要在群发前上传素材
 * </pre>
 *
 * @author liuyazhuang
 */
@Data
public class WxMpMassUploadResult implements Serializable {
  private static final long serialVersionUID = 6568157943644994029L;

  private String type;
  private String mediaId;
  private long createdAt;

  public static WxMpMassUploadResult fromJson(String json) {
    return WxMpGsonBuilder.create().fromJson(json, WxMpMassUploadResult.class);
  }

  @Override
  public String toString() {
    return ToStringUtils.toSimpleString(this);
  }

}
