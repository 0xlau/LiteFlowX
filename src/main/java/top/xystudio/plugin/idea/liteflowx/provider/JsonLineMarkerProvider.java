package top.xystudio.plugin.idea.liteflowx.provider;

import com.google.common.collect.ImmutableSet;
import com.intellij.json.JsonUtil;
import com.intellij.json.psi.*;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiFile;
import org.jetbrains.annotations.NotNull;

import java.util.Optional;

public abstract class JsonLineMarkerProvider extends CommonLineMarkerProvider<JsonProperty> {

    private static final String ROOT_TYPES = "flow";
    private static final ImmutableSet<String> TARGET_TYPES = ImmutableSet.of(
            "then", "when", "pre", "finally"
    );

    @Override
    public boolean isLiteflowFile(@NotNull PsiElement element) {
        PsiFile file = element.getContainingFile();
        if (file == null){
            return false;
        }
        if (!(file instanceof JsonFile)){
            return false;
        }
        JsonValue top = ((JsonFile) file).getTopLevelValue();
        if (top == null || !(top instanceof JsonObject)){
            return false;
        }
        JsonProperty property = ((JsonObject) top).findProperty(ROOT_TYPES);
        if (property == null){
            return false;
        }
        return true;
    }

    @Override
    public boolean isTargetElement(@NotNull PsiElement element) {
        if (!(element instanceof JsonProperty)){
            return false;
        }
        if (!((JsonProperty) element).getName().equals("value")){
            return false;
        }
        JsonStringLiteral typeProperty = Optional.of(JsonUtil.getPropertyValueOfType((JsonObject) element.getParent(), "type", JsonStringLiteral.class)).orElse(null);
        if (typeProperty == null || !TARGET_TYPES.contains(typeProperty.getValue())){
            return false;
        }
        PsiElement parent_3 = Optional.of(element.getParent().getParent().getParent()).orElse(null);
        if (!(parent_3 instanceof JsonProperty ) || !((JsonProperty) parent_3).getName().equals("condition")){
            return false;
        }
        return true;
    }
}
