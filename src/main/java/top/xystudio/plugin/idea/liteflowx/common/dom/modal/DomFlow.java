package top.xystudio.plugin.idea.liteflowx.common.dom.modal;

import com.intellij.util.xml.DomElement;
import com.intellij.util.xml.SubTag;
import com.intellij.util.xml.SubTagList;

import java.util.List;

public interface DomFlow extends DomElement {

    @SubTagList("chain")
    List<DomChain> getChains();

    @SubTag("nodes")
    DomNodes getNodes();

}
