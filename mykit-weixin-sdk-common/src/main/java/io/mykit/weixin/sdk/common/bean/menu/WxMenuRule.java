package io.mykit.weixin.sdk.common.bean.menu;

import com.google.gson.annotations.SerializedName;
import io.mykit.weixin.sdk.common.utils.ToStringUtils;
import lombok.Data;

import java.io.Serializable;

/**
 * @Author: liuyazhuang
 * @Date: 2018/7/15 23:25
 * @Description: 微信菜单规则
 */
@Data
public class WxMenuRule implements Serializable {
    private static final long serialVersionUID = -353735541182178050L;
    /**
     * 变态的微信接口，反序列化时这里反人类的使用和序列化时不一样的名字.
     */
    @SerializedName(value = "tag_id", alternate = "group_id")
    private String tagId;
    private String sex;
    private String country;
    private String province;
    private String city;
    private String clientPlatformType;
    private String language;

    @Override
    public String toString() {
        return ToStringUtils.toSimpleString(this);
    }
}
