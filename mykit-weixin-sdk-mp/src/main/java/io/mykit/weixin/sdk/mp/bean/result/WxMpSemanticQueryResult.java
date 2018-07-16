package io.mykit.weixin.sdk.mp.bean.result;

import io.mykit.weixin.sdk.mp.utils.json.WxMpGsonBuilder;
import lombok.Data;

import java.io.Serializable;

/**
 * 语义理解查询结果对象
 * <p>
 * http://mp.weixin.qq.com/wiki/index.php?title=语义理解
 *
 * @author liuyazhuang
 */
@Data
public class WxMpSemanticQueryResult implements Serializable {
  private static final long serialVersionUID = 4811088544804441365L;

  private String query;
  private String type;
  private String semantic;
  private String result;
  private String answer;
  private String text;

  public static WxMpSemanticQueryResult fromJson(String json) {
    return WxMpGsonBuilder.create().fromJson(json, WxMpSemanticQueryResult.class);
  }

}
