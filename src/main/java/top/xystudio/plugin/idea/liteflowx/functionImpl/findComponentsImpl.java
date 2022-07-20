package top.xystudio.plugin.idea.liteflowx.functionImpl;

import com.intellij.openapi.project.Project;
import com.intellij.psi.PsiClass;
import com.intellij.psi.PsiElement;
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
        List<PsiClass> result = new ArrayList<>();
        if (name == null || name.equals("")){
            return result;
        }
        PsiClass[] allComponent = LiteFlowService.getInstance(project).findAllLiteFlowComponent();
        for (PsiClass psiClass : allComponent) {

            String componentName = LiteFlowService.getInstance(project).getLiteFlowComponentNameByPsiClass(psiClass);
            if (componentName != null && componentName.equals(name)){
                result.add(psiClass);
            }

        }
        return result;
    }
}
