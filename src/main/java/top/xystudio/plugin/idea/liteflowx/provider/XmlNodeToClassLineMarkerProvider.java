package top.xystudio.plugin.idea.liteflowx.provider;

import com.google.common.collect.ImmutableSet;
import com.intellij.icons.AllIcons;
import com.intellij.psi.PsiClass;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiWhiteSpace;
import com.intellij.psi.xml.XmlTag;
import com.intellij.psi.xml.XmlToken;
import com.intellij.util.xml.DomElement;
import com.intellij.util.xml.DomUtil;
import com.intellij.util.xml.GenericAttributeValue;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import top.xystudio.plugin.idea.liteflowx.dom.modal.*;
import top.xystudio.plugin.idea.liteflowx.util.Icons;
import top.xystudio.plugin.idea.liteflowx.util.JavaUtils;
import top.xystudio.plugin.idea.liteflowx.util.LiteFlowUtils;

import javax.swing.*;
import java.util.Optional;

public class XmlNodeToClassLineMarkerProvider extends XmlLineMarkerProvider{

    private static final ImmutableSet<String> TARGET_TYPES = ImmutableSet.of(
            Node.class.getSimpleName().toLowerCase()
    );


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
    public Optional<? extends PsiElement[]> apply(@NotNull XmlToken element) {
        DomElement domElement = DomUtil.getDomElement(element);
        if (domElement == null){
            return Optional.empty();
        }
        else if (domElement instanceof Node){
            String classValue = ((Node) domElement).getClassA().getStringValue();
            PsiClass clazz = JavaUtils.getClassByQualifiedName(element.getProject(), classValue);
            if (clazz != null) {
                return Optional.of(new PsiElement[]{clazz});
            }
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
        if (!(element instanceof XmlToken)){
            return false;
        }
        XmlToken token = (XmlToken) element;
        if (TARGET_TYPES.contains(token.getText())){
            PsiElement parent = token.getParent();
            if (parent instanceof XmlTag){
                PsiElement nextSibling = token.getNextSibling();
                if (nextSibling instanceof PsiWhiteSpace){
                    return true;
                }
            }
        }
        return false;
    }
}
