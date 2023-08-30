package top.xystudio.plugin.idea.liteflowx.common.dom.modal;

import com.intellij.util.xml.Attribute;
import com.intellij.util.xml.DomElement;
import com.intellij.util.xml.GenericAttributeValue;

public interface DomNode extends DomElement {

    @Attribute("id")
    GenericAttributeValue<String> getId();

    @Attribute("class")
    GenericAttributeValue<String> getClazz();

    @Attribute("name")
    GenericAttributeValue<String> getName();

    @Attribute("type")
    GenericAttributeValue<String> getType();

}
