package top.xystudio.plugin.idea.liteflowx.provider;

import com.intellij.ide.IconProvider;
import com.intellij.lang.Language;
import com.intellij.lang.java.JavaLanguage;
import com.intellij.lang.xml.XMLLanguage;
import com.intellij.psi.PsiClass;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiFile;
import com.intellij.psi.xml.XmlFile;
import com.intellij.psi.xml.XmlTag;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import top.xystudio.plugin.idea.liteflowx.dom.modal.Flow;
import top.xystudio.plugin.idea.liteflowx.service.LiteFlowService;
import top.xystudio.plugin.idea.liteflowx.util.DomUtils;
import top.xystudio.plugin.idea.liteflowx.util.Icons;

import javax.swing.*;

/**
 * 文件图标提供
 */
public class FileIconProvider extends IconProvider {
    @Override
    public @Nullable Icon getIcon(@NotNull PsiElement element, int flags) {
        if (isLiteFlowXmlFile(element)){
            return Icons.XML_FILE_ICON;
        }
        if (isLiteFlowComponentClassFile(element)){
            return Icons.COMPONENT_CLASS_FILE_ICON;
        }
        if (isLiteFlowSlotClassFile(element)){
            return Icons.SLOT_CLASS_FILE_ICON;
        }
        return null;
    }

    private boolean isLiteFlowSlotClassFile(PsiElement element) {
        Language language = element.getLanguage();
        if (!language.isKindOf(JavaLanguage.INSTANCE)){
            return false;
        }
        if (!(element instanceof PsiClass)){
            return false;
        }
        if (!LiteFlowService.getInstance(element.getProject()).isLiteFlowSlot((PsiClass) element)){
            return false;
        }
        return true;
    }

    private boolean isLiteFlowComponentClassFile(PsiElement element) {
        Language language = element.getLanguage();
        if (!language.isKindOf(JavaLanguage.INSTANCE)){
            return false;
        }
        if (!(element instanceof PsiClass)){
            return false;
        }
        if (!LiteFlowService.getInstance(element.getProject()).isLiteFlowComponent((PsiClass) element)){
            return false;
        }
        return true;
    }

    private boolean isLiteFlowXmlFile(PsiElement element) {
        Language language = element.getLanguage();
        if (!language.isKindOf(XMLLanguage.INSTANCE)){
            return false;
        }
        return DomUtils.isLiteFlowXmlFile(element.getContainingFile());
    }
}
