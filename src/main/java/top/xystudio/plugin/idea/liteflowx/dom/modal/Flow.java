package top.xystudio.plugin.idea.liteflowx.dom.modal;

import com.intellij.util.xml.DomElement;
import com.intellij.util.xml.SubTag;
import com.intellij.util.xml.SubTagList;

import java.util.List;

public interface Flow extends DomElement {

    @SubTagList("chain")
    List<Chain> getChains();

    @SubTag("nodes")
    Nodes getNodes();

}
