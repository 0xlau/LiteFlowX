package top.xystudio.plugin.idea.liteflowx.dom.modal;

import com.intellij.util.xml.DomElement;
import com.intellij.util.xml.SubTagList;

import java.util.List;

public interface Nodes extends DomElement {

    @SubTagList("node")
    List<Node> getNodeList();
}
