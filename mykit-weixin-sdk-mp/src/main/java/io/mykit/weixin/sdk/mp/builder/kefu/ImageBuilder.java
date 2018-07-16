package io.mykit.weixin.sdk.mp.builder.kefu;

import io.mykit.weixin.sdk.common.api.WxConsts;
import io.mykit.weixin.sdk.mp.bean.kefu.WxMpKefuMessage;

/**
 * 获得消息builder
 * <pre>
 * 用法: WxMpKefuMessage m = WxMpKefuMessage.IMAGE().mediaId(...).toUser(...).build();
 * </pre>
 *
 * @author liuyazhuang
 */
public final class ImageBuilder extends BaseBuilder<ImageBuilder> {
  private String mediaId;

  public ImageBuilder() {
    this.msgType = WxConsts.KefuMsgType.IMAGE;
  }

  public ImageBuilder mediaId(String media_id) {
    this.mediaId = media_id;
    return this;
  }

  @Override
  public WxMpKefuMessage build() {
    WxMpKefuMessage m = super.build();
    m.setMediaId(this.mediaId);
    return m;
  }
}
