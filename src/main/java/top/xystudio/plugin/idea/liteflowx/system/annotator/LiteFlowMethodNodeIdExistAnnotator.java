package top.xystudio.plugin.idea.liteflowx.system.annotator;

import com.intellij.codeInspection.ProblemHighlightType;
import com.intellij.lang.annotation.AnnotationHolder;
import com.intellij.lang.annotation.Annotator;
import com.intellij.lang.annotation.HighlightSeverity;
import com.intellij.openapi.project.Project;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiMethod;
import org.jetbrains.annotations.NotNull;
import top.xystudio.plugin.idea.liteflowx.constant.Annotation;
import top.xystudio.plugin.idea.liteflowx.functionImpl.findComponentsImpl;
import top.xystudio.plugin.idea.liteflowx.service.JavaService;
import top.xystudio.plugin.idea.liteflowx.util.LiteFlowUtils;

import java.util.List;
import java.util.Optional;

/**
 * 检测@LiteFlowMethod的NodeId重复问题
 * （！暂停使用！）
 */
public class LiteFlowMethodNodeIdExistAnnotator implements Annotator {

    @Override
    public void annotate(@NotNull PsiElement element, @NotNull AnnotationHolder holder) {
        Project project = element.getProject();
        JavaService javaService = JavaService.getInstance(project);
        if (element instanceof PsiMethod && ((PsiMethod) element).hasAnnotation(Annotation.LiteflowMethod)){
            String nodeId = javaService.getAnnotationAttributeValue((PsiMethod) element, Annotation.LiteflowMethod, "nodeId");
            if (nodeId != null){
                Optional<? extends List<? extends PsiElement>> optional = LiteFlowUtils.findTargetsByName(element.getProject(), nodeId, new findComponentsImpl());
                if ( optional.isPresent() && optional.get().size() > 1) {
                    holder.newAnnotation(HighlightSeverity.ERROR, String.format("重复命名的组件 '%s' ", nodeId))
                            .range(((PsiMethod) element).getAnnotation(Annotation.LiteflowMethod).getTextRange())
                            .highlightType(ProblemHighlightType.GENERIC_ERROR)
                            .create();
                }
            }
        }
    }

}
