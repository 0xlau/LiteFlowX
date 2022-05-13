package top.xystudio.plugin.idea.liteflowx.provider;

import com.intellij.json.JsonUtil;
import com.intellij.json.psi.JsonObject;
import com.intellij.json.psi.JsonProperty;
import com.intellij.json.psi.JsonStringLiteral;
import com.intellij.psi.PsiElement;
import org.jetbrains.annotations.NotNull;
import top.xystudio.plugin.idea.liteflowx.util.Icons;
import top.xystudio.plugin.idea.liteflowx.util.LiteFlowUtils;

import javax.swing.*;
import java.util.Optional;

public class JsonChainMarkerProvider extends JsonLineMarkerProvider{


    @Override
    public String getTooltip(PsiElement element, PsiElement target) {
        String text = element.getContainingFile().getVirtualFile().getName();
        return "LiteFlow Chain found - In " + text;
    }

    @Override
    public Optional<? extends PsiElement[]> apply(@NotNull JsonProperty element) {
        JsonStringLiteral jsonStringLiteral = JsonUtil.getPropertyValueOfType((JsonObject) element.getParent(), "value", JsonStringLiteral.class);
        if (jsonStringLiteral == null) {
            return Optional.empty();
        }
        String expression = jsonStringLiteral.getValue();
        String[] names = LiteFlowUtils.getComponentNamesByExpression(expression);
        return LiteFlowUtils.findChainsByComponentName(element.getProject(), names);
    }

    @Override
    public @NotNull Icon getIcon() {
        return Icons.CHAIN_LINE_MARKER_ICON;
    }

    @Override
    public String getName() {
        return "Chain statement line marker";
    }
}
