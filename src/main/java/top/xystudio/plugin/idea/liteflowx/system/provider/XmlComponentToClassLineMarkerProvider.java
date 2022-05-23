package top.xystudio.plugin.idea.liteflowx.system.provider;

import com.intellij.ide.util.PsiElementListCellRenderer;
import com.intellij.openapi.util.NlsSafe;
import com.intellij.psi.PsiClass;
import com.intellij.psi.PsiElement;
import com.intellij.psi.xml.XmlToken;
import com.intellij.util.xml.DomElement;
import com.intellij.util.xml.DomUtil;
import icons.LiteFlowIcons;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import top.xystudio.plugin.idea.liteflowx.dom.modal.ComponentElement;
import top.xystudio.plugin.idea.liteflowx.functionImpl.findClassImpl;
import top.xystudio.plugin.idea.liteflowx.parse.RegexEntity;
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
        return LiteFlowIcons.COMPONENT_LINE_MARKER_ICON;
    }

    @Override
    public String getName() {
        return "Component statement line marker";
    }

    @Override
    public PsiElementListCellRenderer getCellRenderer() {
        return new PsiElementListCellRenderer() {
            @Override
            public @NlsSafe String getElementText(PsiElement element) {
                if (!(element instanceof PsiClass)){
                    return null;
                }
                return ((PsiClass) element).getName();
            }

            @Override
            protected @Nullable @NlsSafe String getContainerText(PsiElement element, String name) {
                return element.getContainingFile().getName();
            }

            @Override
            protected int getIconFlags() {
                return 0;
            }

            @Override
            protected Icon getIcon(PsiElement element) {
                return LiteFlowIcons.COMPONENT_LINE_MARKER_ICON;
            }
        };
    }

}
