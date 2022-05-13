package top.xystudio.plugin.idea.liteflowx.provider;

import com.google.common.collect.ImmutableSet;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiFile;
import com.intellij.psi.PsiWhiteSpace;
import com.intellij.psi.xml.XmlFile;
import com.intellij.psi.xml.XmlTag;
import com.intellij.psi.xml.XmlToken;
import top.xystudio.plugin.idea.liteflowx.dom.modal.*;

public abstract class XmlLineMarkerProvider extends CommonLineMarkerProvider<XmlToken> {

    private static final String ROOT_TYPES = Flow.class.getSimpleName().toLowerCase();
    private static final ImmutableSet<String> TARGET_TYPES = ImmutableSet.of(
            Then.class.getSimpleName().toLowerCase(),
            When.class.getSimpleName().toLowerCase(),
            Pre.class.getSimpleName().toLowerCase(),
            Finally.class.getSimpleName().toLowerCase()
    );

    @Override
    public boolean isLiteflowFile(PsiElement element) {
        PsiFile file = element.getContainingFile();
        if (file == null){
            return false;
        }
        if (!(file instanceof XmlFile)){
            return false;
        }
        XmlTag rootTag = ((XmlFile) file).getRootTag();
        if (rootTag == null){
            return false;
        }
        if (!ROOT_TYPES.equals(rootTag.getName())){
            return false;
        }
        return true;
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
