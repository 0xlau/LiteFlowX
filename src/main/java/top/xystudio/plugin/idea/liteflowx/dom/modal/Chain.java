package top.xystudio.plugin.idea.liteflowx.dom.modal;

import com.intellij.util.xml.Attribute;
import com.intellij.util.xml.DomElement;
import com.intellij.util.xml.GenericAttributeValue;

public interface Chain extends DomElement {

    @Attribute("id")
    GenericAttributeValue<String> getId();

    @Attribute("name")
    GenericAttributeValue<String> getName();

}
