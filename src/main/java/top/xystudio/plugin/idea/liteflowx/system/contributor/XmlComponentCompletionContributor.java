package top.xystudio.plugin.idea.liteflowx.system.contributor;

import com.intellij.codeInsight.completion.*;
import com.intellij.codeInsight.lookup.LookupElementBuilder;
import com.intellij.openapi.project.Project;
import com.intellij.patterns.PlatformPatterns;
import com.intellij.psi.PsiClass;
import com.intellij.psi.PsiElement;
import com.intellij.psi.impl.source.xml.XmlTagImpl;
import com.intellij.psi.xml.XmlElementType;
import com.intellij.psi.xml.XmlTag;
import com.intellij.psi.xml.XmlToken;
import com.intellij.util.ProcessingContext;
import icons.LiteFlowIcons;
import org.jetbrains.annotations.NotNull;
import top.xystudio.plugin.idea.liteflowx.service.LiteFlowService;
import top.xystudio.plugin.idea.liteflowx.util.XmlUtils;

public class XmlComponentCompletionContributor extends CompletionContributor {


    public XmlComponentCompletionContributor() {
        extend(CompletionType.BASIC, PlatformPatterns.psiElement(XmlToken.class), new CompletionProvider<>() {
            @Override
            protected void addCompletions(@NotNull CompletionParameters parameters, @NotNull ProcessingContext context, @NotNull CompletionResultSet result) {
                PsiElement position = parameters.getPosition();
                if (!isTargetElement(position)) {
                    return;
                }

                Project project = parameters.getPosition().getProject();
                LiteFlowService liteFlowService = LiteFlowService.getInstance(project);

                /** 搜索全部LiteFlowComponent */
                for (PsiClass psiClass : liteFlowService.findAllLiteFlowComponent()) {
                    String componentName = liteFlowService.getLiteFlowComponentNameByPsiClass(psiClass);
                    if (componentName != null){
                        result.addElement(
                                JavaLookupElementBuilder.forClass(psiClass, componentName)
                                        .withIcon(LiteFlowIcons.COMPONENT_LINE_MARKER_ICON)
                                        .withTypeText("Component")
                                        .bold()
                        );
                    }

                }
                /** 搜索全部LiteFlowChain */
                for (PsiElement element : liteFlowService.findAllLiteFlowChain()) {
                    String text = null;
                    if (element instanceof XmlTag) {
                        text = ((XmlTagImpl) element).getAttributeValue("name");
                    }
                    if (text != null) {
                        result.addElement(
                                LookupElementBuilder.create(element, text).withIcon(LiteFlowIcons.CHAIN_LINE_MARKER_ICON).withTypeText("Chain").bold()
                        );
                    }
                }
                /** 搜索全部LiteFlowNode */
                for (PsiElement element : liteFlowService.findAllLiteFlowNode()) {
                    String id = null;
                    String clazz = null;
                    if (element instanceof XmlTag) {
                        id = ((XmlTagImpl) element).getAttributeValue("id");
                        clazz = ((XmlTagImpl) element).getAttributeValue("class");
                    }
                    if (id != null && clazz != null) {
                        result.addElement(LookupElementBuilder.create(element, id).withIcon(LiteFlowIcons.NODE_LINE_MARKER_ICON).withTypeText("Node").bold());
                    }
                }
            }
        });
    }

    private boolean isTargetElement(PsiElement element) {
        if (!(element instanceof XmlToken)){
            return false;
        }
        if (((XmlToken) element).getTokenType() != XmlElementType.XML_ATTRIBUTE_VALUE_TOKEN){
            return false;
        }
        if (XmlUtils.isLiteFlowXmlFile(element.getContainingFile())){
            return true;
        }
        return false;
    }
}
