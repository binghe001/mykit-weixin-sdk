package io.mykit.weixin.sdk.common.utils.http;

import org.apache.commons.lang3.StringUtils;

import java.io.UnsupportedEncodingException;

/**
 * @Author: liuyazhuang
 * @Date: 2018/7/16 11:27
 * @Description: URI工具类
 */

public class URIUtil {
    private static final String ALLOWED_CHARS = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789-_.!~*'()";

    /**
     * 对输入的字符串进行encode编码
     * @param input 输入的字符串(URI地址)
     * @return 编码后的字符串
     */
    public static String encodeURIComponent(String input) {
        if (StringUtils.isEmpty(input)) {
            return input;
        }

        int l = input.length();
        StringBuilder o = new StringBuilder(l * 3);
        try {
            for (int i = 0; i < l; i++) {
                String e = input.substring(i, i + 1);
                if (ALLOWED_CHARS.indexOf(e) == -1) {
                    byte[] b = e.getBytes("utf-8");
                    o.append(getHex(b));
                    continue;
                }
                o.append(e);
            }
            return o.toString();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return input;
    }

    /**
     * 获取指定字节数组的Hex
     * @param buf 字节数组
     * @return hex字符串
     */
    private static String getHex(byte buf[]) {
        StringBuilder o = new StringBuilder(buf.length * 3);
        for (int i = 0; i < buf.length; i++) {
            int n = buf[i] & 0xff;
            o.append("%");
            if (n < 0x10) {
                o.append("0");
            }
            o.append(Long.toString(n, 16).toUpperCase());
        }
        return o.toString();
    }
}
