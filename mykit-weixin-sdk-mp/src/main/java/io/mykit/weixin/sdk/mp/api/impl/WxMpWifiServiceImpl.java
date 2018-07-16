package io.mykit.weixin.sdk.mp.api.impl;

import com.google.gson.JsonObject;
import io.mykit.weixin.sdk.common.error.WxErrorException;
import io.mykit.weixin.sdk.mp.api.WxMpService;
import io.mykit.weixin.sdk.mp.api.WxMpWifiService;
import io.mykit.weixin.sdk.mp.bean.wifi.WxMpWifiShopListResult;

/**
 * @author liuyazhuang 
 */
public class WxMpWifiServiceImpl implements WxMpWifiService {
  private WxMpService wxMpService;

  public WxMpWifiServiceImpl(WxMpService wxMpService) {
    this.wxMpService = wxMpService;
  }

  @Override
  public WxMpWifiShopListResult listShop(int pageIndex, int pageSize) throws WxErrorException {
    JsonObject json = new JsonObject();
    json.addProperty("pageindex", pageIndex);
    json.addProperty("pagesize", pageSize);
    final String result = this.wxMpService.post("https://api.weixin.qq.com/bizwifi/shop/list", json.toString());
    return WxMpWifiShopListResult.fromJson(result);
  }
}
