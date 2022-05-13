package top.xystudio.plugin.idea.liteflowx.provider;

import com.intellij.psi.PsiClass;
import com.intellij.psi.PsiElement;
import com.intellij.psi.xml.XmlToken;
import com.intellij.util.xml.DomElement;
import com.intellij.util.xml.DomUtil;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import top.xystudio.plugin.idea.liteflowx.dom.modal.ComponentElement;
import top.xystudio.plugin.idea.liteflowx.util.Icons;
import top.xystudio.plugin.idea.liteflowx.util.LiteFlowUtils;

import javax.swing.*;
import java.util.Optional;

public class XmlComponentLineMarkerProvider extends XmlLineMarkerProvider {

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
    public @Nullable Icon getIcon() {
        return Icons.STATEMENT_LINE_MARKER_ICON;
    }

    @Override
    public String getName() {
        return "Component statement line marker";
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
            return LiteFlowUtils.findClassesByComponentName(element.getProject(), names);
        }
        return Optional.empty();
    }
}
