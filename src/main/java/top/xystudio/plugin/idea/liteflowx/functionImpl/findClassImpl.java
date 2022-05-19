package top.xystudio.plugin.idea.liteflowx.functionImpl;

import com.intellij.openapi.project.Project;
import com.intellij.psi.PsiClass;
import com.intellij.psi.PsiElement;
import top.xystudio.plugin.idea.liteflowx.constant.Annotation;
import top.xystudio.plugin.idea.liteflowx.parse.RegexNodeEntity;
import top.xystudio.plugin.idea.liteflowx.service.JavaService;
import top.xystudio.plugin.idea.liteflowx.service.LiteFlowService;
import top.xystudio.plugin.idea.liteflowx.util.StringUtils;

import java.util.function.BiFunction;

/**
 * 实现寻找Class方法
 */
public class findClassImpl implements BiFunction<Project, RegexNodeEntity, PsiElement> {
    @Override
    public PsiElement apply(Project project, RegexNodeEntity regexNodeEntity) {
        String componentId = regexNodeEntity.getId();

        if (componentId == null || componentId.equals("")){
            return null;
        }

        PsiClass[] allComponent = LiteFlowService.getInstance(project).findAllLiteFlowComponent();

        for (PsiClass psiClass : allComponent) {
            String componentValue =
                    JavaService.getInstance(project).getAnnotationAttributeValueByClass(psiClass, Annotation.Component, "value");

            if (componentValue != null){

                /** 如果获取的value值为空，则默认使用字符串首字母小写的Class名称 */
                if (componentValue.equals("")){
                    componentValue = StringUtils.lowerFirst(psiClass.getName());
                }

                if (componentValue.equals(componentId)){
                    return psiClass;
                }

            }

            String liteFlowComponentValue =
                    JavaService.getInstance(project).getAnnotationAttributeValueByClass(psiClass, Annotation.LiteflowComponent, "value");

            if (liteFlowComponentValue != null){

                /** 如果获取的value值为空，则默认使用字符串首字母小写的Class名称 */
                if (liteFlowComponentValue.equals("")){
                    liteFlowComponentValue = StringUtils.lowerFirst(psiClass.getName());
                }

                if (liteFlowComponentValue.equals(componentId)){
                    return psiClass;
                }

            }

        }
        return null;
    }
}
