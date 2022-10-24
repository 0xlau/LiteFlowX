package top.xystudio.plugin.idea.liteflowx.system.language.annotator;

import com.intellij.codeInspection.ProblemHighlightType;
import com.intellij.lang.annotation.AnnotationHolder;
import com.intellij.lang.annotation.Annotator;
import com.intellij.lang.annotation.HighlightSeverity;
import com.intellij.openapi.util.text.StringUtil;
import com.intellij.psi.PsiElement;
import com.intellij.psi.xml.XmlTag;
import com.intellij.psi.xml.XmlToken;
import com.intellij.psi.xml.XmlTokenType;
import org.jetbrains.annotations.NotNull;
import top.xystudio.plugin.idea.liteflowx.functionImpl.findChainsImpl;
import top.xystudio.plugin.idea.liteflowx.util.LiteFlowUtils;
import top.xystudio.plugin.idea.liteflowx.util.XmlUtils;

import java.util.List;
import java.util.Optional;

public class XmlChainAnnotator implements Annotator {
    @Override
    public void annotate(@NotNull PsiElement element, @NotNull AnnotationHolder holder) {
        if (!XmlUtils.isLiteFlowXmlFile(element.getContainingFile())){
            return;
        }
        if (!(element instanceof XmlToken)){
            return;
        }
        if (!(((XmlToken) element).getTokenType() == XmlTokenType.XML_NAME)){
            return;
        }
        if (!element.getText().equalsIgnoreCase("chain")){
            return;
        }
        if (!(element.getParent() instanceof XmlTag)){
            return;
        }
        String name = ((XmlTag) element.getParent()).getAttributeValue("name");
        if (StringUtil.isEmpty(name)){
            holder.newAnnotation(HighlightSeverity.ERROR, "缺少 'name' 属性值")
                    .range(element.getTextRange())
                    .highlightType(ProblemHighlightType.ERROR)
                    .create();
        }else {
            Optional<? extends List<? extends PsiElement>> optional = LiteFlowUtils.findTargetsByName(element.getProject(), name, new findChainsImpl());
            if ( optional.isPresent() && optional.get().size() > 1) {
                holder.newAnnotation(HighlightSeverity.ERROR, String.format("重复命名的链路 '%s' ", name))
                        .range(element.getTextRange())
                        .highlightType(ProblemHighlightType.ERROR)
                        .create();
            }
        }
    }
}
