package top.xystudio.plugin.idea.liteflowx.service;

import com.intellij.openapi.components.Service;
import com.intellij.openapi.components.ServiceManager;
import com.intellij.openapi.module.Module;
import com.intellij.openapi.module.ModuleManager;
import com.intellij.openapi.project.Project;
import com.intellij.psi.*;
import com.intellij.psi.impl.compiled.ClsReferenceExpressionImpl;
import com.intellij.psi.impl.source.tree.java.PsiReferenceExpressionImpl;
import com.intellij.psi.search.GlobalSearchScope;
import com.intellij.psi.search.searches.AnnotatedElementsSearch;
import org.jetbrains.annotations.NotNull;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.stream.Collectors;

@Service(Service.Level.PROJECT)
public final class JavaService {

    private final Project project;

    private final JavaPsiFacade javaPsiFacade;

    public JavaService(Project project) {
        this.project = project;
        this.javaPsiFacade = JavaPsiFacade.getInstance(project);
    }

    /**
     * 获取一个全名为qualifiedName的Class
     * @param qualifiedName 类的全名
     * @return 返回一个PsiClass对象
     */
    public PsiClass getClassByQualifiedName(@NotNull String qualifiedName) {
        return this.getClassByQualifiedName(qualifiedName, ModuleManager.getInstance(this.project).getModules());
    }

    /**
     * 获取一个全名为qualifiedName的Class
     * @param qualifiedName 类的全名
     * @return 返回一个PsiClass对象
     */
    public PsiClass getClassByQualifiedName(@NotNull String qualifiedName, Module[] module) {
        for (Module _m : module) {
            PsiClass aClass = javaPsiFacade.findClass(qualifiedName, GlobalSearchScope.moduleWithDependenciesAndLibrariesScope(_m));
            if (aClass != null){
                return aClass;
            }
        }
        return null;
    }

    /**
     * 获取含有指定Annotation的所有Class
     * @param qualifiedName 注解的全名
     * @return 含有指定qualifiedName的注解的所有Class
     */
    public Collection<PsiClass> getClassesByAnnotationQualifiedName(@NotNull String qualifiedName) {
        return this.getClassesByAnnotationQualifiedName(qualifiedName, ModuleManager.getInstance(this.project).getModules());
    }

    /**
     * 获取含有指定Annotation的所有Class
     * @param qualifiedName 注解的全名
     * @return 含有指定qualifiedName的注解的所有Class
     */
    public Collection<PsiClass> getClassesByAnnotationQualifiedName(@NotNull String qualifiedName, Module[] module) {
        Collection<PsiClass> result = new ArrayList<>();
        PsiClass psiClass = getClassByQualifiedName(qualifiedName);
        if (psiClass == null) {
            return result;
        }
        for (Module _m : module) {
            result.addAll(AnnotatedElementsSearch.searchPsiClasses(psiClass, GlobalSearchScope.moduleWithDependenciesAndLibrariesScope(_m)).findAll());
        }
        return result.stream().distinct().collect(Collectors.toList());
    }

    /**
     * 获取含有指定Annotation的所有Method
     * @param qualifiedName 注解的全名
     * @return 含有指定qualifiedName的注解的所有Method
     */
    public Collection<PsiMethod> getMethodsByAnnotationQualifiedName(@NotNull String qualifiedName) {
        return this.getMethodsByAnnotationQualifiedName(qualifiedName, ModuleManager.getInstance(this.project).getModules());
    }

    /**
     * 获取含有指定Annotation的所有Method
     * @param qualifiedName 注解的全名
     * @return 含有指定qualifiedName的注解的所有Method
     */
    public Collection<PsiMethod> getMethodsByAnnotationQualifiedName(@NotNull String qualifiedName, Module[] module) {
        Collection<PsiMethod> result = new ArrayList<>();
        PsiClass psiClass = getClassByQualifiedName(qualifiedName);
        if (psiClass == null) {
            return result;
        }
        for (Module _m : module) {
            result.addAll(AnnotatedElementsSearch.searchPsiMethods(psiClass, GlobalSearchScope.moduleWithDependenciesAndLibrariesScope(_m)).findAll());
        }
        return result.stream().distinct().collect(Collectors.toList());
    }


    /**
     * 获取Class的指定注解属性值
     * @param psiModifierListOwner Class类对象
     * @param qualifiedName 注解的全名
     * @param attribute 属性名
     * @return 属性值
     */
    public String getAnnotationAttributeValue(@NotNull PsiModifierListOwner psiModifierListOwner, @NotNull String qualifiedName, String attribute) {
        PsiAnnotation annotation = psiModifierListOwner.getAnnotation(qualifiedName);
        if (annotation == null) {
            return null;
        }
        PsiAnnotationMemberValue attributeValue = annotation.findAttributeValue(attribute);
        if (attributeValue == null) {
            return null;
        }
        /*处理注解value是引用表达式*/
        if (attributeValue instanceof PsiReferenceExpressionImpl || attributeValue instanceof ClsReferenceExpressionImpl) {
            PsiElement resolve = ((PsiReferenceExpression) attributeValue).resolve();
            if (resolve == null) {
                return null;
            }
            String[] split = resolve.getText().split("=");
            int length = split.length;
            if (length == 2) {
                return split[1].trim().replace("\"", "").replace(";", "");
            } else if (length == 1) {
                return split[0].trim().replace("\"", "").replace(";", "");
            }
        }


        return attributeValue.getText().replace("\"", "");
    }
}
