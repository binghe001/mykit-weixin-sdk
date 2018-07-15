package io.mykit.weixin.sdk.common.error;

/**
 * @Author: liuyazhuang
 * @Date: 2018/7/15 22:38
 * @Description: 自定义异常类，主要封装微信异常
 */

public class WxErrorException extends Exception{

    private static final long serialVersionUID = 6834696957307044459L;

    private WxError error;

    public WxErrorException(WxError error){
        super(error.toString());
        this.error = error;
    }

    public WxErrorException(WxError error, Throwable cause){
        super(error.toString(), cause);
        this.error = error;
    }

    public WxError getError(){
        return this.error;
    }
}
