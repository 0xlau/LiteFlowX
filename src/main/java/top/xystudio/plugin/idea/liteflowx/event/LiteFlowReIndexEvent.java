package top.xystudio.plugin.idea.liteflowx.event;

import com.intellij.openapi.application.ApplicationManager;
import com.intellij.openapi.fileEditor.FileEditorManagerEvent;
import com.intellij.openapi.fileEditor.FileEditorManagerListener;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.startup.ProjectActivity;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import top.xystudio.plugin.idea.liteflowx.service.LiteFlowChainService;
import top.xystudio.plugin.idea.liteflowx.service.LiteFlowNodeService;

public class LiteFlowReIndexEvent implements ProjectActivity {


    @Nullable
    @Override
    public Object execute(@NotNull Project project, @NotNull Continuation<? super Unit> continuation) {
        LiteFlowNodeService nodeService = project.getService(LiteFlowNodeService.class);
        LiteFlowChainService chainService = project.getService(LiteFlowChainService.class);
        ApplicationManager.getApplication().getMessageBus().connect().subscribe(FileEditorManagerListener.FILE_EDITOR_MANAGER, new FileEditorManagerListener() {
            @Override
            public void selectionChanged(@NotNull FileEditorManagerEvent event) {
                nodeService.reIndex();
                chainService.reIndex();
            }
        });
        return null;
    }
}
