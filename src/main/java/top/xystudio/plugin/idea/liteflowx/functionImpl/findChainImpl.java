package top.xystudio.plugin.idea.liteflowx.functionImpl;

import com.intellij.openapi.project.Project;
import com.intellij.psi.PsiElement;
import com.intellij.psi.search.GlobalSearchScope;
import com.intellij.util.xml.DomFileElement;
import com.intellij.util.xml.DomService;
import top.xystudio.plugin.idea.liteflowx.dom.modal.Chain;
import top.xystudio.plugin.idea.liteflowx.dom.modal.Flow;

import java.util.List;
import java.util.function.BiFunction;

/**
 * 实现寻找Chain方法
 */
public class findChainImpl implements BiFunction<Project, String, PsiElement> {
    @Override
    public PsiElement apply(Project project, String name) {
        String componentId = name;
        if (componentId == null || componentId.equals("")){
            return null;
        }
        List<DomFileElement<Flow>> flows = DomService.getInstance().getFileElements(Flow.class, project, GlobalSearchScope.allScope(project));
        for (DomFileElement<Flow> flow : flows) {
            for (Chain chain : flow.getRootElement().getChains()) {
                if (chain.getName().getStringValue().equals(componentId)){
                    return chain.getXmlTag();
                }
            }
        }
        return null;
    }
}
