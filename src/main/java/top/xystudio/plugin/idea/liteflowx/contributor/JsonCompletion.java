package top.xystudio.plugin.idea.liteflowx.contributor;

import com.google.common.collect.ImmutableSet;
import com.intellij.codeInsight.completion.*;
import com.intellij.codeInsight.lookup.LookupElementBuilder;
import com.intellij.json.JsonUtil;
import com.intellij.json.psi.JsonObject;
import com.intellij.json.psi.JsonProperty;
import com.intellij.json.psi.JsonStringLiteral;
import com.intellij.openapi.project.Project;
import com.intellij.patterns.PlatformPatterns;
import com.intellij.psi.PsiAnnotation;
import com.intellij.psi.PsiClass;
import com.intellij.psi.PsiElement;
import com.intellij.psi.impl.source.tree.LeafPsiElement;
import com.intellij.psi.impl.source.xml.XmlTagImpl;
import com.intellij.psi.xml.XmlTag;
import com.intellij.util.ProcessingContext;
import org.jetbrains.annotations.NotNull;
import top.xystudio.plugin.idea.liteflowx.util.LiteFlowUtils;

import java.util.Optional;

public class JsonCompletion extends CompletionContributor {

    private static final ImmutableSet<String> TARGET_TYPES = ImmutableSet.of(
            "then", "when", "pre", "finally"
    );

    public JsonCompletion() {
        extend(CompletionType.BASIC, PlatformPatterns.psiElement(LeafPsiElement.class), new CompletionProvider<CompletionParameters>() {
            @Override
            protected void addCompletions(@NotNull CompletionParameters parameters, @NotNull ProcessingContext context, @NotNull CompletionResultSet result) {
                PsiElement position = parameters.getPosition();
                if (!isTargetElement(position)){
                    return;
                }
                Project project = parameters.getEditor().getProject();

                for (PsiClass psiClass : LiteFlowUtils.findAllLiteFlowComponent(project)) {
                    PsiAnnotation liteFlowAnnotation = LiteFlowUtils.getLiteFlowAnnotationByClass(psiClass);
                    if (liteFlowAnnotation != null){
                        String value = liteFlowAnnotation.findAttributeValue("value").getText().replace("\"", "");
                        result.addElement(JavaLookupElementBuilder.forClass(psiClass, value));
                    }
                }
                for (PsiElement element : LiteFlowUtils.findAllLiteFlowChain(project)) {
                    String text = null;
                    if (element instanceof XmlTag){
                        text = ((XmlTagImpl) element).getAttribute("name").getValue();
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
            }
        });
    }

    private boolean isTargetElement(PsiElement element) {
        if (!(element instanceof LeafPsiElement)){
            return false;
        }
        element = element.getParent();
        if (!(element instanceof JsonStringLiteral)){
            return false;
        }
        PsiElement parent = element.getParent();
        if (!(parent instanceof JsonProperty) || !((JsonProperty) parent).getName().equals("value")){
            return false;
        }
        JsonStringLiteral typeProperty = Optional.of(JsonUtil.getPropertyValueOfType((JsonObject) parent.getParent(), "type", JsonStringLiteral.class)).orElse(null);
        if (typeProperty == null || !TARGET_TYPES.contains(typeProperty.getValue())){
            return false;
        }
        return true;
    }

}
