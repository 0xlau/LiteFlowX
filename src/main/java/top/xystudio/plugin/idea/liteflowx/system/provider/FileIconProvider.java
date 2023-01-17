package top.xystudio.plugin.idea.liteflowx.system.provider;

import com.intellij.ide.IconProvider;
import com.intellij.lang.Language;
import com.intellij.lang.java.JavaLanguage;
import com.intellij.lang.xml.XMLLanguage;
import com.intellij.psi.PsiClass;
import com.intellij.psi.PsiElement;
import icons.LiteFlowIcons;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import top.xystudio.plugin.idea.liteflowx.service.LiteFlowService;
import top.xystudio.plugin.idea.liteflowx.util.XmlUtils;

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
        Language language = element.getLanguage();
        if (!language.isKindOf(JavaLanguage.INSTANCE)){
            return null;
        }
        if (!(element instanceof PsiClass)){
            return null;
        }
        if (LiteFlowService.getInstance(element.getProject()).isLiteFlowIfComponent(element)){
            return LiteFlowIcons.IF_COMPONENT_ICON;
        }
        if (LiteFlowService.getInstance(element.getProject()).isLiteFlowSwitchComponent(element)){
            return LiteFlowIcons.SW_COMPONENT_ICON;
        }
        if (LiteFlowService.getInstance(element.getProject()).isLiteFlowForComponent(element)){
            return LiteFlowIcons.FOR_COMPONENT_ICON;
        }
        if (LiteFlowService.getInstance(element.getProject()).isLiteFlowIteratorComponent(element)){
            return LiteFlowIcons.ITERATOR_COMPONENT_ICON;
        }
        if (LiteFlowService.getInstance(element.getProject()).isLiteFlowWhileComponent(element)){
            return LiteFlowIcons.WHI_COMPONENT_ICON;
        }
        if (LiteFlowService.getInstance(element.getProject()).isLiteFlowBreakComponent(element)){
            return LiteFlowIcons.BRK_COMPONENT_ICON;
        }
        if (LiteFlowService.getInstance(element.getProject()).isLiteFlowNormalComponent(element)){
            return LiteFlowIcons.COMMON_COMPONENT_ICON;
        }
        if (LiteFlowService.getInstance(element.getProject()).isLiteFlowMultiComponent((PsiClass) element)){
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
