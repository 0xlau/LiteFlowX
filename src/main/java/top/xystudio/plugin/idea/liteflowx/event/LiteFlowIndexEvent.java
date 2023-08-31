package top.xystudio.plugin.idea.liteflowx.event;

import com.intellij.openapi.project.DumbService;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.startup.ProjectActivity;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import top.xystudio.plugin.idea.liteflowx.service.LiteFlowChainService;
import top.xystudio.plugin.idea.liteflowx.service.LiteFlowNodeService;

public class LiteFlowIndexEvent implements ProjectActivity {

    @Nullable
    @Override
    public Object execute(@NotNull Project project, @NotNull Continuation<? super Unit> continuation) {
        LiteFlowNodeService nodeService = project.getService(LiteFlowNodeService.class);
        LiteFlowChainService chainService = project.getService(LiteFlowChainService.class);
        DumbService dumbService = project.getService(DumbService.class);
        dumbService.runWhenSmart(() -> {
            nodeService.init();
            chainService.init();
        });
        return null;
    }
}
