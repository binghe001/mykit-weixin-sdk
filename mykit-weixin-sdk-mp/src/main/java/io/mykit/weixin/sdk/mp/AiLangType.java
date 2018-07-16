package io.mykit.weixin.sdk.mp;

import lombok.Getter;

/**
 * @Author: liuyazhuang
 * @Date: 2018/7/16 15:10
 * @Description: AI开放接口里的语言类型，目前只有中文和英文
 */
@Getter
public enum AiLangType {
    /**
     * 中文 汉语
     */
    zh_CN("zh_CN"),
    /**
     * 英文 英语
     */
    en_US("en_US");

    private String code;

    AiLangType(String code) {
        this.code = code;
    }
}
