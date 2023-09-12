package top.xystudio.plugin.idea.liteflowx.toolwindow.service;

import com.intellij.openapi.components.Service;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.wm.ToolWindow;
import org.jetbrains.annotations.NotNull;
import top.xystudio.plugin.idea.liteflowx.service.LiteFlowChainService;
import top.xystudio.plugin.idea.liteflowx.service.LiteFlowNodeService;
import top.xystudio.plugin.idea.liteflowx.toolwindow.view.CategoryPanel;
import top.xystudio.plugin.idea.liteflowx.toolwindow.view.NavigatePanel;

@Service(Service.Level.PROJECT)
public final class CategoryWindowService {

    private Project project;
    private ToolWindow toolWindow;
    private LiteFlowNodeService liteFlowNodeService;
    private LiteFlowChainService liteFlowChainService;
    private CategoryPanel categoryPanel;

    public CategoryWindowService(Project project){
        this.project = project;
        liteFlowNodeService = project.getService(LiteFlowNodeService.class);
        liteFlowChainService = project.getService(LiteFlowChainService.class);
    }

    public CategoryPanel initToolWindow(@NotNull ToolWindow toolWindow){
        this.toolWindow = toolWindow;
        this.categoryPanel = new CategoryPanel(this);
        return categoryPanel;
    }

}
