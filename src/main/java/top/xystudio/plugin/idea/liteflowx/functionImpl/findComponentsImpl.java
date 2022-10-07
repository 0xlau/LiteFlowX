package top.xystudio.plugin.idea.liteflowx.functionImpl;

import com.intellij.openapi.project.Project;
import com.intellij.psi.PsiClass;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiMethod;
import com.intellij.psi.xml.XmlTag;
import top.xystudio.plugin.idea.liteflowx.service.LiteFlowService;

import java.util.ArrayList;
import java.util.List;
import java.util.function.BiFunction;

/**
 * 实现寻找Component方法
 */
public class findComponentsImpl implements BiFunction<Project, String, List<? extends PsiElement>> {
    @Override
    public List<? extends PsiElement> apply(Project project, String name) {
        List<PsiElement> result = new ArrayList<>();
        if (name == null || name.equals("")){
            return result;
        }
        PsiElement[] allComponent = LiteFlowService.getInstance(project).findAllLiteFlowComponent();
        for (PsiElement psiElement : allComponent) {
            if (psiElement instanceof PsiClass){
                String componentName = LiteFlowService.getInstance(project).getLiteFlowComponentNameByPsiClass((PsiClass) psiElement);
                if (componentName != null && componentName.equals(name)){
                    result.add(psiElement);
                }
            } else if (psiElement instanceof PsiMethod) {
                String componentName = LiteFlowService.getInstance(project).getLiteFlowComponentNameByPsiMethod((PsiMethod) psiElement);
                if (componentName != null && componentName.equals(name)){
                    result.add(psiElement);
                }
            } else if (psiElement instanceof XmlTag){
                String componentName = LiteFlowService.getInstance(project).getLiteFlowComponentNameByXmlTag((XmlTag) psiElement);
                if (componentName != null && componentName.equals(name)){
                    result.add(psiElement);
                }
            }
        }
        return result;
    }
}
