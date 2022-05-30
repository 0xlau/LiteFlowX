package top.xystudio.plugin.idea.liteflowx.system.search;

import com.intellij.codeInsight.navigation.actions.GotoDeclarationHandler;
import com.intellij.lang.properties.psi.PropertiesFile;
import com.intellij.lang.properties.psi.impl.PropertyImpl;
import com.intellij.lang.properties.psi.impl.PropertyValueImpl;
import com.intellij.openapi.actionSystem.DataContext;
import com.intellij.openapi.editor.Editor;
import com.intellij.openapi.module.ModuleUtilCore;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.vfs.VirtualFile;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiManager;
import com.intellij.psi.impl.source.tree.LeafPsiElement;
import com.intellij.psi.xml.XmlElementType;
import com.intellij.psi.xml.XmlFile;
import com.intellij.psi.xml.XmlTag;
import com.intellij.psi.xml.XmlToken;
import org.jetbrains.annotations.Nls;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.jetbrains.yaml.psi.YAMLFile;
import org.jetbrains.yaml.psi.impl.YAMLKeyValueImpl;
import top.xystudio.plugin.idea.liteflowx.constant.Clazz;
import top.xystudio.plugin.idea.liteflowx.service.FileService;

import java.util.ArrayList;
import java.util.Collection;

/**
 * 从properties以及yml或ymal文件中的liteflow.ruleSource跳转至指定文件
 */
public class ruleSourceToFileGotoDeclarationHandler implements GotoDeclarationHandler {
    @Override
    public PsiElement @Nullable [] getGotoDeclarationTargets(@Nullable PsiElement sourceElement, int offset, Editor editor) {
        Project project = sourceElement.getProject();

        Collection<PsiElement> result = new ArrayList<>();
        String pathStr = null;

        if (isPropertiesTarget(sourceElement)){
            pathStr = ((PropertyImpl) sourceElement.getParent()).getValue();
        }else if (isYamlTarget(sourceElement)){
            pathStr = sourceElement.getText();
        }else if (isXmlTarget(sourceElement)){
            pathStr = sourceElement.getText();
        }

        if (pathStr == null){
            return null;
        }

        String[] paths = pathStr.replaceAll("xml:|json:|yml:", "").split(",|;");
        for (String path : paths) {
            VirtualFile resourceFile = FileService.getInstance(project).getFileInResourcePath(ModuleUtilCore.findModuleForPsiElement(sourceElement), path.trim());
            if (resourceFile != null){
                result.add(PsiManager.getInstance(project).findFile(resourceFile));
                continue;
            }
            VirtualFile absoluteFile = FileService.getInstance(project).getFileInAbsolutePath(path.trim());
            if (absoluteFile != null){
                result.add(PsiManager.getInstance(project).findFile(absoluteFile));
                continue;
            }
        }
        return result.toArray(new PsiElement[0]);

    }



    @Override
    public @Nullable @Nls(capitalization = Nls.Capitalization.Title) String getActionText(@NotNull DataContext context) {
        return GotoDeclarationHandler.super.getActionText(context);
    }

    /**
     * 过滤sourceElement，是不是来自xml文件的liteflow.ruleSource跳转请求
     * @param psiElement
     * @return
     */
    private boolean isXmlTarget(PsiElement psiElement) {
        if (!(psiElement.getContainingFile() instanceof XmlFile)){
            return false;
        }
        if (!(psiElement instanceof XmlToken)){
            return false;
        }
        try{
            XmlTag parent_x3 = ((XmlTag) psiElement.getParent().getParent().getParent());
            XmlTag parent_x4 = ((XmlTag) psiElement.getParent().getParent().getParent().getParent());
            if (!parent_x3.getName().equals("property") || !parent_x4.getName().equals("bean")){
                return false;
            }
            if (!parent_x3.getAttributeValue("name").equals("ruleSource") && !parent_x3.getAttributeValue("name").equals("rule-source")){
                return false;
            }
            if (!parent_x4.getAttributeValue("class").equals(Clazz.LiteFlowConfig)){
                return false;
            }
            if (((XmlToken)psiElement.getPrevSibling()).getTokenType() != XmlElementType.XML_ATTRIBUTE_VALUE_START_DELIMITER){
                return false;
            }
            if (((XmlToken)psiElement.getNextSibling()).getTokenType() != XmlElementType.XML_ATTRIBUTE_VALUE_END_DELIMITER){
                return false;
            }
        }catch (Exception ignore){return false;}
        return true;
    }

    /**
     * 过滤sourceElement，是不是来自yaml文件的liteflow.ruleSource跳转请求
     * @param psiElement
     * @return
     */
    private boolean isYamlTarget(PsiElement psiElement) {
        if (!(psiElement.getContainingFile() instanceof YAMLFile)){
            return false;
        }
        if (!(psiElement instanceof LeafPsiElement)){
            return false;
        }
        try{
            String parent_x2 = ((YAMLKeyValueImpl) psiElement.getParent().getParent()).getName();
            String parent_x4 = ((YAMLKeyValueImpl) psiElement.getParent().getParent().getParent().getParent()).getName();
            if (!((parent_x2.equals("ruleSource") || parent_x2.equals("rule-source"))  && parent_x4.equals("liteflow"))){
                return false;
            }
        }catch (Exception ignore){return false;}
        return true;
    }

    /**
     * 过滤sourceElement，是不是来自properties文件的liteflow.ruleSource跳转请求
     * @param psiElement
     * @return
     */
    private boolean isPropertiesTarget(PsiElement psiElement){
        if (!(psiElement.getContainingFile() instanceof PropertiesFile)){
            return false;
        }
        if (!(psiElement instanceof PropertyValueImpl)){
            return false;
        }
        try{
            PropertyImpl context = (PropertyImpl) psiElement.getParent();
            if (!context.getKey().equals("liteflow.ruleSource") && !context.getKey().equals("liteflow.rule-source")){
                return false;
            }
        }catch (Exception ignore){return false;}
        return true;
    }
}
