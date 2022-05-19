package top.xystudio.plugin.idea.liteflowx.provider;

import com.intellij.psi.PsiClass;
import com.intellij.psi.PsiElement;
import com.intellij.psi.xml.XmlToken;
import com.intellij.util.xml.DomElement;
import com.intellij.util.xml.DomUtil;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import top.xystudio.plugin.idea.liteflowx.dom.modal.ComponentElement;
import top.xystudio.plugin.idea.liteflowx.functionImpl.findClassImpl;
import top.xystudio.plugin.idea.liteflowx.parse.RegexEntity;
import top.xystudio.plugin.idea.liteflowx.util.Icons;
import top.xystudio.plugin.idea.liteflowx.util.LiteFlowUtils;
import top.xystudio.plugin.idea.liteflowx.util.ParseUtils;

import javax.swing.*;
import java.util.List;
import java.util.Optional;

/**
 * 实现对Component的识别以及获取Class的位置
 * @author Coder-XiaoYi
 */
public class XmlComponentToClassLineMarkerProvider extends XmlLineMarkerProvider {

    @Override
    public Optional<? extends PsiElement[]> apply(@NotNull XmlToken element) {
        DomElement domElement = DomUtil.getDomElement(element);
        if (domElement == null || !(domElement instanceof ComponentElement)){
            return Optional.empty();
        }
        String expression = ((ComponentElement) domElement).getValue().getRawText();
        List<RegexEntity> regexEntities = ParseUtils.parseExpression(expression);
        return LiteFlowUtils.findTargetsByRegexEntities(element.getProject(), regexEntities, new findClassImpl());
    }

    @Override
    public String getTooltip(PsiElement to, PsiElement from) {
        String text = null;
        if (text == null && to instanceof PsiClass) {
            PsiClass psiClass = (PsiClass) to;
            text = psiClass.getQualifiedName();
        }
        if (text == null) {
            text = from.getContainingFile().getText();
        }
        return "LiteFlow Component found - " + text;
    }

    @Override
    public @Nullable Icon getIcon() {
        return Icons.COMPONENT_LINE_MARKER_ICON;
    }

    @Override
    public String getName() {
        return "Component statement line marker";
    }

}
