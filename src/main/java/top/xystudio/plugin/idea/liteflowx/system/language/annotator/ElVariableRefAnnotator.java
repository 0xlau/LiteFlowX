package top.xystudio.plugin.idea.liteflowx.system.language.annotator;

import com.intellij.codeInspection.ProblemHighlightType;
import com.intellij.lang.annotation.AnnotationHolder;
import com.intellij.lang.annotation.Annotator;
import com.intellij.lang.annotation.HighlightSeverity;
import com.intellij.openapi.project.Project;
import com.intellij.psi.PsiElement;
import org.jetbrains.annotations.NotNull;
import top.xystudio.plugin.idea.liteflowx.functionImpl.findChainsImpl;
import top.xystudio.plugin.idea.liteflowx.functionImpl.findComponentsImpl;
import top.xystudio.plugin.idea.liteflowx.functionImpl.findElfLocalVariablesImpl;
import top.xystudio.plugin.idea.liteflowx.system.language.highlight.LiteFlowSyntaxHighlighter;
import top.xystudio.plugin.idea.liteflowx.system.language.psi.impl.LiteFlowElVariableRefImpl;
import top.xystudio.plugin.idea.liteflowx.system.language.psi.impl.LiteFlowLiteFlowNodeRefImpl;
import top.xystudio.plugin.idea.liteflowx.util.LiteFlowUtils;

import java.util.List;
import java.util.Optional;

/**
 * elf语法中，处理使用局部变量的ref
 */
public class ElVariableRefAnnotator implements Annotator {
    @Override
    public void annotate(@NotNull PsiElement element, @NotNull AnnotationHolder holder) {
        // Ensure the Psi Element is an expression

        Project project = element.getProject();

        if (!(element.getParent() instanceof LiteFlowElVariableRefImpl)){
            return;
        }

        String symbol = element.getParent().getText();

        // 判断局部变量
        Optional<? extends List<? extends PsiElement>> pe_localVariable = LiteFlowUtils.findTargetsByName(project, element.getText(), new findElfLocalVariablesImpl(element.getContainingFile()));
        if (pe_localVariable.isPresent()){
            holder.newSilentAnnotation(HighlightSeverity.INFORMATION)
                    .range(element.getTextRange())
                    .textAttributes(LiteFlowSyntaxHighlighter.QLEXPRESS_VARIABLES)
                    .create();
            return;
        }

        holder.newAnnotation(HighlightSeverity.ERROR, String.format("未找到被定义为 '%s' 的局部变量", symbol))
                .range(element.getTextRange())
                .highlightType(ProblemHighlightType.LIKE_UNKNOWN_SYMBOL)
                .create();

    }
}
