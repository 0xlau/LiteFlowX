package top.xystudio.plugin.idea.liteflowx.dom.modal;

import com.intellij.util.xml.Attribute;
import com.intellij.util.xml.DomElement;
import com.intellij.util.xml.GenericAttributeValue;

public interface Node extends DomElement {

    @Attribute("id")
    GenericAttributeValue<String> getId();

    @Attribute("class")
    GenericAttributeValue<String> getClassA();

    @Attribute("name")
    GenericAttributeValue<String> getName();


}
