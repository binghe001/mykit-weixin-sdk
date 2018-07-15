package io.mykit.weixin.sdk.common.bean.result;

import io.mykit.weixin.sdk.common.utils.ToStringUtils;
import io.mykit.weixin.sdk.common.utils.json.WxGsonBuilder;
import lombok.Data;

import java.io.Serializable;

/**
 * @Author: liuyazhuang
 * @Date: 2018/7/15 23:38
 * @Description: 微信多媒体上传结果
 */
@Data
public class WxMediaUploadResult implements Serializable {
    private static final long serialVersionUID = 17337759420098790L;
    private String type;
    private String mediaId;
    private String thumbMediaId;
    private long createAt;

    public static WxMediaUploadResult fromJson(String json) {
        return WxGsonBuilder.create().fromJson(json, WxMediaUploadResult.class);
    }

    @Override
    public String toString() {
        return ToStringUtils.toSimpleString(this);
    }

}
