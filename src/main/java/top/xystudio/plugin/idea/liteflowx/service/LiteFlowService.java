package top.xystudio.plugin.idea.liteflowx.service;

import com.intellij.openapi.components.ServiceManager;
import com.intellij.openapi.project.Project;
import com.intellij.psi.PsiAnnotation;
import com.intellij.psi.PsiClass;
import org.jetbrains.annotations.NotNull;
import top.xystudio.plugin.idea.liteflowx.constant.Annotation;
import top.xystudio.plugin.idea.liteflowx.constant.Clazz;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

public class LiteFlowService implements Serializable {

    private static final long serialVersionUID = 1L;

    private Project project;

    private JavaService javaService;

    public LiteFlowService(Project project){
        this.project = project;
        this.javaService = javaService.getInstance(project);
    }

    public static LiteFlowService getInstance(@NotNull Project project){
        return ServiceManager.getService(project, LiteFlowService.class);
    }

    /**
     * 寻找所有的LiteFlowComponent
     * @return
     */
    public PsiClass[] findAllLiteFlowComponent(){
        Collection<PsiClass> result = new ArrayList<>();
        Collection<PsiClass> components = javaService.getClassesByAnnotationQualifiedName(Annotation.Component);
        Collection<PsiClass> liteFlowComponents = javaService.getClassesByAnnotationQualifiedName(Annotation.LiteflowComponent);
        result.addAll(components);
        result.addAll(liteFlowComponents);
        List<PsiClass> collect = result.stream().filter(this::isLiteFlowComponent).collect(Collectors.toList());
        return collect.toArray(new PsiClass[0]);
    }

    /**
     * 判断是不是LiteFlowComponent
     * 如果继承了NodeComponent或NodeCondComponent，则判断为是
     * 如果没继承以上两个Class，而使用LiteFlowCmpDefine和LiteFlowCondCmpDefine的注解，同样判断为是
     * 否则为不是
     * @param psiClass
     * @return
     */
    public boolean isLiteFlowComponent(PsiClass psiClass){
        String qualifiedName = psiClass.getSuperClass().getQualifiedName();
        if (qualifiedName.equals(Clazz.NodeComponent) || qualifiedName.equals(Clazz.NodeCondComponent)){
            return true;
        }
        PsiAnnotation liteflowCmpDefine = psiClass.getAnnotation(Annotation.LiteflowCmpDefine);
        PsiAnnotation liteflowCondCmpDefine = psiClass.getAnnotation(Annotation.LiteflowCondCmpDefine);
        return liteflowCmpDefine != null || liteflowCondCmpDefine != null;
    }
}
