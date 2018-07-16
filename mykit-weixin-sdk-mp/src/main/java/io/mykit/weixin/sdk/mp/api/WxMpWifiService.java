package io.mykit.weixin.sdk.mp.api;

import io.mykit.weixin.sdk.common.error.WxErrorException;
import io.mykit.weixin.sdk.mp.bean.wifi.WxMpWifiShopListResult;

/**
 * <pre>
 *  微信连接WI-FI接口.
 * </pre>
 *
 * @author liuyazhuang 
 */
public interface WxMpWifiService {
  /**
   * <pre>
   * 获取Wi-Fi门店列表.
   * 通过此接口获取WiFi的门店列表，该列表包括公众平台的门店信息、以及添加设备后的WiFi相关信息。创建门店方法请参考“微信门店接口”。
   * 注：微信连Wi-Fi下的所有接口中的shop_id，必需先通过此接口获取。
   *
   * http请求方式: POST
   * 请求URL：https://api.weixin.qq.com/bizwifi/shop/list?access_token=ACCESS_TOKEN
   * </pre>
   *  @param pageIndex 分页下标，默认从1开始
   * @param pageSize  每页的个数，默认10个，最大20个
   */
  WxMpWifiShopListResult listShop(int pageIndex, int pageSize) throws WxErrorException;
}
