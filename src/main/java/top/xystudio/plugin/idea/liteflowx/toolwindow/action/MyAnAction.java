package top.xystudio.plugin.idea.liteflowx.toolwindow.action;

import com.intellij.openapi.actionSystem.AnAction;
import com.intellij.openapi.actionSystem.AnActionEvent;
import com.intellij.openapi.util.NlsActions;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import javax.swing.*;
import java.util.function.Consumer;

public class MyAnAction extends AnAction {

    private final Consumer<AnActionEvent> anActionEvent;

    public MyAnAction(@Nullable @NlsActions.ActionText String text,
                      @Nullable @NlsActions.ActionDescription String description,
                      @Nullable Icon icon,
                      @NotNull Consumer<AnActionEvent> event){
        super(text, description, icon);
        this.anActionEvent = event;
    }
    
    @Override
    public void actionPerformed(@NotNull AnActionEvent e) {
        this.anActionEvent.accept(e);
    }
}
