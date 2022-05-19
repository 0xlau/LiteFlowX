package top.xystudio.plugin.idea.liteflowx.contributor;

import com.intellij.codeInsight.completion.*;
import com.intellij.codeInsight.lookup.LookupElementBuilder;
import com.intellij.json.JsonUtil;
import com.intellij.json.psi.JsonObject;
import com.intellij.json.psi.JsonStringLiteral;
import com.intellij.openapi.project.Project;
import com.intellij.patterns.PlatformPatterns;
import com.intellij.psi.PsiAnnotation;
import com.intellij.psi.PsiClass;
import com.intellij.psi.PsiElement;
import com.intellij.psi.impl.source.xml.XmlTagImpl;
import com.intellij.psi.xml.XmlElementType;
import com.intellij.psi.xml.XmlTag;
import com.intellij.psi.xml.XmlToken;
import com.intellij.util.ProcessingContext;
import com.intellij.util.xml.DomElement;
import com.intellij.util.xml.DomUtil;
import org.jetbrains.annotations.NotNull;
import top.xystudio.plugin.idea.liteflowx.dom.modal.ComponentElement;
import top.xystudio.plugin.idea.liteflowx.service.LiteFlowService;
import top.xystudio.plugin.idea.liteflowx.util.LiteFlowUtils;

import java.util.Optional;

public class XmlCompletion extends CompletionContributor {

    public XmlCompletion() {
        extend(CompletionType.BASIC, PlatformPatterns.psiElement(XmlToken.class), new CompletionProvider<CompletionParameters>() {
            @Override
            protected void addCompletions(@NotNull CompletionParameters parameters, @NotNull ProcessingContext context, @NotNull CompletionResultSet result) {
                PsiElement position = parameters.getPosition();
                if (!isTargetElement(position)){
                    return;
                }
                Project project = parameters.getEditor().getProject();

                /* Find Component */
                for (PsiClass psiClass : LiteFlowService.getInstance(project).findAllLiteFlowComponent()) {
                    PsiAnnotation liteFlowAnnotation = LiteFlowUtils.getLiteFlowAnnotationByClass(psiClass);
                    if (liteFlowAnnotation != null){
                        String value = liteFlowAnnotation.findAttributeValue("value").getText().replace("\"", "");
                        result.addElement(JavaLookupElementBuilder.forClass(psiClass, value));
                    }
                }
                /* Find Chain */
                for (PsiElement element : LiteFlowUtils.findAllLiteFlowChain(project)) {
                    String text = null;
                    if (element instanceof XmlTag){
                        text = ((XmlTagImpl) element).getAttributeValue("name");
                    }else if(element instanceof JsonObject){
                        JsonStringLiteral chain = Optional.of(JsonUtil.getPropertyValueOfType((JsonObject) element, "name", JsonStringLiteral.class)).orElse(null);
                        if (chain != null){
                            text = chain.getValue();
                        }
                    }
                    if (text != null) {
                        result.addElement(LookupElementBuilder.create(element, text));
                    }
                }
                /* Find Node */
                for (PsiElement element : LiteFlowUtils.findAllLiteFlowNode(project)) {
                    String id = null;
                    String clazz = null;
                    if (element instanceof XmlTag){
                        id = ((XmlTagImpl) element).getAttributeValue("id");
                        clazz = ((XmlTagImpl) element).getAttributeValue("class");
                    }else if(element instanceof JsonObject){
                        JsonStringLiteral idString = Optional.of(JsonUtil.getPropertyValueOfType((JsonObject) element, "id", JsonStringLiteral.class)).orElse(null);
                        JsonStringLiteral clazzString = Optional.of(JsonUtil.getPropertyValueOfType((JsonObject) element, "class", JsonStringLiteral.class)).orElse(null);
                        if (idString != null){
                            id = idString.getValue();
                        }
                        if (clazzString != null){
                            clazz = idString.getValue();
                        }
                    }
                    if (id != null && clazz != null) {
                        result.addElement(LookupElementBuilder.create(element, id));
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
        DomElement domElement = Optional.of(DomUtil.getDomElement(element.getParent().getParent().getParent())).orElse(null);
        if (domElement instanceof ComponentElement) {
            return true;
        }
        return false;
    }
}
