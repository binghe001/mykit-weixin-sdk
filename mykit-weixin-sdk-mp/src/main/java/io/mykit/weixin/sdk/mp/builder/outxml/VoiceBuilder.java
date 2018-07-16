package io.mykit.weixin.sdk.mp.builder.outxml;

import io.mykit.weixin.sdk.mp.bean.message.WxMpXmlOutVoiceMessage;

/**
 * 语音消息builder
 *
 * @author liuyazhuang
 */
public final class VoiceBuilder extends BaseBuilder<VoiceBuilder, WxMpXmlOutVoiceMessage> {

  private String mediaId;

  public VoiceBuilder mediaId(String mediaId) {
    this.mediaId = mediaId;
    return this;
  }

  @Override
  public WxMpXmlOutVoiceMessage build() {
    WxMpXmlOutVoiceMessage m = new WxMpXmlOutVoiceMessage();
    setCommon(m);
    m.setMediaId(this.mediaId);
    return m;
  }

}
