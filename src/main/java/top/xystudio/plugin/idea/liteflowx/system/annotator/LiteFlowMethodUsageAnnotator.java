package top.xystudio.plugin.idea.liteflowx.system.annotator;

import com.intellij.codeInspection.ProblemHighlightType;
import com.intellij.lang.annotation.AnnotationHolder;
import com.intellij.lang.annotation.Annotator;
import com.intellij.lang.annotation.HighlightSeverity;
import com.intellij.openapi.project.Project;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiMethod;
import org.apache.commons.lang.ArrayUtils;
import org.jetbrains.annotations.NotNull;
import top.xystudio.plugin.idea.liteflowx.constant.Annotation;
import top.xystudio.plugin.idea.liteflowx.constant.LiteFlowMethodEnum;
import top.xystudio.plugin.idea.liteflowx.constant.NodeTypeEnum;
import top.xystudio.plugin.idea.liteflowx.service.JavaService;
import top.xystudio.plugin.idea.liteflowx.service.LiteFlowService;

/**
 * 检测@LiteFlowMethod的NodeType和MethodEnum搭配形式
 */
public class LiteFlowMethodUsageAnnotator implements Annotator {

    @Override
    public void annotate(@NotNull PsiElement element, @NotNull AnnotationHolder holder) {
        Project project = element.getProject();
        JavaService javaService = JavaService.getInstance(project);
        LiteFlowService liteFlowService = LiteFlowService.getInstance(project);
        if (element instanceof PsiMethod && ((PsiMethod) element).hasAnnotation(Annotation.LiteflowMethod)){
            if (!liteFlowService.isLiteFlowComponent(element)){
                return;
            }
            String value = javaService.getAnnotationAttributeValue((PsiMethod) element, Annotation.LiteflowMethod, "value");
            String nodeType = javaService.getAnnotationAttributeValue((PsiMethod) element, Annotation.LiteflowMethod, "nodeType");
            if (ArrayUtils.contains(LiteFlowMethodEnum.NECESSARY_PROCESS, value.split("\\(")[0])){
                if (value.equals(LiteFlowMethodEnum.PROCESS) && nodeType.equals(NodeTypeEnum.COMMON)){
                    return;
                }
                if (value.equals(LiteFlowMethodEnum.PROCESS_BREAK) && nodeType.equals(NodeTypeEnum.BREAK)){
                    return;
                }
                if (value.equals(LiteFlowMethodEnum.PROCESS_FOR) && nodeType.equals(NodeTypeEnum.FOR)){
                    return;
                }
                if (value.equals(LiteFlowMethodEnum.PROCESS_ITERATOR) && nodeType.equals(NodeTypeEnum.ITERATOR)){
                    return;
                }
                if (value.equals(LiteFlowMethodEnum.PROCESS_IF) && nodeType.equals(NodeTypeEnum.IF)){
                    return;
                }
                if (value.equals(LiteFlowMethodEnum.PROCESS_WHILE) && nodeType.equals(NodeTypeEnum.WHILE)){
                    return;
                }
                if (value.equals(LiteFlowMethodEnum.PROCESS_SWITCH) && nodeType.equals(NodeTypeEnum.SWITCH)){
                    return;
                }
                if (value.equals(LiteFlowMethodEnum.PROCESS_BOOLEAN) && nodeType.equals(NodeTypeEnum.BOOLEAN)){
                    return;
                }
                holder.newAnnotation(HighlightSeverity.WARNING, "LiteFlowMethodEnum 与 nodeType 不是最佳的搭配形式")
                        .range(((PsiMethod) element).getAnnotation(Annotation.LiteflowMethod).getTextRange())
                        .highlightType(ProblemHighlightType.WARNING)
                        .create();
            }
        }
    }

}
