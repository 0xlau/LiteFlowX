package top.xystudio.plugin.idea.liteflowx.toolWindow.actions;

import com.intellij.icons.AllIcons;
import com.intellij.openapi.actionSystem.AnActionEvent;
import com.intellij.openapi.project.DumbAwareAction;
import org.jetbrains.annotations.NotNull;
import top.xystudio.plugin.idea.liteflowx.toolWindow.LiteFlowToolWindowFactory;
import top.xystudio.plugin.idea.liteflowx.toolWindow.frame.LiteFlowToolWindow;

/**
 * toolWindow刷新按钮
 */
public class RefreshAction extends DumbAwareAction {

    public RefreshAction() {
        getTemplatePresentation().setText("Refresh");
        getTemplatePresentation().setIcon(AllIcons.Actions.Refresh);
    }

    @Override
    public void actionPerformed(@NotNull AnActionEvent e) {
        LiteFlowToolWindow toolWindow = LiteFlowToolWindowFactory.getToolWindow(e.getProject());
        if (toolWindow != null){
            toolWindow.refreshTree();
        }
    }

}
