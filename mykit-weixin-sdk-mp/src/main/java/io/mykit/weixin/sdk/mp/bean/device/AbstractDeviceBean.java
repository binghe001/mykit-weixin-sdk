package io.mykit.weixin.sdk.mp.bean.device;

import io.mykit.weixin.sdk.common.utils.json.WxGsonBuilder;

import java.io.Serializable;

/**
 * 设备抽象类.
 *
 * @author liuyazhuang
 * @date 14/12/2016
 */
public abstract class AbstractDeviceBean implements Serializable {
  private static final long serialVersionUID = 4359729626772515385L;

  public String toJson() {
    return WxGsonBuilder.create().toJson(this);
  }
}
