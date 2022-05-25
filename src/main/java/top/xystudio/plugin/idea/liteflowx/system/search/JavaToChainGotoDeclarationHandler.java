package top.xystudio.plugin.idea.liteflowx.system.search;

import com.intellij.codeInsight.navigation.actions.GotoDeclarationHandler;
import com.intellij.ide.DataManager;
import com.intellij.openapi.actionSystem.DataContext;
import com.intellij.openapi.actionSystem.DataKey;
import com.intellij.openapi.actionSystem.DataProvider;
import com.intellij.openapi.actionSystem.PlatformDataKeys;
import com.intellij.openapi.editor.Editor;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.util.Key;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiJavaToken;
import org.jetbrains.annotations.Nls;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import top.xystudio.plugin.idea.liteflowx.functionImpl.findChainImpl;
import top.xystudio.plugin.idea.liteflowx.util.LiteFlowUtils;

import java.util.Optional;

public class JavaToChainGotoDeclarationHandler implements GotoDeclarationHandler {
    @Override
    public PsiElement @Nullable [] getGotoDeclarationTargets(@Nullable PsiElement sourceElement, int offset, Editor editor) {
        Project project = sourceElement.getProject();
        if (!(sourceElement instanceof PsiJavaToken)){
            return null;
        }
        String name = sourceElement.getText().replace("\"", "");
        Optional<? extends PsiElement> result = LiteFlowUtils.findTargetByName(project, name, new findChainImpl());
        if (!result.isPresent()){
            return null;
        }
        return new PsiElement[]{result.get()};
    }

    @Override
    public @Nullable @Nls(capitalization = Nls.Capitalization.Title) String getActionText(@NotNull DataContext context) {
        return GotoDeclarationHandler.super.getActionText(context);
    }
}
