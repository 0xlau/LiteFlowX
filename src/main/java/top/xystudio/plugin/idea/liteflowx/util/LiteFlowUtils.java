package top.xystudio.plugin.idea.liteflowx.util;

import com.intellij.openapi.project.Project;
import com.intellij.psi.PsiElement;

import java.util.List;
import java.util.Optional;
import java.util.function.BiFunction;

public class LiteFlowUtils {

    /**
     * 根据名称 查找对应的目标PsiElement
     * @param project
     * @param name
     * @param biFunction
     * @return
     */
    public static Optional<? extends PsiElement> findTargetByName(Project project, String name, BiFunction<Project, String, List<? extends PsiElement>> biFunction) {
        List<? extends PsiElement> result = biFunction.apply(project, name);
        if (result == null){
            return Optional.empty();
        }
        return Optional.ofNullable(result.size() == 0? null : result.get(0));
    }

    /**
     * 根据名称 查找对应的目标PsiElement
     * @param project
     * @param name
     * @param biFunction
     * @return
     */
    public static Optional<? extends List<? extends PsiElement>> findTargetsByName(Project project, String name, BiFunction<Project, String, List<? extends PsiElement>> biFunction) {
        List<? extends PsiElement> result = biFunction.apply(project, name);
        if (result == null){
            return Optional.empty();
        }
        return Optional.ofNullable(result.size() == 0? null : result);
    }
}
