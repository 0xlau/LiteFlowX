package top.xystudio.plugin.idea.liteflowx.system.annotator;

import com.intellij.codeInspection.ProblemHighlightType;
import com.intellij.lang.annotation.AnnotationHolder;
import com.intellij.lang.annotation.Annotator;
import com.intellij.lang.annotation.HighlightSeverity;
import com.intellij.openapi.project.Project;
import com.intellij.psi.PsiClass;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiMethod;
import org.jetbrains.annotations.NotNull;
import top.xystudio.plugin.idea.liteflowx.constant.Annotation;
import top.xystudio.plugin.idea.liteflowx.constant.Clazz;
import top.xystudio.plugin.idea.liteflowx.service.JavaService;
import top.xystudio.plugin.idea.liteflowx.service.LiteFlowService;

public class LiteFlowCmpDefineUsageAnnotator implements Annotator {
    @Override
    public void annotate(@NotNull PsiElement element, @NotNull AnnotationHolder holder) {
        Project project = element.getProject();
        JavaService javaService = JavaService.getInstance(project);
        LiteFlowService liteFlowService = LiteFlowService.getInstance(project);
        if (element instanceof PsiClass && ((PsiClass) element).hasAnnotation(Annotation.LiteflowCmpDefine)) {
            if (!liteFlowService.isLiteFlowComponent(element)) {
                return;
            }
            if (((PsiClass) element).isInheritor(javaService.getClassByQualifiedName(Clazz.NodeComponent), true)){
                holder.newAnnotation(HighlightSeverity.WARNING, "当继承了LiteFlow的组件类时，@LiteFlowCmpDefine不是必须的")
                        .range(((PsiClass) element).getAnnotation(Annotation.LiteflowCmpDefine).getTextRange())
                        .highlightType(ProblemHighlightType.WARNING)
                        .create();
            }
        }
    }
}
