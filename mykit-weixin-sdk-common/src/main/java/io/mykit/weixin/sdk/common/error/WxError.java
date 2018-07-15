package io.mykit.weixin.sdk.common.error;

import io.mykit.weixin.sdk.common.type.WxType;
import io.mykit.weixin.sdk.common.utils.json.WxGsonBuilder;
import lombok.Builder;
import lombok.Data;
import org.apache.commons.lang3.StringUtils;

import java.io.Serializable;

/**
 * @Author: liuyazhuang
 * @Date: 2018/7/15 22:40
 * @Description: 微信错误码
 *  请阅读：
 *  公众平台：<a href="http://mp.weixin.qq.com/wiki/10/6380dc743053a91c544ffd2b7c959166.html">全局返回码说明</a>
 *  企业微信：<a href="https://work.weixin.qq.com/api/doc#10649">全局错误码</a>
 */
@Data
@Builder
public class WxError implements Serializable {
    private static final long serialVersionUID = 3410002737807233548L;

    /**
     * 微信错误代码
     */
    private int errorCode;

    /**
     * 微信错误信息
     */
    private String errorMsg;

    /**
     * 微信接口返回的错误原始信息(英文)
     */
    private String errorMsgEn;

    /**
     * Json字符串
     */
    private String json;

    /**
     * 根据json字符串构建WxError对象
     * @param json json字符串
     * @return WxError对象
     */
    public static WxError fromJson(String json){
        return fromJson(json, null);
    }

    /**
     * 根据json字符串和WxType构建WxError对象
     * @param json json字符串
     * @param type WxType类型
     * @return WxError对象
     */
    public static WxError fromJson(String json, WxType type){
        final WxError wxError = WxGsonBuilder.create().fromJson(json, WxError.class);
        if(StringUtils.isNotEmpty(wxError.getErrorMsg())){
            wxError.setErrorMsg(wxError.getErrorMsg());
        }

        if(type == null){
            return wxError;
        }
        //微信公众号
        if(type == WxType.MP){
            final String msg = WxMpErrorMsgEnum.findMsgByCode(wxError.getErrorCode());
            if(msg != null){
                wxError.setErrorMsg(msg);
            }
        }else if(type == WxType.CP){    //微信企业号
            final String msg = WxCpErrorMsgEnum.findMsgByCode(wxError.getErrorCode());
            if(msg != null){
                wxError.setErrorMsg(msg);
            }
        }
        return wxError;
    }

    @Override
    public String toString(){
        if(this.json != null){
            return this.json;
        }
        return "错误: Code=" + this.errorCode + ", Msg=" + this.errorMsg;
    }
}
