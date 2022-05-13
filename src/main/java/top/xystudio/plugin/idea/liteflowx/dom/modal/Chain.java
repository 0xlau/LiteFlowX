package top.xystudio.plugin.idea.liteflowx.dom.modal;

import com.intellij.util.xml.*;

import java.util.List;

public interface Chain extends DomElement {

    @SubTagList("pre")
    List<Pre> getPres();

    @SubTagList("finally")
    List<Finally> getFinallys();

    @SubTagList("then")
    List<Then> getThens();

    @SubTagList("when")
    List<When> getWhens();

    @Attribute("name")
    GenericAttributeValue<String> getName();

}
