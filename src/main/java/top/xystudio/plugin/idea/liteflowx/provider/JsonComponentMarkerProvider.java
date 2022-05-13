package top.xystudio.plugin.idea.liteflowx.provider;

import com.google.common.collect.ImmutableSet;
import com.intellij.json.JsonUtil;
import com.intellij.json.psi.*;
import com.intellij.json.psi.impl.JsonObjectImpl;
import com.intellij.json.psi.impl.JsonPropertyImpl;
import com.intellij.psi.PsiClass;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiFile;
import com.intellij.util.xml.DomElement;
import com.intellij.util.xml.DomUtil;
import org.jetbrains.annotations.NotNull;
import top.xystudio.plugin.idea.liteflowx.dom.modal.ComponentElement;
import top.xystudio.plugin.idea.liteflowx.util.Icons;
import top.xystudio.plugin.idea.liteflowx.util.LiteFlowUtils;

import javax.swing.*;
import java.util.Optional;

public class JsonComponentMarkerProvider extends JsonLineMarkerProvider{

    @Override
    public String getTooltip(PsiElement element, PsiElement target) {
        String text = null;
        if (text == null && element instanceof PsiClass) {
            PsiClass psiClass = (PsiClass) element;
            text = psiClass.getQualifiedName();
        }
        if (text == null) {
            text = target.getContainingFile().getText();
        }
        return "LiteFlow Component found - " + text;
    }


    @Override
    public Optional<? extends PsiElement[]> apply(@NotNull JsonProperty element) {
        JsonStringLiteral jsonStringLiteral = JsonUtil.getPropertyValueOfType((JsonObject) element.getParent(), "value", JsonStringLiteral.class);
        if (jsonStringLiteral == null) {
            return Optional.empty();
        }
        String expression = jsonStringLiteral.getValue();
        String[] names = LiteFlowUtils.getComponentNamesByExpression(expression);
        return LiteFlowUtils.findClassesByComponentName(element.getProject(), names);
    }

    @Override
    public @NotNull Icon getIcon() {
        return Icons.STATEMENT_LINE_MARKER_ICON;
    }

    @Override
    public String getName() {
        return "Component statement line marker";
    }
}
