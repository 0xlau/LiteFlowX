package top.xystudio.plugin.idea.liteflowx.functionImpl;

import com.intellij.openapi.project.Project;
import com.intellij.psi.PsiElement;
import com.intellij.psi.search.GlobalSearchScope;
import com.intellij.psi.xml.XmlTag;
import com.intellij.util.xml.DomFileElement;
import com.intellij.util.xml.DomService;
import top.xystudio.plugin.idea.liteflowx.common.dom.modal.DomChain;
import top.xystudio.plugin.idea.liteflowx.common.dom.modal.DomFlow;

import java.util.ArrayList;
import java.util.List;
import java.util.function.BiFunction;

/**
 * 实现寻找Chain方法
 */
public class findChainsImpl implements BiFunction<Project, String, List<? extends PsiElement>> {
    @Override
    public List<? extends PsiElement> apply(Project project, String name) {
        List<XmlTag> result = new ArrayList<>();
        if (name == null || name.equals("")){
            return result;
        }
        List<DomFileElement<DomFlow>> flows = DomService.getInstance().getFileElements(DomFlow.class, project, GlobalSearchScope.allScope(project));
        for (DomFileElement<DomFlow> flow : flows) {
            for (DomChain domChain : flow.getRootElement().getChains()) {
                if (domChain.getId().getStringValue() != null && domChain.getId().getStringValue().equals(name)){
                    result.add(domChain.getXmlTag());
                }else if (domChain.getName().getStringValue() != null && domChain.getName().getStringValue().equals(name)){
                    result.add(domChain.getXmlTag());
                }
            }
        }
        return result;
    }
}
