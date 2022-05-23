package top.xystudio.plugin.idea.liteflowx.system.toolWindow.service.impl;

import com.intellij.openapi.project.Project;
import top.xystudio.plugin.idea.liteflowx.system.toolWindow.frame.LiteFlowToolWindow;
import top.xystudio.plugin.idea.liteflowx.system.toolWindow.service.LiteFlowToolWindowService;

import javax.swing.*;

public class LiteFlowToolWindowServiceImpl implements LiteFlowToolWindowService {

    private final Project project;

    public LiteFlowToolWindowServiceImpl(Project project) {
        this.project = project;
    }

    @Override
    public JComponent getContent() {
        return new LiteFlowToolWindow(project);
    }

}
