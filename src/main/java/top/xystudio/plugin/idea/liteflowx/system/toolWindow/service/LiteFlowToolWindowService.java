package top.xystudio.plugin.idea.liteflowx.system.toolWindow.service;

import com.intellij.openapi.application.ApplicationManager;
import com.intellij.openapi.components.ServiceManager;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.wm.ToolWindow;
import com.intellij.ui.content.Content;
import com.intellij.ui.content.ContentFactory;
import org.jetbrains.annotations.NotNull;

import javax.swing.*;

public interface LiteFlowToolWindowService {

    static LiteFlowToolWindowService getInstance(@NotNull Project project) {
        return project.getService(LiteFlowToolWindowService.class);
    }

    /**
     * 获取可视化内容
     *
     * @return ContentView
     */
    JComponent getContent();

    /**
     * 初始化窗口
     * @param toolWindow toolWindow
     */
    default void init(@NotNull ToolWindow toolWindow) {
        ContentFactory contentFactory = ApplicationManager.getApplication().getService(ContentFactory.class);
        Content content = contentFactory.createContent(getContent(), "", false);
        toolWindow.getContentManager().addContent(content);
    }

}
