package io.mykit.weixin.sdk.mp.api.impl;

import com.google.gson.JsonObject;
import io.mykit.weixin.sdk.common.error.WxErrorException;
import io.mykit.weixin.sdk.mp.api.WxMpService;
import io.mykit.weixin.sdk.mp.api.WxMpUserService;
import io.mykit.weixin.sdk.mp.bean.WxMpUserQuery;
import io.mykit.weixin.sdk.mp.bean.result.WxMpUser;
import io.mykit.weixin.sdk.mp.bean.result.WxMpUserList;

import java.util.List;
public class WxMpUserServiceImpl implements WxMpUserService {
  private static final String API_URL_PREFIX = "https://api.weixin.qq.com/cgi-bin/user";
  private WxMpService wxMpService;

  public WxMpUserServiceImpl(WxMpService wxMpService) {
    this.wxMpService = wxMpService;
  }

  @Override
  public void userUpdateRemark(String openid, String remark) throws WxErrorException {
    String url = API_URL_PREFIX + "/info/updateremark";
    JsonObject json = new JsonObject();
    json.addProperty("openid", openid);
    json.addProperty("remark", remark);
    this.wxMpService.post(url, json.toString());
  }

  @Override
  public WxMpUser userInfo(String openid) throws WxErrorException {
    return this.userInfo(openid, null);
  }

  @Override
  public WxMpUser userInfo(String openid, String lang) throws WxErrorException {
    String url = API_URL_PREFIX + "/info";
    lang = lang == null ? "zh_CN" : lang;
    String responseContent = this.wxMpService.get(url,
      "openid=" + openid + "&lang=" + lang);
    return WxMpUser.fromJson(responseContent);
  }

  @Override
  public WxMpUserList userList(String next_openid) throws WxErrorException {
    String url = API_URL_PREFIX + "/get";
    String responseContent = this.wxMpService.get(url,
      next_openid == null ? null : "next_openid=" + next_openid);
    return WxMpUserList.fromJson(responseContent);
  }

  @Override
  public List<WxMpUser> userInfoList(List<String> openids)
    throws WxErrorException {
    return this.userInfoList(new WxMpUserQuery(openids));
  }

  @Override
  public List<WxMpUser> userInfoList(WxMpUserQuery userQuery) throws WxErrorException {
    String url = API_URL_PREFIX + "/info/batchget";
    String responseContent = this.wxMpService.post(url,
      userQuery.toJsonString());
    return WxMpUser.fromJsonList(responseContent);
  }

}
