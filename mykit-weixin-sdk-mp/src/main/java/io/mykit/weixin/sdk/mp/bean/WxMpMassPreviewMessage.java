package io.mykit.weixin.sdk.mp.bean;

import io.mykit.weixin.sdk.common.api.WxConsts;
import io.mykit.weixin.sdk.mp.utils.json.WxMpGsonBuilder;
import lombok.Data;

import java.io.Serializable;

/**
 * @author liuyazhuang 
 */
@Data
public class WxMpMassPreviewMessage implements Serializable {
  private static final long serialVersionUID = 9095211638358424020L;

  private String toWxUserName;
  private String toWxUserOpenid;
  /**
   * <pre>
   * 消息类型
   * 请使用
   * {@link WxConsts.MassMsgType#IMAGE}
   * {@link WxConsts.MassMsgType#MPNEWS}
   * {@link WxConsts.MassMsgType#TEXT}
   * {@link WxConsts.MassMsgType#MPVIDEO}
   * {@link WxConsts.MassMsgType#VOICE}
   * 如果msgtype和media_id不匹配的话，会返回系统繁忙的错误
   * </pre>
   */
  private String msgType;
  private String content;
  private String mediaId;

  public WxMpMassPreviewMessage() {
    super();
  }

  public String toJson() {
    return WxMpGsonBuilder.INSTANCE.create().toJson(this);
  }
}
