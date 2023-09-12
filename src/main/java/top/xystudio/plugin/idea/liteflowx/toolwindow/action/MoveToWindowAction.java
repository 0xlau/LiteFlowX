package top.xystudio.plugin.idea.liteflowx.toolwindow.action;

import com.intellij.icons.AllIcons;
import com.intellij.openapi.actionSystem.ActionManager;
import com.intellij.openapi.actionSystem.AnAction;
import com.intellij.openapi.actionSystem.AnActionEvent;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.wm.ToolWindow;
import com.intellij.openapi.wm.ToolWindowAnchor;
import com.intellij.openapi.wm.ToolWindowManager;
import com.intellij.openapi.wm.ToolWindowType;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import javax.swing.*;
import java.util.function.Consumer;

public class MoveToWindowAction extends MyAnAction {

    public MoveToWindowAction() {
        super("Move to Window", null, AllIcons.Actions.MoveToWindow, e -> {
            Project project = e.getProject();
            assert project != null;
            ToolWindow window = ToolWindowManager.getInstance(project).getToolWindow("LiteFlow Tools");
            assert window != null;
            window.setType(ToolWindowType.WINDOWED, null);
        });
    }
}
