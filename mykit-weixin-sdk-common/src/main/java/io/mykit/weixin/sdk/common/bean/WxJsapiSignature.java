package io.mykit.weixin.sdk.common.bean;

import lombok.Data;

import java.io.Serializable;

/**
 * @Author: liuyazhuang
 * @Date: 2018/7/16 13:13
 * @Description: WxJsapi签名
 */
@Data
public class WxJsapiSignature implements Serializable {
    private static final long serialVersionUID = 6355132347135512356L;

    private String appId;

    private String nonceStr;

    private long timestamp;

    private String url;

    private String signature;
}
