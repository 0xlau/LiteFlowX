package top.xystudio.plugin.idea.liteflowx.functionImpl;

import com.intellij.openapi.project.Project;
import com.intellij.psi.PsiElement;
import com.intellij.psi.search.GlobalSearchScope;
import com.intellij.psi.xml.XmlTag;
import com.intellij.util.xml.DomFileElement;
import com.intellij.util.xml.DomService;
import top.xystudio.plugin.idea.liteflowx.dom.modal.Chain;
import top.xystudio.plugin.idea.liteflowx.dom.modal.Flow;

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
        List<DomFileElement<Flow>> flows = DomService.getInstance().getFileElements(Flow.class, project, GlobalSearchScope.allScope(project));
        for (DomFileElement<Flow> flow : flows) {
            for (Chain chain : flow.getRootElement().getChains()) {
                if (chain.getId().getStringValue() != null && chain.getId().getStringValue().equals(name)){
                    result.add(chain.getXmlTag());
                }else if (chain.getName().getStringValue() != null && chain.getName().getStringValue().equals(name)){
                    result.add(chain.getXmlTag());
                }
            }
        }
        return result;
    }
}
