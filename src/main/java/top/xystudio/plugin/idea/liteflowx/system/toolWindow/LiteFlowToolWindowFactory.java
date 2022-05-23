package top.xystudio.plugin.idea.liteflowx.system.toolWindow;

import com.intellij.openapi.project.Project;
import com.intellij.openapi.wm.ToolWindow;
import com.intellij.openapi.wm.ToolWindowFactory;
import com.intellij.openapi.wm.ToolWindowManager;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import top.xystudio.plugin.idea.liteflowx.system.toolWindow.frame.LiteFlowToolWindow;
import top.xystudio.plugin.idea.liteflowx.system.toolWindow.service.LiteFlowToolWindowService;

import java.awt.*;

public class LiteFlowToolWindowFactory implements ToolWindowFactory {

    /**
     * ToolWindowId
     */
    public static final String TOOL_WINDOW_ID = "LiteFlowTool";

    /**
     * 获取LiteFlowTool的toolWindow窗口内容
     *
     * @param project auto
     * @return LiteFlowToolWindow
     */
    @Nullable
    public static LiteFlowToolWindow getToolWindow(@Nullable Project project) {
        return getToolWindow(project, null);
    }

    /**
     * 获取LiteFlowTool的toolWindow窗口内容
     *
     * @param project auto
     * @return LiteFlowToolWindow
     */
    @Nullable
    public static LiteFlowToolWindow getToolWindow(@Nullable Project project, Boolean show) {
        if (project == null) {
            return null;
        }
        ToolWindow toolWindow = getWindow(project);
        if (show != null && show) {
            showWindow(project, null);
        }
        if (toolWindow != null) {
            for (Component component : toolWindow.getComponent().getComponents()) {
                if (component instanceof LiteFlowToolWindow) {
                    return ((LiteFlowToolWindow) component);
                }
            }
        }
        return null;
    }

    @Nullable
    public static ToolWindow getWindow(@NotNull Project project) {
        return ToolWindowManager.getInstance(project).getToolWindow(TOOL_WINDOW_ID);
    }

    public static void showWindow(@NotNull Project project, @Nullable Runnable onShow) {
        ToolWindow window = getWindow(project);
        if (window == null) {
            return;
        }
        window.show(onShow);
    }

    public static void hideWindow(@NotNull Project project, @Nullable Runnable onShow) {
        ToolWindow window = getWindow(project);
        if (window == null) {
            return;
        }
        window.hide(onShow);
    }

    @Override
    public void createToolWindowContent(@NotNull Project project, @NotNull ToolWindow toolWindow) {
        LiteFlowToolWindowService.getInstance(project).init(toolWindow);
    }

}
