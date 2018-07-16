package io.mykit.weixin.sdk.common.bean;

import io.mykit.weixin.sdk.common.utils.ToStringUtils;
import lombok.Data;

import java.io.Serializable;

/**
 * @Author: liuyazhuang
 * @Date: 2018/7/16 13:12
 * @Description: 微信Card API签名
 */
@Data
public class WxCardApiSignature implements Serializable {
    private static final long serialVersionUID = 158176707226975979L;

    private String appId;

    private String cardId;

    private String cardType;

    private String locationId;

    private String code;

    private String openId;

    private Long timestamp;

    private String nonceStr;

    private String signature;

    @Override
    public String toString() {
        return ToStringUtils.toSimpleString(this);
    }
}
