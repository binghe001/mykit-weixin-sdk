package io.mykit.weixin.sdk.common.utils.xml;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.converters.reflection.PureJavaReflectionProvider;
import com.thoughtworks.xstream.core.util.QuickWriter;
import com.thoughtworks.xstream.io.HierarchicalStreamWriter;
import com.thoughtworks.xstream.io.xml.PrettyPrintWriter;
import com.thoughtworks.xstream.io.xml.XppDriver;
import com.thoughtworks.xstream.security.NullPermission;
import com.thoughtworks.xstream.security.PrimitiveTypePermission;

import java.io.Writer;

/**
 * @Author: liuyazhuang
 * @Date: 2018/7/16 10:35
 * @Description: XML初始化器
 */

public class XStreamInitializer {

    /**
     * 获取XStream实例
     * @return XStream实例
     */
    public static XStream getInstance(){
        XStream xstream = new XStream(new PureJavaReflectionProvider(), new XppDriver(){
            @Override
            public HierarchicalStreamWriter createWriter(Writer out){
                return new PrettyPrintWriter(out, getNameCoder()){
                    protected String PREFIX_CDATA = "<![CDATA[";
                    protected String SUFFIX_CDATA = "]]>" ;
                    protected String PREFIX_MEDIA_ID = "<MediaId>";
                    protected String SUFFIX_MEDIA_ID = "</MediaId>";

                    @Override
                    protected void writeText(QuickWriter writer, String text) {
                       if(text.startsWith(this.PREFIX_CDATA) && text.endsWith(this.SUFFIX_CDATA)){
                           writer.write(text);
                       }else if(text.startsWith(this.PREFIX_MEDIA_ID) && text.endsWith(this.SUFFIX_MEDIA_ID)){
                           writer.write(text);
                       }else{
                           super.writeText(writer, text);
                       }
                    }

                    @Override
                    public String encodeNode(String name) {
                        return name;
                    }
                };
            }
        });
        xstream.ignoreUnknownElements();;
        xstream.setMode(XStream.NO_REFERENCES);
        xstream.addPermission(NullPermission.NULL);
        xstream.addPermission(PrimitiveTypePermission.PRIMITIVES);
        xstream.setClassLoader(Thread.currentThread().getContextClassLoader());
        return xstream;
    }
}
