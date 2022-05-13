package top.xystudio.plugin.idea.liteflowx.provider;

import com.intellij.psi.PsiElement;
import com.intellij.psi.xml.XmlTag;
import com.intellij.psi.xml.XmlToken;
import com.intellij.util.xml.DomElement;
import com.intellij.util.xml.DomUtil;
import com.intellij.util.xml.GenericAttributeValue;
import org.jetbrains.annotations.NotNull;
import top.xystudio.plugin.idea.liteflowx.dom.modal.ComponentElement;
import top.xystudio.plugin.idea.liteflowx.dom.modal.Node;
import top.xystudio.plugin.idea.liteflowx.util.Icons;
import top.xystudio.plugin.idea.liteflowx.util.LiteFlowUtils;

import javax.swing.*;
import java.util.Optional;

public class XmlNodeLineMarkerProvider extends XmlLineMarkerProvider{
    @Override
    public @NotNull String getTooltip(PsiElement element, @NotNull PsiElement target) {
        String text = "LiteFlow Node found - In " + element.getContainingFile().getVirtualFile().getName();
        if (element instanceof XmlTag){
            DomElement domElement = DomUtil.getDomElement(element);
            if (domElement instanceof Node){
                text = "LiteFlow Node found - (" + ((Node) domElement).getName().getStringValue() +")" + ((Node) domElement).getClassA().getStringValue();
            }
        }
        return text;
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
            return LiteFlowUtils.findNodesByComponentName(element.getProject(), names);
        }
        return Optional.empty();
    }

    @Override
    public @NotNull Icon getIcon() {
        return Icons.NODE_LINE_MARKER_ICON;
    }

    @Override
    public String getName() {
        return "Node statement line marker";
    }
}
