package top.xystudio.plugin.idea.liteflowx.provider;

import com.intellij.icons.AllIcons;
import com.intellij.json.JsonUtil;
import com.intellij.json.psi.JsonObject;
import com.intellij.json.psi.JsonProperty;
import com.intellij.json.psi.JsonStringLiteral;
import com.intellij.psi.PsiClass;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiWhiteSpace;
import com.intellij.psi.xml.XmlTag;
import com.intellij.psi.xml.XmlToken;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import top.xystudio.plugin.idea.liteflowx.util.JavaUtils;
import top.xystudio.plugin.idea.liteflowx.util.LiteFlowUtils;

import javax.swing.*;
import java.util.Optional;

public class JsonNodeToClassLineMarkerProvider extends JsonLineMarkerProvider{

    @Override
    public @NotNull String getTooltip(PsiElement element, @NotNull PsiElement target) {
        String text = null;
        if (text == null && element instanceof PsiClass) {
            PsiClass psiClass = (PsiClass) element;
            text = psiClass.getQualifiedName();
        }
        if (text == null) {
            text = target.getContainingFile().getText();
        }
        return "LiteFlow Class found - " + text;
    }

    @Override
    public Optional<? extends PsiElement[]> apply(@NotNull JsonProperty element) {
        JsonStringLiteral jsonStringLiteral = JsonUtil.getPropertyValueOfType((JsonObject) element.getParent(), "class", JsonStringLiteral.class);
        if (jsonStringLiteral == null) {
            return Optional.empty();
        }
        PsiClass clazz = JavaUtils.getClassByQualifiedName(element.getProject(), jsonStringLiteral.getValue());
        if (clazz != null) {
            return Optional.of(new PsiElement[]{clazz});
        }
        return Optional.empty();
    }

    @Override
    public @Nullable Icon getIcon() {
        return AllIcons.FileTypes.JavaClass;
    }

    @Override
    public String getName() {
        return "Class statement line marker";
    }

    @Override
    public boolean isTargetElement(PsiElement element) {
        if (!(element instanceof JsonProperty)){
            return false;
        }
        if (!((JsonProperty) element).getName().equals("class")){
            return false;
        }
        PsiElement parent_3 = Optional.of(element.getParent().getParent().getParent()).orElse(null);
        if (!(parent_3 instanceof JsonProperty ) || !((JsonProperty) parent_3).getName().equals("node")){
            return false;
        }
        return true;
    }
}
