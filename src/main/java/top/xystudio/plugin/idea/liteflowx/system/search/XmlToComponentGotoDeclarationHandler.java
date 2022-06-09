package top.xystudio.plugin.idea.liteflowx.system.search;

import com.google.common.collect.ImmutableSet;
import com.intellij.codeInsight.navigation.actions.GotoDeclarationHandler;
import com.intellij.openapi.editor.Editor;
import com.intellij.openapi.project.Project;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiWhiteSpace;
import com.intellij.psi.impl.source.xml.XmlTokenImpl;
import com.intellij.psi.xml.*;
import com.intellij.util.ArrayUtil;
import org.jetbrains.annotations.Nullable;
import top.xystudio.plugin.idea.liteflowx.constant.Clazz;
import top.xystudio.plugin.idea.liteflowx.dom.modal.*;
import top.xystudio.plugin.idea.liteflowx.functionImpl.findChainImpl;
import top.xystudio.plugin.idea.liteflowx.functionImpl.findClassImpl;
import top.xystudio.plugin.idea.liteflowx.functionImpl.findNodeImpl;
import top.xystudio.plugin.idea.liteflowx.parse.RegexEntity;
import top.xystudio.plugin.idea.liteflowx.util.LiteFlowUtils;
import top.xystudio.plugin.idea.liteflowx.util.ParseUtils;
import top.xystudio.plugin.idea.liteflowx.util.XmlUtils;

import java.util.List;

public class XmlToComponentGotoDeclarationHandler implements GotoDeclarationHandler {

    @Override
    public PsiElement @Nullable [] getGotoDeclarationTargets(@Nullable PsiElement sourceElement, int offset, Editor editor) {
        Project project = sourceElement.getProject();

        if (isXmlTarget(sourceElement)){
            String expression = sourceElement.getText();
            List<RegexEntity> regexEntities = ParseUtils.parseExpression(expression);
            PsiElement[] psiElement1 = LiteFlowUtils.findTargetsByRegexEntities(project, regexEntities, new findClassImpl()).get();
            PsiElement[] psiElement2 = LiteFlowUtils.findTargetsByRegexEntities(project, regexEntities, new findChainImpl()).get();
            PsiElement[] psiElement3 = LiteFlowUtils.findTargetsByRegexEntities(project, regexEntities, new findNodeImpl()).get();
            return ArrayUtil.mergeArrays(ArrayUtil.mergeArrays(psiElement1, psiElement2), psiElement3);
        }

        return null;
    }

    /**
     * 过滤sourceElement，是不是来自xml文件的liteflow.ruleSource跳转请求
     * @param psiElement
     * @return
     */
    private boolean isXmlTarget(PsiElement psiElement) {
        if (!XmlUtils.isLiteFlowXmlFile(psiElement.getContainingFile())){
            return false;
        }
        if (!(psiElement instanceof XmlToken)){
            return false;
        }
        if (((XmlToken) psiElement).getTokenType() != XmlTokenType.XML_ATTRIBUTE_VALUE_TOKEN){
            return false;
        }
        if (!XmlUtils.isLiteflowXmlTarget(psiElement.getParent().getParent().getPrevSibling().getPrevSibling())){
            return false;
        }
        return true;
    }
}
