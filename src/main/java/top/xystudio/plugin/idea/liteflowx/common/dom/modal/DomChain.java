package top.xystudio.plugin.idea.liteflowx.common.dom.modal;

import com.intellij.util.xml.Attribute;
import com.intellij.util.xml.DomElement;
import com.intellij.util.xml.GenericAttributeValue;

public interface DomChain extends DomElement {

    @Attribute("id")
    GenericAttributeValue<String> getId();

    @Attribute("name")
    GenericAttributeValue<String> getName();

}
