package top.xystudio.plugin.idea.liteflowx.service;

import com.intellij.openapi.components.ServiceManager;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.util.text.StringUtil;
import com.intellij.psi.*;
import com.intellij.psi.search.GlobalSearchScope;
import com.intellij.util.xml.DomFileElement;
import com.intellij.util.xml.DomService;
import org.jetbrains.annotations.NotNull;
import top.xystudio.plugin.idea.liteflowx.constant.Annotation;
import top.xystudio.plugin.idea.liteflowx.constant.Clazz;
import top.xystudio.plugin.idea.liteflowx.dom.modal.Chain;
import top.xystudio.plugin.idea.liteflowx.dom.modal.Flow;
import top.xystudio.plugin.idea.liteflowx.dom.modal.Node;
import top.xystudio.plugin.idea.liteflowx.dom.modal.Nodes;
import top.xystudio.plugin.idea.liteflowx.util.StringUtils;

import java.io.Serializable;
import java.util.*;
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
     * 寻找所有的LiteFlowChain
     * @return
     */
    public PsiElement[] findAllLiteFlowChain(){
        Collection<PsiElement> result = new ArrayList<>();
        List<DomFileElement<Flow>> flows = DomService.getInstance().getFileElements(Flow.class, this.project, GlobalSearchScope.allScope(this.project));
        for (DomFileElement<Flow> flow : flows) {
            for (Chain chain : flow.getRootElement().getChains()) {
                if (chain.getName().getStringValue() != null && StringUtil.isNotEmpty(chain.getName().getStringValue())) {
                    result.add(chain.getXmlTag());
                }
            }
        }
        return result.toArray(new PsiElement[0]);
    }

    /**
     * 寻找所有的LiteFlowComponent
     * @return
     */
    public PsiClass[] findAllLiteFlowComponent(){
        Collection<PsiClass> result = new ArrayList<>();
        Collection<PsiClass> components = javaService.getClassesByAnnotationQualifiedName(Annotation.Component);
        Collection<PsiClass> liteFlowComponents = javaService.getClassesByAnnotationQualifiedName(Annotation.LiteflowComponent);

        // 根据xml文件定义的node也归为Component
        List<DomFileElement<Flow>> flows = DomService.getInstance().getFileElements(Flow.class, this.project, GlobalSearchScope.allScope(this.project));
        for (DomFileElement<Flow> flow : flows) {
            Nodes nodes = flow.getRootElement().getNodes();
            for (Node node : nodes.getNodeList()) {
                String clazzValue = node.getClazz().getStringValue();
                if (clazzValue == null) {continue;}
                PsiClass aClass = javaService.getClassByQualifiedName(clazzValue);
                if (aClass == null) {continue;}
                result.add(aClass);
            }
        }

        result.addAll(components);
        result.addAll(liteFlowComponents);
        return result.stream().distinct().filter(this::isLiteFlowClass).toArray(PsiClass[]::new);
    }

    /**
     * 根据Class获取LiteFlowComponent的名称
     * @param psiClass
     * @return
     */
    public String getLiteFlowComponentNameByPsiClass(@NotNull PsiClass psiClass){

        if (!this.isLiteFlowClass(psiClass)){
            return null;
        }

        String className = psiClass.getName();
        if (className == null){
            return null;
        }

        String componentValue = JavaService.getInstance(this.project).getAnnotationAttributeValueByClass(psiClass, Annotation.Component, "value");
        if (componentValue != null){
            /* 如果获取的value值为空，则默认使用字符串首字母小写的Class名称 */
            if (componentValue.equals("")){
                componentValue = StringUtils.lowerFirst(className);
            }
            return componentValue;
        }

        String liteFlowComponentValue =
                JavaService.getInstance(this.project).getAnnotationAttributeValueByClass(psiClass, Annotation.LiteflowComponent, "value");
        String liteFlowComponentId =
                JavaService.getInstance(this.project).getAnnotationAttributeValueByClass(psiClass, Annotation.LiteflowComponent, "id");

        String name = StringUtil.isEmpty(liteFlowComponentValue)? liteFlowComponentId : liteFlowComponentValue;
        if (name != null){
            /* 如果获取的value或者id值为空，则默认使用字符串首字母小写的Class名称 */
            if (name.equals("")){
                name = StringUtils.lowerFirst(className);
            }
            return name;
        }

        // 根据xml文件定义的node也归为Component
        List<DomFileElement<Flow>> flows = DomService.getInstance().getFileElements(Flow.class, this.project, GlobalSearchScope.allScope(this.project));
        for (DomFileElement<Flow> flow : flows) {
            Nodes nodes = flow.getRootElement().getNodes();
            for (Node node : nodes.getNodeList()) {
                String clazzValue = node.getClazz().getStringValue();
                String idValue = node.getId().getStringValue();
                if (psiClass.getQualifiedName()==null || clazzValue == null || idValue==null) {
                    continue;
                }
                if (psiClass.getQualifiedName().equals(clazzValue)){
                    return idValue;
                }
            }
        }

        return null;
    }

    /**
     * 判断是不是LiteFlowClass
     * 如果继承了NodeComponent或NodeSwitchComponent，则判断为是
     * 如果没继承以上两个Class，而使用LiteFlowCmpDefine和LiteflowSwitchCmpDefine的注解，同样判断为是
     * 否则为不是
     * @param psiClass
     * @return
     */
    public boolean isLiteFlowClass(@NotNull PsiClass psiClass){
        if (psiClass.getText().contains("abstract class")){
            return false;
        }
        PsiClass nodeComponent = JavaService.getInstance(project).getClassByQualifiedName(Clazz.NodeComponent);
        PsiClass nodeSwitchComponent = JavaService.getInstance(project).getClassByQualifiedName(Clazz.NodeSwitchComponent);
        if ((nodeSwitchComponent != null && psiClass.isInheritor(nodeSwitchComponent, true)) || (nodeComponent != null && psiClass.isInheritor(nodeComponent, true))){
            return true;
        }
        PsiAnnotation liteflowCmpDefine = psiClass.getAnnotation(Annotation.LiteflowCmpDefine);
        PsiAnnotation liteflowSwitchCmpDefine = psiClass.getAnnotation(Annotation.LiteflowSwitchCmpDefine);
        return liteflowCmpDefine != null || liteflowSwitchCmpDefine != null;
    }

}
