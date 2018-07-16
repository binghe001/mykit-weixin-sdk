package io.mykit.weixin.sdk.mp.bean.membercard;

import io.mykit.weixin.sdk.common.utils.ToStringUtils;
import io.mykit.weixin.sdk.mp.utils.json.WxMpGsonBuilder;
import lombok.Data;

import java.io.Serializable;

/**
 * <pre>
 * 拉取会员信息返回的结果
 *
 * 字段格式参考https://mp.weixin.qq.com/wiki?t=resource/res_main&id=mp1451025283  6.2.1小节的步骤5
 * </pre>
 *
 * @author liuyazhuang
 * @version 2017/7/9
 */
@Data
public class WxMpMemberCardUserInfoResult implements Serializable {

  private static final long serialVersionUID = 9084777967442098311L;

  private String errorCode;

  private String errorMsg;

  private String openId;

  private String nickname;

  private String membershipNumber;

  private Integer bonus;

  private Double balance;

  private String sex;

  private MemberCardUserInfo userInfo;

  private String userCardStatus;

  private Boolean hasActive;

  @Override
  public String toString() {
    return ToStringUtils.toSimpleString(this);
  }

  public static WxMpMemberCardUserInfoResult fromJson(String json) {
    return WxMpGsonBuilder.INSTANCE.create().fromJson(json, WxMpMemberCardUserInfoResult.class);
  }
}

