package io.mykit.weixin.sdk.mp.bean.datacube;

import com.google.gson.JsonParser;
import com.google.gson.annotations.SerializedName;
import io.mykit.weixin.sdk.common.utils.ToStringUtils;
import lombok.Data;

import java.io.Serializable;

/**
 * <pre>
 *  统计接口的共用属性类.
 * </pre>
 *
 * @author liuyazhuang
 */
@Data
public abstract class WxDataCubeBaseResult implements Serializable {
  private static final long serialVersionUID = 8780389911053297600L;
  protected static final JsonParser JSON_PARSER = new JsonParser();

  /**
   * ref_date.
   * 数据的日期，需在begin_date和end_date之间
   */
  @SerializedName("ref_date")
  private String refDate;

  @Override
  public String toString() {
    return ToStringUtils.toSimpleString(this);
  }

}
