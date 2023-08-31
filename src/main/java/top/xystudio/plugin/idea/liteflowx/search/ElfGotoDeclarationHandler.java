package top.xystudio.plugin.idea.liteflowx.search;

import com.intellij.codeInsight.navigation.actions.GotoDeclarationHandler;
import com.intellij.openapi.actionSystem.DataContext;
import com.intellij.openapi.editor.Editor;
import com.intellij.openapi.project.Project;
import com.intellij.psi.PsiElement;
import org.jetbrains.annotations.Nls;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import top.xystudio.plugin.idea.liteflowx.common.metadata.LiteFlowChainMetadata;
import top.xystudio.plugin.idea.liteflowx.common.metadata.LiteFlowNodeMetadata;
import top.xystudio.plugin.idea.liteflowx.functionImpl.findChainsImpl;
import top.xystudio.plugin.idea.liteflowx.functionImpl.findComponentsImpl;
import top.xystudio.plugin.idea.liteflowx.functionImpl.findElfLocalVariablesImpl;
import top.xystudio.plugin.idea.liteflowx.service.LiteFlowChainService;
import top.xystudio.plugin.idea.liteflowx.service.LiteFlowNodeService;
import top.xystudio.plugin.idea.liteflowx.system.language.psi.LiteFlowElVariableRef;
import top.xystudio.plugin.idea.liteflowx.system.language.psi.LiteFlowLiteFlowNodeRef;
import top.xystudio.plugin.idea.liteflowx.system.language.psi.LiteFlowLiteFlowNodeStringRef;
import top.xystudio.plugin.idea.liteflowx.common.util.LiteFlowUtils;

import java.util.ArrayList;
import java.util.List;

public class ElfGotoDeclarationHandler implements GotoDeclarationHandler {
    @Override
    public PsiElement @Nullable [] getGotoDeclarationTargets(@Nullable PsiElement sourceElement, int offset, Editor editor) {
        assert sourceElement != null;
        Project project = sourceElement.getProject();
        LiteFlowNodeService nodeService = project.getService(LiteFlowNodeService.class);
        LiteFlowChainService chainService = project.getService(LiteFlowChainService.class);
        if (isNodeRefElement(sourceElement)){

            // 识别 普通组件
            String id = sourceElement.getText();
            LiteFlowNodeMetadata nodeMetadata = nodeService.getLiteFlowNodeMetadataById(id);
            if (nodeMetadata != null){
                return new PsiElement[]{nodeMetadata.getPsiTarget()};
            }
            LiteFlowChainMetadata chainMetadata = chainService.getLiteFlowChainMetadataById(id);
            if (chainMetadata != null){
                return new PsiElement[]{chainMetadata.getPsiTarget()};
            }
            return null;


        }else if(isNodeStringRefElement(sourceElement)){

            // 识别 node("")
            String id = sourceElement.getText().replace("\"", "");
            LiteFlowNodeMetadata metadata = nodeService.getLiteFlowNodeMetadataById(id);
            if (metadata == null){
                return null;
            }
            return new PsiElement[]{metadata.getPsiTarget()};

        }else if (isVariableRefElement(sourceElement)){

            // 识别 局部变量
            String name = sourceElement.getText();
            List<PsiElement> result = new ArrayList<>();
            LiteFlowUtils.findTargetByName(project, name, new findElfLocalVariablesImpl(sourceElement.getContainingFile())).ifPresent(result::add);
            if (result.size() == 0){
                return null;
            }
            return result.toArray(new PsiElement[0]);

        }

        return null;
    }

    private boolean isNodeStringRefElement(PsiElement psiElement) {
        if (psiElement.getParent() == null){
            return false;
        }

        return psiElement.getParent() instanceof LiteFlowLiteFlowNodeStringRef;
    }

    private boolean isNodeRefElement(PsiElement psiElement) {

        if (psiElement.getParent() == null){
            return false;
        }

        return psiElement.getParent() instanceof LiteFlowLiteFlowNodeRef;

    }

    private boolean isVariableRefElement(PsiElement psiElement) {

        if (psiElement.getParent() == null){
            return false;
        }

        return psiElement.getParent() instanceof LiteFlowElVariableRef;

    }

    @Override
    public @Nullable @Nls(capitalization = Nls.Capitalization.Title) String getActionText(@NotNull DataContext context) {
        return GotoDeclarationHandler.super.getActionText(context);
    }
}
