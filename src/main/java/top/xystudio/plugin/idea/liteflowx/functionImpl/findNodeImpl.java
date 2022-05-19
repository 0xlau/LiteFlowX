package top.xystudio.plugin.idea.liteflowx.functionImpl;

import com.intellij.openapi.project.Project;
import com.intellij.psi.PsiElement;
import com.intellij.psi.search.GlobalSearchScope;
import com.intellij.util.xml.DomFileElement;
import com.intellij.util.xml.DomService;
import top.xystudio.plugin.idea.liteflowx.dom.modal.Flow;
import top.xystudio.plugin.idea.liteflowx.dom.modal.Node;
import top.xystudio.plugin.idea.liteflowx.dom.modal.Nodes;
import top.xystudio.plugin.idea.liteflowx.parse.RegexNodeEntity;

import java.util.List;
import java.util.function.BiFunction;

/**
 * 实现寻找Node方法
 */
public class findNodeImpl implements BiFunction<Project, RegexNodeEntity, PsiElement> {
    @Override
    public PsiElement apply(Project project, RegexNodeEntity regexNodeEntity) {
        String componentId = regexNodeEntity.getId();
        if (componentId == null || componentId.equals("")){
            return null;
        }
        List<DomFileElement<Flow>> flows = DomService.getInstance().getFileElements(Flow.class, project, GlobalSearchScope.allScope(project));
        for (DomFileElement<Flow> flow : flows) {
            Nodes nodes = flow.getRootElement().getNodes();
            if (nodes != null){
                for (Node node : nodes.getNodeList()) {
                    if (node.getId().getStringValue().equals(componentId)){
                        return node.getXmlTag();
                    }
                }
            }
        }
        return null;
    }
}
