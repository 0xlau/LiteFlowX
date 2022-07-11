package top.xystudio.plugin.idea.liteflowx.system.search;

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
import top.xystudio.plugin.idea.liteflowx.functionImpl.findChainsImpl;
import top.xystudio.plugin.idea.liteflowx.util.LiteFlowUtils;

import java.util.Optional;

public class JavaToChainGotoDeclarationHandler implements GotoDeclarationHandler {
    @Override
    public PsiElement @Nullable [] getGotoDeclarationTargets(@Nullable PsiElement sourceElement, int offset, Editor editor) {
        Project project = sourceElement.getProject();
        if (!isTargetElement(sourceElement)){
            return null;
        }
        String name = sourceElement.getText().replace("\"", "");
        Optional<? extends PsiElement> result = LiteFlowUtils.findTargetByName(project, name, new findChainsImpl());
        if (!result.isPresent()){
            return null;
        }
        return new PsiElement[]{result.get()};
    }

    private boolean isTargetElement(PsiElement psiElement){
        if (!(psiElement instanceof PsiJavaToken)){
            return false;
        }
        if (((PsiJavaToken) psiElement).getTokenType() != JavaTokenType.STRING_LITERAL){
            return false;
        }
        return true;
    }

    @Override
    public @Nullable @Nls(capitalization = Nls.Capitalization.Title) String getActionText(@NotNull DataContext context) {
        return GotoDeclarationHandler.super.getActionText(context);
    }
}
