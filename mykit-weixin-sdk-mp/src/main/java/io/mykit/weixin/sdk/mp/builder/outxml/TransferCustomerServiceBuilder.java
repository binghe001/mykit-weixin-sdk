package io.mykit.weixin.sdk.mp.builder.outxml;

import io.mykit.weixin.sdk.mp.bean.message.WxMpXmlOutTransferKefuMessage;
import org.apache.commons.lang3.StringUtils;

/**
 * 客服消息builder
 * <pre>
 * 用法: WxMpKefuMessage m = WxMpXmlOutMessage.TRANSFER_CUSTOMER_SERVICE().content(...).toUser(...).build();
 * </pre>
 *
 * @author liuyazhuang
 */
public final class TransferCustomerServiceBuilder extends BaseBuilder<TransferCustomerServiceBuilder, WxMpXmlOutTransferKefuMessage> {
  private String kfAccount;

  public TransferCustomerServiceBuilder kfAccount(String kf) {
    this.kfAccount = kf;
    return this;
  }

  @Override
  public WxMpXmlOutTransferKefuMessage build() {
    WxMpXmlOutTransferKefuMessage m = new WxMpXmlOutTransferKefuMessage();
    setCommon(m);
    if (StringUtils.isNotBlank(this.kfAccount)) {
      WxMpXmlOutTransferKefuMessage.TransInfo transInfo = new WxMpXmlOutTransferKefuMessage.TransInfo();
      transInfo.setKfAccount(this.kfAccount);
      m.setTransInfo(transInfo);
    }
    return m;
  }
}
