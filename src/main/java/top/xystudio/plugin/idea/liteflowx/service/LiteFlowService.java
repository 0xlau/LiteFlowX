package top.xystudio.plugin.idea.liteflowx.service;

import com.intellij.openapi.components.ServiceManager;
import com.intellij.openapi.project.Project;
import com.intellij.psi.*;
import com.intellij.psi.search.GlobalSearchScope;
import com.intellij.util.xml.DomFileElement;
import com.intellij.util.xml.DomService;
import org.jetbrains.annotations.NotNull;
import top.xystudio.plugin.idea.liteflowx.constant.Annotation;
import top.xystudio.plugin.idea.liteflowx.constant.Clazz;
import top.xystudio.plugin.idea.liteflowx.constant.Interface;
import top.xystudio.plugin.idea.liteflowx.dom.modal.Chain;
import top.xystudio.plugin.idea.liteflowx.dom.modal.Flow;
import top.xystudio.plugin.idea.liteflowx.dom.modal.Node;
import top.xystudio.plugin.idea.liteflowx.dom.modal.Nodes;
import top.xystudio.plugin.idea.liteflowx.util.StringUtils;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
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
     * 寻找所有的LiteFlowNode
     * @return
     */
    public PsiElement[] findAllLiteFlowNode(){
        Collection<PsiElement> result = new ArrayList<>();
        List<DomFileElement<Flow>> flows = DomService.getInstance().getFileElements(Flow.class, this.project, GlobalSearchScope.allScope(this.project));
        for (DomFileElement<Flow> flow : flows) {
            Nodes nodes = flow.getRootElement().getNodes();
            for (Node node : nodes.getNodeList()) {
                result.add(node.getXmlTag());
            }
        }
        return result.toArray(new PsiElement[0]);
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
                result.add(chain.getXmlTag());
            }
        }
        return result.toArray(new PsiElement[0]);
    }

    /**
     * 寻找所有的LiteFlowClass
     * @return
     */
    public PsiClass[] findAllLiteFlowClass(){
        Collection<PsiClass> result = javaService.getAllClasses();
        List<PsiClass> collect = result.stream().filter(this::isLiteFlowClass).collect(Collectors.toList());
        return collect.toArray(new PsiClass[0]);
    }

    /**
     * 寻找所有的LiteFlowComponent
     * @return
     */
    public PsiClass[] findAllLiteFlowComponent(){
        Collection<PsiClass> result = new ArrayList<>();
        Collection<PsiClass> components = javaService.getClassesByAnnotationQualifiedName(Annotation.Component);
        Collection<PsiClass> liteFlowComponents = javaService.getClassesByAnnotationQualifiedName(Annotation.LiteflowComponent);
        result.addAll(components);
        result.addAll(liteFlowComponents);
        List<PsiClass> collect = result.stream().filter(this::isLiteFlowClass).collect(Collectors.toList());
        return collect.toArray(new PsiClass[0]);
    }

    /**
     * 寻找所有的LiteFlowSlot
     * @return
     */
    public PsiClass[] findAllLiteFlowSlot(){
        Collection<PsiClass> result = javaService.getAllClasses();
        List<PsiClass> collect = result.stream().filter(this::isLiteFlowSlot).collect(Collectors.toList());
        return collect.toArray(new PsiClass[0]);
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

        String componentValue = JavaService.getInstance(this.project).getAnnotationAttributeValueByClass(psiClass, Annotation.Component, "value");
        if (componentValue != null){
            /** 如果获取的value值为空，则默认使用字符串首字母小写的Class名称 */
            if (componentValue.equals("")){
                componentValue = StringUtils.lowerFirst(psiClass.getName());
            }
            return componentValue;
        }

        String liteFlowComponentValue =
                JavaService.getInstance(this.project).getAnnotationAttributeValueByClass(psiClass, Annotation.LiteflowComponent, "value");
        if (liteFlowComponentValue != null){
            /** 如果获取的value值为空，则默认使用字符串首字母小写的Class名称 */
            if (liteFlowComponentValue.equals("")){
                liteFlowComponentValue = StringUtils.lowerFirst(psiClass.getName());
            }
            return liteFlowComponentValue;
        }
        return null;
    }

    /**
     * 判断是不是LiteFlowClass
     * 如果继承了NodeComponent或NodeCondComponent，则判断为是
     * 如果没继承以上两个Class，而使用LiteFlowCmpDefine和LiteFlowCondCmpDefine的注解，同样判断为是
     * 否则为不是
     * @param psiClass
     * @return
     */
    public boolean isLiteFlowClass(@NotNull PsiClass psiClass){
        String qualifiedName = psiClass.getSuperClass().getQualifiedName();
        if (qualifiedName.equals(Clazz.NodeComponent) || qualifiedName.equals(Clazz.NodeCondComponent)){
            return true;
        }
        PsiAnnotation liteflowCmpDefine = psiClass.getAnnotation(Annotation.LiteflowCmpDefine);
        PsiAnnotation liteflowCondCmpDefine = psiClass.getAnnotation(Annotation.LiteflowCondCmpDefine);
        return liteflowCmpDefine != null || liteflowCondCmpDefine != null;
    }

    /**
     * 判断是不是LiteFlowSlot
     * 如果超类是接口SLot，则判断为是，否则不是
     * @param psiClass
     * @return
     */
    public boolean isLiteFlowSlot(@NotNull PsiClass psiClass){
        while (psiClass.getSuperClassType() != null &&
                !psiClass.getSuperClassType().equals(PsiClassType.getJavaLangObject(PsiManager.getInstance(this.project), GlobalSearchScope.allScope(this.project)))){
            psiClass = psiClass.getSuperClass();
        }
        PsiClassType[] implementsListTypes = psiClass.getImplementsListTypes();
        for (PsiClassType implementsListType : implementsListTypes) {
            if (implementsListType.equals(PsiClassType.getTypeByName(Interface.Slot, this.project, GlobalSearchScope.allScope(this.project)))){
                return true;
            }
        }
        return false;
    }
}
