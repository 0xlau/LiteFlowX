package top.xystudio.plugin.idea.liteflowx.annotator;

import com.intellij.codeInspection.ProblemHighlightType;
import com.intellij.lang.annotation.AnnotationHolder;
import com.intellij.lang.annotation.Annotator;
import com.intellij.lang.annotation.HighlightSeverity;
import com.intellij.openapi.project.Project;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiMethod;
import org.apache.commons.lang.ArrayUtils;
import org.jetbrains.annotations.NotNull;
import top.xystudio.plugin.idea.liteflowx.common.constant.Annotation;
import top.xystudio.plugin.idea.liteflowx.common.constant.LiteFlowMethodEnum;
import top.xystudio.plugin.idea.liteflowx.common.constant.NodeTypeEnum;
import top.xystudio.plugin.idea.liteflowx.service.JavaService;
import top.xystudio.plugin.idea.liteflowx.service.LiteFlowNodeService;
import top.xystudio.plugin.idea.liteflowx.service.LiteFlowService;

import java.util.Objects;

/**
 * 检测 @LiteFlowMethod 的 NodeTypeEnum 和 LiteFlowMethodEnum 搭配形式
 */
public class LiteFlowMethodUsageAnnotator implements Annotator {

    @Override
    public void annotate(@NotNull PsiElement element, @NotNull AnnotationHolder holder) {
        Project project = element.getProject();
        JavaService javaService = project.getService(JavaService.class);
        LiteFlowNodeService liteFlowNodeService = project.getService(LiteFlowNodeService.class);
        if (element instanceof PsiMethod psiMethod && psiMethod.hasAnnotation(Annotation.LiteflowMethod)){
            if (liteFlowNodeService.getLiteFlowNodeMetadata(psiMethod) == null){
                return;
            }
            String value = javaService.getAnnotationAttributeValue(psiMethod, Annotation.LiteflowMethod, "value");
            String nodeType = javaService.getAnnotationAttributeValue(psiMethod, Annotation.LiteflowMethod, "nodeType");
            if (value == null || nodeType == null) return;
            if (LiteFlowMethodEnum.isNecessaryProcess(value) && NodeTypeEnum.isStandardNodeType(nodeType)){
                if (value.contains(LiteFlowMethodEnum.PROCESS) && nodeType.contains(NodeTypeEnum.COMMON)){
                    return;
                }
                if (value.contains(LiteFlowMethodEnum.PROCESS_BREAK) && nodeType.contains(NodeTypeEnum.BREAK)){
                    return;
                }
                if (value.contains(LiteFlowMethodEnum.PROCESS_FOR) && nodeType.contains(NodeTypeEnum.FOR)){
                    return;
                }
                if (value.contains(LiteFlowMethodEnum.PROCESS_ITERATOR) && nodeType.contains(NodeTypeEnum.ITERATOR)){
                    return;
                }
                if (value.contains(LiteFlowMethodEnum.PROCESS_IF) && nodeType.contains(NodeTypeEnum.IF)){
                    return;
                }
                if (value.contains(LiteFlowMethodEnum.PROCESS_WHILE) && nodeType.contains(NodeTypeEnum.WHILE)){
                    return;
                }
                if (value.contains(LiteFlowMethodEnum.PROCESS_SWITCH) && nodeType.contains(NodeTypeEnum.SWITCH)){
                    return;
                }
                holder.newAnnotation(HighlightSeverity.WARNING, "LiteFlowMethodEnum and NodeTypeEnum are not the best combination.")
                        .range(Objects.requireNonNull(psiMethod.getAnnotation(Annotation.LiteflowMethod)).getTextRange())
                        .highlightType(ProblemHighlightType.WARNING)
                        .create();
            }
        }
    }

}
