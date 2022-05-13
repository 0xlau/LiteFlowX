package top.xystudio.plugin.idea.liteflowx.dom.description;

import com.intellij.util.xml.DomFileDescription;
import top.xystudio.plugin.idea.liteflowx.dom.modal.Flow;

public class FlowDescription extends DomFileDescription<Flow> {

    public FlowDescription() {
        super(Flow.class, "flow");
    }

}
