package top.xystudio.plugin.idea.liteflowx.toolwindow.action;

import com.intellij.icons.AllIcons;
import com.intellij.openapi.actionSystem.AnAction;
import com.intellij.openapi.actionSystem.AnActionEvent;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import javax.swing.*;
import java.awt.*;
import java.net.URI;
import java.util.function.Consumer;

public class LineToDocumentAction extends MyAnAction {

    public LineToDocumentAction() {
        super("Link to Document", null, AllIcons.Actions.Help, (e) -> {
            String url = "https://liteflow.cc";

            Desktop desktop = Desktop.getDesktop();
            if(Desktop.isDesktopSupported() && desktop.isSupported(Desktop.Action.BROWSE)) {
                try {
                    desktop.browse(new URI(url));
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
        });
    }
}
