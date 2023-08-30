package top.xystudio.plugin.idea.liteflowx.provider;

import com.intellij.codeInsight.daemon.RelatedItemLineMarkerInfo;
import com.intellij.codeInsight.daemon.RelatedItemLineMarkerProvider;
import com.intellij.codeInsight.navigation.NavigationGutterIconBuilder;
import com.intellij.openapi.project.Project;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiMethod;
import icons.LiteFlowIcons;
import org.jetbrains.annotations.NotNull;
import top.xystudio.plugin.idea.liteflowx.common.constant.Annotation;
import top.xystudio.plugin.idea.liteflowx.common.constant.NodeTypeEnum;
import top.xystudio.plugin.idea.liteflowx.common.enums.LiteFlowNodeTypeEnum;
import top.xystudio.plugin.idea.liteflowx.common.metadata.LiteFlowNodeMetadata;
import top.xystudio.plugin.idea.liteflowx.service.JavaService;
import top.xystudio.plugin.idea.liteflowx.service.LiteFlowNodeService;
import top.xystudio.plugin.idea.liteflowx.service.LiteFlowService;

import javax.swing.*;
import java.util.Collection;

public class LiteFlowMethodLineMarkerProvider extends RelatedItemLineMarkerProvider {

    @Override
    protected void collectNavigationMarkers(@NotNull PsiElement element,
                                            @NotNull Collection<? super RelatedItemLineMarkerInfo<?>> result) {
        Project project = element.getProject();
        JavaService javaService = project.getService(JavaService.class);
        LiteFlowNodeService liteFlowNodeService = project.getService(LiteFlowNodeService.class);

        if (!(element instanceof PsiMethod psiMethod)) {
            return;
        }
        if (!psiMethod.hasAnnotation(Annotation.LiteflowMethod)){
            return;
        }
        LiteFlowNodeMetadata metadata = liteFlowNodeService.getLiteFlowNodeMetadata(psiMethod);
        if (metadata == null) {
            return;
        }
        Icon icon = null;
        String tip = "";
        switch (metadata.getNodeType()){
            case COMMON -> {
                icon = LiteFlowIcons.COMMON_COMPONENT_ICON;
                tip = "Component";
            }
            case IF -> {
                icon = LiteFlowIcons.IF_COMPONENT_ICON;
                tip = "IfComponent";
            }
            case FOR -> {
                icon = LiteFlowIcons.FOR_COMPONENT_ICON;
                tip = "ForComponent";
            }
            case BREAK -> {
                icon = LiteFlowIcons.BRK_COMPONENT_ICON;
                tip = "BreakComponent";
            }
            case SWITCH -> {
                icon = LiteFlowIcons.SW_COMPONENT_ICON;
                tip = "SwitchComponent";
            }
            case WHILE -> {
                icon = LiteFlowIcons.WHI_COMPONENT_ICON;
                tip = "WhileComponent";
            }
            case ITERATOR -> {
                icon = LiteFlowIcons.ITERATOR_COMPONENT_ICON;
                tip = "IteratorComponent";
            }
        }
        if (icon == null){
            return;
        }
        NavigationGutterIconBuilder<PsiElement> builder =
                NavigationGutterIconBuilder.create(icon).setTooltipTitle(metadata.getId() + " - " + tip).setTarget(metadata.getPsiTarget());
        result.add(builder.createLineMarkerInfo(element));
    }

}
