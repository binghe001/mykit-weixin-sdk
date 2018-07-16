package io.mykit.weixin.sdk.mp.api;

import io.mykit.weixin.sdk.common.error.WxErrorException;
import io.mykit.weixin.sdk.common.session.WxSessionManager;
import io.mykit.weixin.sdk.mp.bean.message.WxMpXmlMessage;

import java.util.Map;

/**
 * 微信消息拦截器，可以用来做验证
 *
 * @author liuyazhuang 
 */
public interface WxMpMessageInterceptor {

  /**
   * 拦截微信消息
   *
   * @param wxMessage
   * @param context        上下文，如果handler或interceptor之间有信息要传递，可以用这个
   * @param wxMpService
   * @param sessionManager
   * @return true代表OK，false代表不OK
   */
  boolean intercept(WxMpXmlMessage wxMessage,
                    Map<String, Object> context,
                    WxMpService wxMpService,
                    WxSessionManager sessionManager) throws WxErrorException;

}
