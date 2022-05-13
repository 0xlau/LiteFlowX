package top.xystudio.plugin.idea.liteflowx.util;

import com.intellij.openapi.project.Project;
import com.intellij.psi.JavaPsiFacade;
import com.intellij.psi.PsiAnnotation;
import com.intellij.psi.PsiClass;
import com.intellij.psi.impl.java.stubs.index.JavaAnnotationIndex;
import com.intellij.psi.impl.search.ClassesWithAnnotatedMembersSearcher;
import com.intellij.psi.search.GlobalSearchScope;
import com.intellij.psi.search.searches.AnnotationTargetsSearch;
import com.intellij.psi.search.searches.ClassesWithAnnotatedMembersSearch;
import com.intellij.util.ArrayUtil;
import kotlin.reflect.jvm.internal.impl.load.java.structure.JavaAnnotationArgument;
import kotlin.reflect.jvm.internal.impl.load.java.structure.JavaAnnotationOwner;
import kotlin.reflect.jvm.internal.impl.load.java.structure.JavaArrayAnnotationArgument;
import top.xystudio.plugin.idea.liteflowx.constant.Annotation;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

public class JavaUtils {

    public static PsiClass getClassByQualifiedName(Project project, String qualifiedName){
        return JavaPsiFacade.getInstance(project).findClass(qualifiedName, GlobalSearchScope.allScope(project));
    }

    public static Collection<PsiClass> getClassesByAnnotation(Project project, PsiClass psiClass){
        return ClassesWithAnnotatedMembersSearch.search(psiClass, GlobalSearchScope.projectScope(project)).findAll();
    }

    public static String getAnnotationComponentValue(PsiClass psiClass, PsiAnnotation annotationComponent){
        if (psiClass != null && annotationComponent != null){
            String annotationValue = annotationComponent.findAttributeValue("value").getText().replace("\"", "");
            if (annotationValue.equals("")){
                char[] chars = psiClass.getName().toCharArray();
                chars[0] = Character.toLowerCase(chars[0]);
                return new String(chars);
            }else{
                return annotationValue;
            }
        }
        return null;
    }

}
