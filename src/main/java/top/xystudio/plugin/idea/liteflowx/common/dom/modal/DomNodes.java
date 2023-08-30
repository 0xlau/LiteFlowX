package top.xystudio.plugin.idea.liteflowx.common.dom.modal;

import com.intellij.util.xml.DomElement;
import com.intellij.util.xml.SubTagList;

import java.util.List;

public interface DomNodes extends DomElement {

    @SubTagList("node")
    List<DomNode> getNodeList();
}
