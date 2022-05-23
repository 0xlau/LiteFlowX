package top.xystudio.plugin.idea.liteflowx.service;

import com.intellij.openapi.components.ServiceManager;
import com.intellij.openapi.project.Project;
import com.intellij.psi.JavaPsiFacade;
import com.intellij.psi.PsiAnnotation;
import com.intellij.psi.PsiAnnotationMemberValue;
import com.intellij.psi.PsiClass;
import com.intellij.psi.impl.search.ClassesWithAnnotatedMembersSearcher;
import com.intellij.psi.search.GlobalSearchScope;
import com.intellij.psi.search.searches.AllClassesSearch;
import com.intellij.psi.search.searches.ClassesWithAnnotatedMembersSearch;
import org.jetbrains.annotations.NotNull;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;

public class JavaService implements Serializable {

    private static final long serialVersionUID = 1L;

    private Project project;

    private JavaPsiFacade javaPsiFacade;

    public JavaService(Project project) {
        this.project = project;
        this.javaPsiFacade = JavaPsiFacade.getInstance(project);
    }

    public static JavaService getInstance(@NotNull Project project){
        return ServiceManager.getService(project, JavaService.class);
    }

    /**
     * 获取项目里的所有Class
     * @return
     */
    public Collection<PsiClass> getAllClasses(){
        return AllClassesSearch.search(GlobalSearchScope.projectScope(project), project).findAll();
    }

    /**
     * 获取一个全名为qualifiedName的Class
     * @param qualifiedName
     * @return
     */
    public PsiClass getClassByQualifiedName(@NotNull String qualifiedName){
        return javaPsiFacade.findClass(qualifiedName, GlobalSearchScope.allScope(this.project));
    }

    /**
     * 获取含有指定Annotation的所有Class
     * @param qualifiedName 注解的全名
     * @return 含有指定qualifiedName的注解的所有Class
     */
    public Collection<PsiClass> getClassesByAnnotationQualifiedName(@NotNull String qualifiedName){
        PsiClass psiClass = getClassByQualifiedName(qualifiedName);
        if (psiClass == null){
            return new ArrayList<>();
        }
        return ClassesWithAnnotatedMembersSearch.search(psiClass, GlobalSearchScope.projectScope(this.project)).findAll();
    }

    /**
     * 获取Class的指定注解属性值
     * @param psiClass Class类对象
     * @param qualifiedName 注解的全名
     * @param attribute 属性名
     * @return 属性值
     */
    public String getAnnotationAttributeValueByClass(@NotNull PsiClass psiClass, @NotNull String qualifiedName, String attribute){
        PsiAnnotation annotation = psiClass.getAnnotation(qualifiedName);
        if (annotation == null){
            return null;
        }
        PsiAnnotationMemberValue attributeValue = annotation.findAttributeValue(attribute);
        if (attributeValue == null){
            return null;
        }
        return attributeValue.getText().replace("\"", "");
    }
}
