package top.xystudio.plugin.idea.liteflowx.toolwindow;

import com.intellij.icons.AllIcons;
import com.intellij.openapi.actionSystem.*;
import com.intellij.openapi.project.DumbAware;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.wm.ToolWindow;
import com.intellij.openapi.wm.ToolWindowFactory;
import com.intellij.ui.content.Content;
import com.intellij.ui.content.ContentFactory;
import org.jetbrains.annotations.NotNull;
import top.xystudio.plugin.idea.liteflowx.toolwindow.action.LineToDocumentAction;
import top.xystudio.plugin.idea.liteflowx.toolwindow.action.MoveToWindowAction;
import top.xystudio.plugin.idea.liteflowx.toolwindow.action.MyAnAction;
import top.xystudio.plugin.idea.liteflowx.toolwindow.service.CategoryWindowService;
import top.xystudio.plugin.idea.liteflowx.toolwindow.service.NavigateWindowService;

import java.util.ArrayList;
import java.util.List;

public class LiteFlowToolWindowFactory implements ToolWindowFactory, DumbAware {

    @Override
    public void createToolWindowContent(@NotNull Project project, @NotNull ToolWindow toolWindow) {

        // 配置 OptionActions
        List<AnAction> actions = new ArrayList<>();
        actions.add(new MoveToWindowAction());
        actions.add(new LineToDocumentAction());
        toolWindow.setTitleActions(actions);

        // 配置 Tabs
        NavigateWindowService navigateWindowService = project.getService(NavigateWindowService.class);
        CategoryWindowService categoryWindowService = project.getService(CategoryWindowService.class);

        Content navigateContent = ContentFactory.getInstance().createContent(navigateWindowService.initToolWindow(toolWindow), "Navigate", true);
        navigateContent.setIcon(AllIcons.Nodes.Artifact);
        navigateContent.putUserData(ToolWindow.SHOW_CONTENT_ICON, true);

        Content categoryContent = ContentFactory.getInstance().createContent(categoryWindowService.initToolWindow(toolWindow), "Category", true);
        categoryContent.setIcon(AllIcons.Nodes.InspectionResults);
        categoryContent.putUserData(ToolWindow.SHOW_CONTENT_ICON, true);

        toolWindow.getContentManager().addContent(navigateContent);
        toolWindow.getContentManager().addContent(categoryContent);

    }

}
