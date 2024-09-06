package top.xystudio.plugin.idea.liteflowx.system.provider;

import com.intellij.lang.documentation.AbstractDocumentationProvider;
import com.intellij.openapi.editor.Editor;
import com.intellij.openapi.util.NlsSafe;
import com.intellij.openapi.util.TextRange;
import com.intellij.psi.PsiDocCommentBase;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiFile;
import com.intellij.psi.PsiJavaDocumentedElement;
import com.intellij.psi.PsiManager;
import com.intellij.psi.PsiMethod;
import com.intellij.psi.impl.source.tree.LeafPsiElement;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import top.xystudio.plugin.idea.liteflowx.functionImpl.findComponentsImpl;
import top.xystudio.plugin.idea.liteflowx.system.language.psi.LiteFlowTypes;
import top.xystudio.plugin.idea.liteflowx.util.LiteFlowUtils;

import java.util.List;
import java.util.Optional;
import java.util.function.Consumer;

/**
 * @author hongzhida
 * @date 2024-08-03
 */
public class LiteflowDocumentationProvider extends AbstractDocumentationProvider {
    @Override
    public @Nullable String getQuickNavigateInfo(PsiElement element, PsiElement originalElement) {
        return super.getQuickNavigateInfo(element, originalElement);
    }

    @Override
    public @Nullable List<String> getUrlFor(PsiElement element, PsiElement originalElement) {
        return super.getUrlFor(element, originalElement);
    }

    @Override
    public @Nullable String generateHoverDoc(@NotNull PsiElement element, @Nullable PsiElement originalElement) {
        return super.generateHoverDoc(element, originalElement);
    }

    @Override
    public @Nullable @NlsSafe String generateRenderedDoc(@NotNull PsiDocCommentBase comment) {
        return super.generateRenderedDoc(comment);
    }

    @Override
    public void collectDocComments(@NotNull PsiFile file, @NotNull Consumer<? super @NotNull PsiDocCommentBase> sink) {
        super.collectDocComments(file, sink);
    }

    @Override
    public @Nullable PsiDocCommentBase findDocComment(@NotNull PsiFile file, @NotNull TextRange range) {
        return super.findDocComment(file, range);
    }

    @Override
    public @Nullable PsiElement getDocumentationElementForLookupItem(PsiManager psiManager, Object object, PsiElement element) {
        return super.getDocumentationElementForLookupItem(psiManager, object, element);
    }

    @Override
    public @Nullable PsiElement getDocumentationElementForLink(PsiManager psiManager, String link, PsiElement context) {
        return super.getDocumentationElementForLink(psiManager, link, context);
    }

    @Override
    public @Nullable PsiElement getCustomDocumentationElement(@NotNull Editor editor, @NotNull PsiFile file, @Nullable PsiElement contextElement, int targetOffset) {
        if (contextElement != null && ((LeafPsiElement) contextElement).getElementType() == LiteFlowTypes.LITEFLOW_IDENTIFIER) {
            return getJavaMethodFromLiteFlowElement(contextElement);
        }
        return super.getCustomDocumentationElement(editor, file, contextElement, targetOffset);
    }

    @Override
    public @Nullable @NlsSafe String generateDoc(PsiElement element, @Nullable PsiElement originalElement) {
        if (element instanceof PsiMethod) {
            PsiMethod method = (PsiMethod) element;
            return getMethodDocumentation(method);
        }
        return null;
    }

    private PsiElement getJavaMethodFromLiteFlowElement(PsiElement element) {
        String value = element.getText();
        Optional<? extends PsiElement> psiElement = LiteFlowUtils.findTargetByName(element.getProject(), value, new findComponentsImpl());
        // 返回对应的PsiMethod对象
        return psiElement.orElse(null);
    }

    private String getMethodDocumentation(PsiMethod method) {
        PsiJavaDocumentedElement docElement = (PsiJavaDocumentedElement) method;
        if (docElement.getDocComment() != null) {
            return docElement.getDocComment().getText();
        }
        return "No documentation available";
    }
}
