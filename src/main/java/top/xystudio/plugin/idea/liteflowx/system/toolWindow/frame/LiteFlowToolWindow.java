package top.xystudio.plugin.idea.liteflowx.system.toolWindow.frame;

import com.intellij.openapi.actionSystem.*;
import com.intellij.openapi.project.Project;
import org.jetbrains.annotations.NotNull;

import javax.swing.*;
import java.awt.*;

public class LiteFlowToolWindow extends JPanel {

    private final Project project;

    public LiteFlowToolWindow(@NotNull Project project) {
        super(new BorderLayout());
        this.project = project;

        AnAction action = ActionManager.getInstance().getAction("LiteFlowTool.Toolbar");
        ActionToolbar actionToolbar = ActionManager.getInstance().createActionToolbar(
                ActionPlaces.TOOLBAR,
                action instanceof ActionGroup ? ((ActionGroup) action) : new DefaultActionGroup(),
                true
        );
        actionToolbar.setTargetComponent(this);
        this.add(actionToolbar.getComponent(), BorderLayout.NORTH);

    }

    public void refreshTree() {
        System.out.println("Refresh Tree");
    }
}
