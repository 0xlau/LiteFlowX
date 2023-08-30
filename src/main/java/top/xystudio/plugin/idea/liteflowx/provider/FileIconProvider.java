package top.xystudio.plugin.idea.liteflowx.provider;

import com.intellij.ide.IconProvider;
import com.intellij.lang.Language;
import com.intellij.lang.java.JavaLanguage;
import com.intellij.lang.xml.XMLLanguage;
import com.intellij.openapi.project.Project;
import com.intellij.psi.PsiClass;
import com.intellij.psi.PsiElement;
import icons.LiteFlowIcons;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import top.xystudio.plugin.idea.liteflowx.common.metadata.LiteFlowNodeMetadata;
import top.xystudio.plugin.idea.liteflowx.service.LiteFlowNodeService;
import top.xystudio.plugin.idea.liteflowx.service.LiteFlowService;
import top.xystudio.plugin.idea.liteflowx.common.util.XmlUtils;

import javax.swing.*;

/**
 * 文件图标提供
 */
public class FileIconProvider extends IconProvider {
    @Override
    public @Nullable Icon getIcon(@NotNull PsiElement element, int flags) {

        if (isLiteFlowXmlFile(element)){
            return LiteFlowIcons.XML_ICON;
        }
        return getLiteFlowFileIcon(element);
    }

    private Icon getLiteFlowFileIcon(PsiElement element) {

        Project project = element.getProject();

        Language language = element.getLanguage();
        if (!language.isKindOf(JavaLanguage.INSTANCE)){
            return null;
        }

        if (!(element instanceof PsiClass psiClass)){
            return null;
        }

        LiteFlowNodeService nodeService = project.getService(LiteFlowNodeService.class);

        LiteFlowNodeMetadata metadata = nodeService.getLiteFlowNodeMetadata(psiClass);
        if (metadata == null || metadata.isScript()) return null;
        switch (metadata.getNodeType()){
            case COMMON -> {return LiteFlowIcons.COMMON_COMPONENT_ICON;}
            case IF -> {return LiteFlowIcons.IF_COMPONENT_ICON;}
            case SWITCH -> {return LiteFlowIcons.SW_COMPONENT_ICON;}
            case FOR -> {return LiteFlowIcons.FOR_COMPONENT_ICON;}
            case ITERATOR -> {return LiteFlowIcons.ITERATOR_COMPONENT_ICON;}
            case WHILE -> {return LiteFlowIcons.WHI_COMPONENT_ICON;}
            case BREAK -> {return LiteFlowIcons.BRK_COMPONENT_ICON;}
        }

        if (LiteFlowService.getInstance(project).isLiteFlowMultiComponent((PsiClass) element)){
            return LiteFlowIcons.MULTI_COMPONENT_ICON;
        }
        return null;
    }

    private boolean isLiteFlowXmlFile(PsiElement element) {
        Language language = element.getLanguage();
        if (!language.isKindOf(XMLLanguage.INSTANCE)){
            return false;
        }
        return XmlUtils.isLiteFlowXmlFile(element.getContainingFile());
    }
}
