package top.xystudio.plugin.idea.liteflowx.provider;

import com.intellij.ide.IconProvider;
import com.intellij.lang.Language;
import com.intellij.lang.java.JavaLanguage;
import com.intellij.lang.xml.XMLLanguage;
import com.intellij.openapi.project.Project;
import com.intellij.psi.PsiClass;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiMethod;
import icons.LiteFlowIcons;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import top.xystudio.plugin.idea.liteflowx.common.constant.Annotation;
import top.xystudio.plugin.idea.liteflowx.common.enums.DefineTypeEnum;
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
        LiteFlowNodeService nodeService = project.getService(LiteFlowNodeService.class);

        // 判断语言类型是否符合 Java 语言
        Language language = element.getLanguage();
        if (!language.isKindOf(JavaLanguage.INSTANCE)){
            return null;
        }

        if (!(element instanceof PsiClass psiClass)){
            return null;
        }

        // 转 LiteFlowNodeMetadata 判断类组件类型
        LiteFlowNodeMetadata classMetadata = nodeService.getLiteFlowNodeMetadata(psiClass);
        if (classMetadata != null && !classMetadata.isScript()){
            switch (classMetadata.getNodeType()){
                case COMMON -> {return LiteFlowIcons.COMMON_COMPONENT_ICON;}
                case IF -> {return LiteFlowIcons.IF_COMPONENT_ICON;}
                case SWITCH -> {return LiteFlowIcons.SW_COMPONENT_ICON;}
                case FOR -> {return LiteFlowIcons.FOR_COMPONENT_ICON;}
                case ITERATOR -> {return LiteFlowIcons.ITERATOR_COMPONENT_ICON;}
                case WHILE -> {return LiteFlowIcons.WHI_COMPONENT_ICON;}
                case BREAK -> {return LiteFlowIcons.BRK_COMPONENT_ICON;}
            }
        }


        // 单独判断是否为方法级声明组件类
        if (!psiClass.hasAnnotation(Annotation.LiteflowCmpDefine)){
            for (PsiMethod method : psiClass.getMethods()) {
                LiteFlowNodeMetadata methodMetadata = nodeService.getLiteFlowNodeMetadata(method);
                if (methodMetadata != null && methodMetadata.getDefineType() == DefineTypeEnum.DECLARED_METHOD){
                    return LiteFlowIcons.MULTI_COMPONENT_ICON;
                }
            }
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
