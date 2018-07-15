package io.mykit.weixin.sdk.common.utils;

/**
 * @Author: liuyazhuang
 * @Date: 2018/7/15 22:32
 * @Description: 随机字符串工具类
 */

public class RandomUtils {
    private static final String RANDOM_STR = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";

    private static final java.util.Random RANDOM = new java.util.Random();

    /**
     * 生成默认长度的随机字符串，默认为16位
     * @return 生成的结果字符串
     */
    public static String getRandomStr(){
        return getRandomStr(16);
    }
    /**
     * 生成指定位数的随机字符串
     * @param num 待生成的字符串长度
     * @return 生成的结果字符串
     */
    public static String getRandomStr(int num){
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < num; i++){
            sb.append(RANDOM_STR.charAt(RANDOM.nextInt(RANDOM_STR.length())));
        }
        return sb.toString();
    }
}
