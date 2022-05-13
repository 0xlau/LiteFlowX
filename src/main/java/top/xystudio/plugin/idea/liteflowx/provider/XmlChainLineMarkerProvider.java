package top.xystudio.plugin.idea.liteflowx.provider;

import com.intellij.psi.PsiClass;
import com.intellij.psi.PsiElement;
import com.intellij.psi.xml.XmlToken;
import com.intellij.util.xml.DomElement;
import com.intellij.util.xml.DomUtil;
import org.jetbrains.annotations.NotNull;
import top.xystudio.plugin.idea.liteflowx.dom.modal.ComponentElement;
import top.xystudio.plugin.idea.liteflowx.util.Icons;
import top.xystudio.plugin.idea.liteflowx.util.LiteFlowUtils;

import javax.swing.*;
import java.util.Optional;

public class XmlChainLineMarkerProvider extends XmlLineMarkerProvider {

    @Override
    public String getTooltip(PsiElement element, PsiElement target) {
        String text = element.getContainingFile().getVirtualFile().getName();
        return "LiteFlow Chain found - In " + text;
    }

    @Override
    public Optional<? extends PsiElement[]> apply(@NotNull XmlToken element) {
        DomElement domElement = DomUtil.getDomElement(element);
        if (domElement == null){
            return Optional.empty();
        }
        else if (domElement instanceof ComponentElement){
            String expression = ((ComponentElement) domElement).getValue().getRawText();
            String[] names = LiteFlowUtils.getComponentNamesByExpression(expression);
            return LiteFlowUtils.findChainsByComponentName(element.getProject(), names);
        }
        return Optional.empty();
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
