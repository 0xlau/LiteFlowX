package top.xystudio.plugin.idea.liteflowx.util;

import com.intellij.openapi.project.Project;
import com.intellij.psi.PsiElement;
import top.xystudio.plugin.idea.liteflowx.parse.RegexEntity;
import top.xystudio.plugin.idea.liteflowx.parse.RegexNodeEntity;

import java.util.*;
import java.util.function.BiFunction;

public class LiteFlowUtils {

    /**
     * 根据被解析后得到的List<RegexEntity> 查找对应的目标PsiElement
     * @param project
     * @param regexEntities
     * @return
     */
    public static Optional<? extends PsiElement[]> findTargetsByRegexEntities(Project project, List<RegexEntity> regexEntities, BiFunction<Project, RegexNodeEntity, PsiElement> biFunction) {
        Collection<PsiElement> result = new ArrayList<>();
        List<RegexNodeEntity> nodes = new ArrayList<>();
        for (RegexEntity regexEntity : regexEntities) {
            nodes.add(regexEntity.getItem());
            if (regexEntity.getRealItemArray() != null){
                nodes.addAll(Arrays.asList(regexEntity.getRealItemArray()));
            }
        }
        for (RegexNodeEntity node : nodes){
            PsiElement element = biFunction.apply(project, node);
            if (element != null){
                result.add(element);
            }
        }
        return Optional.of(result.toArray(new PsiElement[0]));
    }

    /**
     * 根据名称 查找对应的目标PsiElement
     * @param project
     * @param name
     * @param biFunction
     * @return
     */
    public static Optional<? extends PsiElement> findTargetByName(Project project, String name, BiFunction<Project, RegexNodeEntity, PsiElement> biFunction) {
        RegexNodeEntity node = new RegexNodeEntity(name, null);
        return Optional.ofNullable(biFunction.apply(project, node));
    }
}
