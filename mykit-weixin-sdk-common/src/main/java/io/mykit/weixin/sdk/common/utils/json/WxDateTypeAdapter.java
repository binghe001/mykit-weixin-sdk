package io.mykit.weixin.sdk.common.utils.json;

import com.google.gson.JsonParseException;
import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonToken;
import com.google.gson.stream.JsonWriter;

import java.io.IOException;
import java.util.Date;

/**
 * @Author: liuyazhuang
 * @Date: 2018/7/16 11:08
 * @Description: 日期类型转换器
 */

public class WxDateTypeAdapter extends TypeAdapter<Date> {
    @Override
    public void write(JsonWriter out, Date value) throws IOException {
        if(value == null){
            out.nullValue();
        }else{
            out.value(value.getTime() / 1000);
        }
    }

    @Override
    public Date read(JsonReader in) throws IOException {
        JsonToken peek = in.peek();
        switch (peek){
            case NULL:
                in.nextNull();
                return null;
            case NUMBER:
                return new Date(in.nextInt() * 1000);
            default:
                throw new JsonParseException("Expected NUMBER but was " + peek);
        }
    }
}
