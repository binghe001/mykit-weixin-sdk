package io.mykit.weixin.sdk.common.utils.http;

import org.apache.http.conn.DnsResolver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.HashMap;
import java.util.Map;

/**
 * @Author: liuyazhuang
 * @Date: 2018/7/16 11:16
 * @Description:
 *  微信DNS域名解析器，将微信域名绑定到指定IP
 *  --------------------------------------------
 *  适用于服务器端调用微信服务器需要开通出口防火墙情况
 *   <p>
 */

public class WxDnsResolver implements DnsResolver {

    private static final String WECHAT_API_URL = "api.weixin.qq.com";
    private static Map<String, InetAddress[]> MAPPINGS = new HashMap<String, InetAddress[]>();
    protected final Logger logger = LoggerFactory.getLogger(WxDnsResolver.class);
    private String wxApiIp;

    public WxDnsResolver(String ip){
        this.wxApiIp = ip;
        this.init();
    }

    private void init(){
        if (logger.isDebugEnabled()) {
            logger.debug("init wechat dns config with ip {}", wxApiIp);
        }
        try {
            MAPPINGS.put(WECHAT_API_URL, new InetAddress[]{InetAddress.getByName(wxApiIp)});
        } catch (UnknownHostException e) {
            //如果初始化DNS配置失败则使用默认配置,不影响服务的启动
            logger.error("init WxDnsResolver error", e);
            MAPPINGS = new HashMap<String, InetAddress[]>();
        }
    }

    @Override
    public InetAddress[] resolve(String s) {
        return MAPPINGS.containsKey(s) ? MAPPINGS.get(s) : new InetAddress[0];
    }

    public void setWxApiIp(String ip){
        this.wxApiIp = ip;
        this.init();
    }

    public String getWxApiIp(){
        return this.wxApiIp;
    }
}
