package io.mykit.weixin.sdk.mp.api.impl;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.reflect.TypeToken;
import io.mykit.weixin.sdk.common.error.WxError;
import io.mykit.weixin.sdk.common.error.WxErrorException;
import io.mykit.weixin.sdk.common.type.WxType;
import io.mykit.weixin.sdk.common.utils.BeanUtils;
import io.mykit.weixin.sdk.mp.api.WxMpService;
import io.mykit.weixin.sdk.mp.api.WxMpStoreService;
import io.mykit.weixin.sdk.mp.bean.store.WxMpStoreBaseInfo;
import io.mykit.weixin.sdk.mp.bean.store.WxMpStoreInfo;
import io.mykit.weixin.sdk.mp.bean.store.WxMpStoreListResult;
import io.mykit.weixin.sdk.mp.utils.json.WxMpGsonBuilder;

import java.util.List;

/**
 * @author liuyazhuang
 */
public class WxMpStoreServiceImpl implements WxMpStoreService {
  private WxMpService wxMpService;

  public WxMpStoreServiceImpl(WxMpService wxMpService) {
    this.wxMpService = wxMpService;
  }

  @Override
  public void add(WxMpStoreBaseInfo request) throws WxErrorException {
    BeanUtils.checkRequiredFields(request);

    String response = this.wxMpService.post(POI_ADD_URL, request.toJson());
    WxError wxError = WxError.fromJson(response, WxType.MP);
    if (wxError.getErrorCode() != 0) {
      throw new WxErrorException(wxError);
    }
  }

  @Override
  public WxMpStoreBaseInfo get(String poiId) throws WxErrorException {
    JsonObject paramObject = new JsonObject();
    paramObject.addProperty("poi_id", poiId);
    String response = this.wxMpService.post(POI_GET_URL, paramObject.toString());
    WxError wxError = WxError.fromJson(response, WxType.MP);
    if (wxError.getErrorCode() != 0) {
      throw new WxErrorException(wxError);
    }
    return WxMpStoreBaseInfo.fromJson(new JsonParser().parse(response).getAsJsonObject()
      .get("business").getAsJsonObject().get("base_info").toString());
  }

  @Override
  public void delete(String poiId) throws WxErrorException {
    JsonObject paramObject = new JsonObject();
    paramObject.addProperty("poi_id", poiId);
    String response = this.wxMpService.post(POI_DEL_URL, paramObject.toString());
    WxError wxError = WxError.fromJson(response, WxType.MP);
    if (wxError.getErrorCode() != 0) {
      throw new WxErrorException(wxError);
    }
  }

  @Override
  public WxMpStoreListResult list(int begin, int limit)
    throws WxErrorException {
    JsonObject params = new JsonObject();
    params.addProperty("begin", begin);
    params.addProperty("limit", limit);
    String response = this.wxMpService.post(POI_LIST_URL, params.toString());

    WxError wxError = WxError.fromJson(response, WxType.MP);
    if (wxError.getErrorCode() != 0) {
      throw new WxErrorException(wxError);
    }

    return WxMpStoreListResult.fromJson(response);
  }

  @Override
  public List<WxMpStoreInfo> listAll() throws WxErrorException {
    int limit = 50;
    WxMpStoreListResult list = this.list(0, limit);
    List<WxMpStoreInfo> stores = list.getBusinessList();
    if (list.getTotalCount() > limit) {
      int begin = limit;
      WxMpStoreListResult followingList = this.list(begin, limit);
      while (followingList.getBusinessList().size() > 0) {
        stores.addAll(followingList.getBusinessList());
        begin += limit;
        if (begin >= list.getTotalCount()) {
          break;
        }
        followingList = this.list(begin, limit);
      }
    }

    return stores;
  }

  @Override
  public void update(WxMpStoreBaseInfo request) throws WxErrorException {
    String response = this.wxMpService.post(POI_UPDATE_URL, request.toJson());
    WxError wxError = WxError.fromJson(response, WxType.MP);
    if (wxError.getErrorCode() != 0) {
      throw new WxErrorException(wxError);
    }
  }

  @Override
  public List<String> listCategories() throws WxErrorException {
    String response = this.wxMpService.get(POI_GET_WX_CATEGORY_URL, null);
    WxError wxError = WxError.fromJson(response, WxType.MP);
    if (wxError.getErrorCode() != 0) {
      throw new WxErrorException(wxError);
    }

    return WxMpGsonBuilder.create().fromJson(
      new JsonParser().parse(response).getAsJsonObject().get("category_list"),
      new TypeToken<List<String>>() {
      }.getType());
  }

}
