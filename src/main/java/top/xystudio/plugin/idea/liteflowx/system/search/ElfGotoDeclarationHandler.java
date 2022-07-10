package top.xystudio.plugin.idea.liteflowx.system.search;

import com.intellij.codeInsight.navigation.actions.GotoDeclarationHandler;
import com.intellij.openapi.actionSystem.DataContext;
import com.intellij.openapi.editor.Editor;
import com.intellij.openapi.project.Project;
import com.intellij.psi.PsiElement;
import org.jetbrains.annotations.Nls;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import top.xystudio.plugin.idea.liteflowx.functionImpl.findChainImpl;
import top.xystudio.plugin.idea.liteflowx.functionImpl.findComponentImpl;
import top.xystudio.plugin.idea.liteflowx.functionImpl.findElfLocalVariablesImpl;
import top.xystudio.plugin.idea.liteflowx.system.language.psi.LiteFlowLiteFlowNodeRef;
import top.xystudio.plugin.idea.liteflowx.system.language.psi.LiteFlowTokenType;
import top.xystudio.plugin.idea.liteflowx.util.LiteFlowUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class ElfGotoDeclarationHandler implements GotoDeclarationHandler {
    @Override
    public PsiElement @Nullable [] getGotoDeclarationTargets(@Nullable PsiElement sourceElement, int offset, Editor editor) {
        Project project = sourceElement.getProject();

        if (!isTargetElement(sourceElement)){
            return null;
        }

        String name = sourceElement.getText();

        List<PsiElement> result = new ArrayList<>();
        PsiElement component = LiteFlowUtils.findTargetByName(project, name, new findComponentImpl()).orElse(null);
        PsiElement chain = LiteFlowUtils.findTargetByName(project, name, new findChainImpl()).orElse(null);
        List<? extends PsiElement> localVars = LiteFlowUtils.findTargetsByName(project, name, new findElfLocalVariablesImpl(sourceElement.getContainingFile())).orElse(null);

        if (component != null)  {result.add(component);}
        if (chain != null)  {result.add(chain);}
        if (localVars != null) {result.addAll(localVars);}

        if (result.size() == 0){
            return null;
        }
        return result.toArray(new PsiElement[0]);
    }

    private boolean isTargetElement(PsiElement psiElement) {

        if (psiElement.getParent() == null){
            return false;
        }

        if (!(psiElement.getParent() instanceof LiteFlowLiteFlowNodeRef)){
            return false;
        }
        return true;

    }

    @Override
    public @Nullable @Nls(capitalization = Nls.Capitalization.Title) String getActionText(@NotNull DataContext context) {
        return GotoDeclarationHandler.super.getActionText(context);
    }
}
