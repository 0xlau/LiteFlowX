package top.xystudio.plugin.idea.liteflowx.annotator;

import com.intellij.codeInsight.daemon.impl.quickfix.DeleteElementFix;
import com.intellij.codeInsight.intention.IntentionAction;
import com.intellij.codeInsight.intention.impl.BaseIntentionAction;
import com.intellij.codeInspection.ProblemHighlightType;
import com.intellij.codeInspection.RemoveAnnotationQuickFix;
import com.intellij.lang.annotation.AnnotationHolder;
import com.intellij.lang.annotation.Annotator;
import com.intellij.lang.annotation.HighlightSeverity;
import com.intellij.openapi.project.Project;
import com.intellij.psi.PsiAnnotation;
import com.intellij.psi.PsiClass;
import com.intellij.psi.PsiElement;
import com.intellij.refactoring.safeDelete.usageInfo.SafeDeleteAnnotation;
import org.jetbrains.annotations.NotNull;
import top.xystudio.plugin.idea.liteflowx.common.constant.Annotation;
import top.xystudio.plugin.idea.liteflowx.common.constant.Clazz;
import top.xystudio.plugin.idea.liteflowx.service.JavaService;
import top.xystudio.plugin.idea.liteflowx.service.LiteFlowNodeService;
import top.xystudio.plugin.idea.liteflowx.service.LiteFlowService;

import java.util.Objects;

/**
 * 判断使用 @LiteflowCmpDefine 注解时的合理性
 */
public class LiteFlowCmpDefineUsageAnnotator implements Annotator {
    @Override
    public void annotate(@NotNull PsiElement element, @NotNull AnnotationHolder holder) {
        Project project = element.getProject();
        JavaService javaService = project.getService(JavaService.class);
        LiteFlowNodeService liteFlowNodeService = project.getService(LiteFlowNodeService.class);

        if (element instanceof PsiClass psiClass) {
            PsiAnnotation annotation = psiClass.getAnnotation(Annotation.LiteflowCmpDefine);
            if (annotation == null || liteFlowNodeService.getLiteFlowNodeMetadata(psiClass) == null) {
                return;
            }
            if (psiClass.isInheritor(javaService.getClassByQualifiedName(Clazz.NodeComponent), true)){
                holder.newAnnotation(HighlightSeverity.WARNING, "@LiteflowCmpDefine is not required.")
                        .range(annotation.getTextRange())
                        .highlightType(ProblemHighlightType.WARNING)
                        .withFix(new DeleteElementFix(annotation))
                        .create();
            }
        }

    }
}
