package top.xystudio.plugin.idea.liteflowx.dom.modal;

import com.intellij.util.xml.*;

import java.util.List;

public interface Chain extends DomElement {

    @Attribute("name")
    GenericAttributeValue<String> getName();

}
