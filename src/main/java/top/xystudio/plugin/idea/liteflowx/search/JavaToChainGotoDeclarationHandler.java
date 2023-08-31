package top.xystudio.plugin.idea.liteflowx.search;

import com.intellij.codeInsight.navigation.actions.GotoDeclarationHandler;
import com.intellij.openapi.actionSystem.DataContext;
import com.intellij.openapi.editor.Editor;
import com.intellij.openapi.project.Project;
import com.intellij.psi.JavaTokenType;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiJavaToken;
import org.jetbrains.annotations.Nls;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import top.xystudio.plugin.idea.liteflowx.common.metadata.LiteFlowChainMetadata;
import top.xystudio.plugin.idea.liteflowx.functionImpl.findChainsImpl;
import top.xystudio.plugin.idea.liteflowx.common.util.LiteFlowUtils;
import top.xystudio.plugin.idea.liteflowx.service.LiteFlowChainService;

import java.util.Optional;

public class JavaToChainGotoDeclarationHandler implements GotoDeclarationHandler {
    @Override
    public PsiElement @Nullable [] getGotoDeclarationTargets(@Nullable PsiElement sourceElement, int offset, Editor editor) {

        assert sourceElement != null;

        Project project = sourceElement.getProject();
        LiteFlowChainService chainService = project.getService(LiteFlowChainService.class);

        if (!isTargetElement(sourceElement)){
            return null;
        }
        String id = sourceElement.getText().replace("\"", "");
        LiteFlowChainMetadata metadata = chainService.getLiteFlowChainMetadataById(id);
        if (metadata == null){
            return null;
        }
        return new PsiElement[]{metadata.getPsiTarget()};
    }

    private boolean isTargetElement(PsiElement psiElement){
        if (!(psiElement instanceof PsiJavaToken psiJavaToken)){
            return false;
        }
        if (psiJavaToken.getTokenType() != JavaTokenType.STRING_LITERAL){
            return false;
        }
        return true;
    }

    @Override
    public @Nullable @Nls(capitalization = Nls.Capitalization.Title) String getActionText(@NotNull DataContext context) {
        return GotoDeclarationHandler.super.getActionText(context);
    }
}
