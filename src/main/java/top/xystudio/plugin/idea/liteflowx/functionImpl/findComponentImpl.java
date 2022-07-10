package top.xystudio.plugin.idea.liteflowx.functionImpl;

import com.intellij.openapi.project.Project;
import com.intellij.psi.PsiClass;
import com.intellij.psi.PsiElement;
import top.xystudio.plugin.idea.liteflowx.service.LiteFlowService;

import java.util.function.BiFunction;

/**
 * 实现寻找Component方法
 */
public class findComponentImpl implements BiFunction<Project, String, PsiElement> {
    @Override
    public PsiElement apply(Project project, String name) {
        String componentId = name;

        if (componentId == null || componentId.equals("")){
            return null;
        }

        PsiClass[] allComponent = LiteFlowService.getInstance(project).findAllLiteFlowComponent();

        for (PsiClass psiClass : allComponent) {

            String componentName = LiteFlowService.getInstance(project).getLiteFlowComponentNameByPsiClass(psiClass);
            if (componentName != null && componentName.equals(componentId)){
                return psiClass;
            }

        }
        return null;
    }
}
