package top.xystudio.plugin.idea.liteflowx.dom.modal;

import com.intellij.util.xml.*;

public interface ComponentElement extends DomElement {

    @Required
    @NameValue
    @Attribute("value")
    GenericAttributeValue<String> getValue();
}
