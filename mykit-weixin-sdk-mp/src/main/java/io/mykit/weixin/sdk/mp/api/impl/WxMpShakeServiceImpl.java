package io.mykit.weixin.sdk.mp.api.impl;

import io.mykit.weixin.sdk.common.error.WxError;
import io.mykit.weixin.sdk.common.error.WxErrorException;
import io.mykit.weixin.sdk.common.type.WxType;
import io.mykit.weixin.sdk.mp.api.WxMpService;
import io.mykit.weixin.sdk.mp.api.WxMpShakeService;
import io.mykit.weixin.sdk.mp.bean.WxMpShakeInfoResult;
import io.mykit.weixin.sdk.mp.bean.WxMpShakeQuery;
import io.mykit.weixin.sdk.mp.bean.shake.*;

/**
 *
 * @author liuyazhuang
 */
public class WxMpShakeServiceImpl implements WxMpShakeService {

  private WxMpService wxMpService;

  public WxMpShakeServiceImpl(WxMpService wxMpService) {
    this.wxMpService = wxMpService;
  }

  /**
   * <pre>
   * 获取设备及用户信息<br/>
   * 获取设备信息，包括UUID、major、minor，以及距离、openID等信息。
   * 详情请见: https://mp.weixin.qq.com/wiki?action=doc&id=mp1443447963
   * http请求方式: POST（请使用https协议）
   * 接口地址：https://api.weixin.qq.com/shakearound/user/getshakeinfo?access_token=ACCESS_TOKE
   * </pre>
   *
   * @param wxMpShakeQuery 查询参数
   */
  @Override
  public WxMpShakeInfoResult getShakeInfo(WxMpShakeQuery wxMpShakeQuery) throws WxErrorException {
    String url = "https://api.weixin.qq.com/shakearound/user/getshakeinfo";
    String postData = wxMpShakeQuery.toJsonString();
    String responseContent = this.wxMpService.post(url, postData);
    return WxMpShakeInfoResult.fromJson(responseContent);
  }

  @Override
  public WxMpShakeAroundPageAddResult pageAdd(WxMpShakeAroundPageAddQuery shakeAroundPageAddQuery) throws WxErrorException {
    String url = "https://api.weixin.qq.com/shakearound/page/add";
    String postData = shakeAroundPageAddQuery.toJsonString();
    String responseContent = this.wxMpService.post(url, postData);
    return WxMpShakeAroundPageAddResult.fromJson(responseContent);
  }

  @Override
  public WxError deviceBindPageQuery(WxMpShakeAroundDeviceBindPageQuery shakeAroundDeviceBindPageQuery) throws WxErrorException {
    String url = "https://api.weixin.qq.com/shakearound/device/bindpage";
    String postData = shakeAroundDeviceBindPageQuery.toJsonString();
    String responseContent = this.wxMpService.post(url, postData);
    return WxError.fromJson(responseContent, WxType.MP);
  }

  @Override
  public WxMpShakeAroundRelationSearchResult relationSearch(WxMpShakeAroundRelationSearchQuery shakeAroundRelationSearchQuery) throws WxErrorException {
    String url = "https://api.weixin.qq.com/shakearound/relation/search";
    String postData = shakeAroundRelationSearchQuery.toJsonString();
    String responseContent = this.wxMpService.post(url, postData);
    return WxMpShakeAroundRelationSearchResult.fromJson(responseContent);
  }
}
