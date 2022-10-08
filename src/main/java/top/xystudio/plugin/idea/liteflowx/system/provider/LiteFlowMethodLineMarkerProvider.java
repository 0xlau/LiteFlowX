package top.xystudio.plugin.idea.liteflowx.system.provider;

import com.intellij.codeInsight.daemon.RelatedItemLineMarkerInfo;
import com.intellij.codeInsight.daemon.RelatedItemLineMarkerProvider;
import com.intellij.codeInsight.navigation.NavigationGutterIconBuilder;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiMethod;
import icons.LiteFlowIcons;
import org.jetbrains.annotations.NotNull;
import top.xystudio.plugin.idea.liteflowx.constant.Annotation;
import top.xystudio.plugin.idea.liteflowx.constant.NodeTypeEnum;
import top.xystudio.plugin.idea.liteflowx.service.JavaService;
import top.xystudio.plugin.idea.liteflowx.service.LiteFlowService;

import javax.swing.*;
import java.util.Collection;

public class LiteFlowMethodLineMarkerProvider extends RelatedItemLineMarkerProvider {

    @Override
    protected void collectNavigationMarkers(@NotNull PsiElement element,
                                            @NotNull Collection<? super RelatedItemLineMarkerInfo<?>> result) {
        JavaService javaService = JavaService.getInstance(element.getProject());
        LiteFlowService liteFlowService = LiteFlowService.getInstance(element.getProject());

        if (!(element instanceof PsiMethod)) {
            return;
        }
        if (!((PsiMethod) element).hasAnnotation(Annotation.LiteflowMethod)){
            return;
        }
        if (!liteFlowService.isLiteFlowComponent(element)) {
            return;
        }
        String nodeType = javaService.getAnnotationAttributeValue((PsiMethod) element, Annotation.LiteflowMethod, "nodeType");
        if (nodeType == null){
            return;
        }
        Icon icon;
        String tip = "";
        if (nodeType.equals(NodeTypeEnum.COMMON)){
            icon = LiteFlowIcons.NORMAL_COMPONENT_CLASS_FILE_ICON;
            tip = "Common component";
        } else if (nodeType.equals(NodeTypeEnum.IF)) {
            icon = LiteFlowIcons.IF_COMPONENT_CLASS_FILE_ICON;
            tip = "If component";
        } else if (nodeType.equals(NodeTypeEnum.SWITCH)) {
            icon = LiteFlowIcons.SW_COMPONENT_CLASS_FILE_ICON;
            tip = "SWITCH component";
        } else if (nodeType.equals(NodeTypeEnum.FOR)) {
            icon = LiteFlowIcons.FOR_COMPONENT_CLASS_FILE_ICON;
            tip = "For component";
        } else if (nodeType.equals(NodeTypeEnum.WHILE)) {
            icon = LiteFlowIcons.WHI_COMPONENT_CLASS_FILE_ICON;
            tip = "While component";
        } else if (nodeType.equals(NodeTypeEnum.BREAK)) {
            icon = LiteFlowIcons.BRK_COMPONENT_CLASS_FILE_ICON;
            tip = "Break component";
        } else {
            return;
        }
        NavigationGutterIconBuilder<PsiElement> builder =
                NavigationGutterIconBuilder.create(icon).setTooltipTitle(tip).setTarget(null);
        result.add(builder.createLineMarkerInfo(element));
    }

}
