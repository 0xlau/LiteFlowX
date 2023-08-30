package top.xystudio.plugin.idea.liteflowx.search;

import com.intellij.codeInsight.navigation.actions.GotoDeclarationHandler;
import com.intellij.openapi.actionSystem.DataContext;
import com.intellij.openapi.editor.Editor;
import com.intellij.openapi.project.Project;
import com.intellij.psi.PsiElement;
import org.jetbrains.annotations.Nls;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import top.xystudio.plugin.idea.liteflowx.functionImpl.findChainsImpl;
import top.xystudio.plugin.idea.liteflowx.functionImpl.findComponentsImpl;
import top.xystudio.plugin.idea.liteflowx.functionImpl.findElfLocalVariablesImpl;
import top.xystudio.plugin.idea.liteflowx.system.language.psi.LiteFlowElVariableRef;
import top.xystudio.plugin.idea.liteflowx.system.language.psi.LiteFlowLiteFlowNodeRef;
import top.xystudio.plugin.idea.liteflowx.system.language.psi.LiteFlowLiteFlowNodeStringRef;
import top.xystudio.plugin.idea.liteflowx.common.util.LiteFlowUtils;

import java.util.ArrayList;
import java.util.List;

public class ElfGotoDeclarationHandler implements GotoDeclarationHandler {
    @Override
    public PsiElement @Nullable [] getGotoDeclarationTargets(@Nullable PsiElement sourceElement, int offset, Editor editor) {
        Project project = sourceElement.getProject();

        if (isNodeRefElement(sourceElement)){

            // 识别 普通组件
            String name = sourceElement.getText();
            List<PsiElement> result = new ArrayList<>();
            PsiElement component = LiteFlowUtils.findTargetByName(project, name, new findComponentsImpl()).orElse(null);
            PsiElement chain = LiteFlowUtils.findTargetByName(project, name, new findChainsImpl()).orElse(null);
            List<? extends PsiElement> localVars = LiteFlowUtils.findTargetsByName(project, name, new findElfLocalVariablesImpl(sourceElement.getContainingFile())).orElse(null);

            if (component != null)  {result.add(component);}
            if (chain != null)  {result.add(chain);}
            if (localVars != null) {result.addAll(localVars);}

            if (result.size() == 0){
                return null;
            }
            return result.toArray(new PsiElement[0]);

        }else if(isNodeStringRefElement(sourceElement)){

            // 识别 node("")
            String name = sourceElement.getText().replace("\"", "");
            List<PsiElement> result = new ArrayList<>();
            LiteFlowUtils.findTargetByName(project, name, new findComponentsImpl()).ifPresent(result::add);
            if (result.size() == 0){
                return null;
            }
            return result.toArray(new PsiElement[0]);

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
