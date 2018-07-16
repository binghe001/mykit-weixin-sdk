package io.mykit.weixin.sdk.common.utils.crypto;

import java.util.ArrayList;

/**
 * @Author: liuyazhuang
 * @Date: 2018/7/16 12:59
 * @Description: Byte操作
 */

public class ByteGroup {
    ArrayList<Byte> byteContainer = new ArrayList<>();

    public byte[] toBytes() {
        byte[] bytes = new byte[this.byteContainer.size()];
        for (int i = 0; i < this.byteContainer.size(); i++) {
            bytes[i] = this.byteContainer.get(i);
        }
        return bytes;
    }

    public ByteGroup addBytes(byte[] bytes) {
        for (byte b : bytes) {
            this.byteContainer.add(b);
        }
        return this;
    }

    public int size() {
        return this.byteContainer.size();
    }
}
