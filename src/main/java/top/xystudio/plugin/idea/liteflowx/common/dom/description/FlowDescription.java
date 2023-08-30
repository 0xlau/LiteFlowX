package top.xystudio.plugin.idea.liteflowx.common.dom.description;

import com.intellij.util.xml.DomFileDescription;
import top.xystudio.plugin.idea.liteflowx.common.dom.modal.DomFlow;

public class FlowDescription extends DomFileDescription<DomFlow> {

    public FlowDescription() {
        super(DomFlow.class, "flow");
    }

}
