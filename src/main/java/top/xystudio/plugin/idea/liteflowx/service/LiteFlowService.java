package top.xystudio.plugin.idea.liteflowx.service;

import com.intellij.openapi.components.ServiceManager;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.util.text.StringUtil;
import com.intellij.psi.PsiClass;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiMethod;
import com.intellij.psi.search.GlobalSearchScope;
import com.intellij.psi.xml.XmlTag;
import com.intellij.util.xml.DomFileElement;
import com.intellij.util.xml.DomService;
import org.apache.commons.lang.ArrayUtils;
import org.jetbrains.annotations.NotNull;
import top.xystudio.plugin.idea.liteflowx.constant.Annotation;
import top.xystudio.plugin.idea.liteflowx.constant.Clazz;
import top.xystudio.plugin.idea.liteflowx.constant.LiteFlowMethodEnum;
import top.xystudio.plugin.idea.liteflowx.constant.NodeTypeEnum;
import top.xystudio.plugin.idea.liteflowx.dom.modal.Chain;
import top.xystudio.plugin.idea.liteflowx.dom.modal.Flow;
import top.xystudio.plugin.idea.liteflowx.dom.modal.Node;
import top.xystudio.plugin.idea.liteflowx.dom.modal.Nodes;
import top.xystudio.plugin.idea.liteflowx.util.StringUtils;
import top.xystudio.plugin.idea.liteflowx.util.XmlUtils;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class LiteFlowService implements Serializable {

    private static final long serialVersionUID = 1L;

    private final Project project;

    private final JavaService javaService;

    public LiteFlowService(Project project){
        this.project = project;
        this.javaService = JavaService.getInstance(project);
    }

    public static LiteFlowService getInstance(@NotNull Project project){
        return ServiceManager.getService(project, LiteFlowService.class);
    }

    /**
     * 寻找所有的LiteFlowChain
     * @return 返回所有Chain的XmlTag
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
     * 寻找所有的LiteFlowComponent涉及到的PsiClass（!脚本组件可能扫不到）
     * @return 返回所有PsiClass
     */

    public PsiElement[] findAllLiteFlowComponent(){
        Collection<PsiElement> result = new ArrayList<>();
        Collection<PsiClass> springComponents = javaService.getClassesByAnnotationQualifiedName(Annotation.Component);
        Collection<PsiClass> liteFlowComponents = javaService.getClassesByAnnotationQualifiedName(Annotation.LiteflowComponent);
        Collection<PsiMethod> methodComponents = javaService.getMethodsByAnnotationQualifiedName(Annotation.LiteflowMethod);

        // 根据xml文件定义的node也归为Component
        List<DomFileElement<Flow>> flows = DomService.getInstance().getFileElements(Flow.class, this.project, GlobalSearchScope.allScope(this.project));
        for (DomFileElement<Flow> flow : flows) {
            Nodes nodes = flow.getRootElement().getNodes();
            for (Node node : nodes.getNodeList()) {
                String id = node.getId().getStringValue();
                if (StringUtil.isEmpty(id)){continue;}

                String type = node.getType().getStringValue();
                if (ArrayUtils.contains(NodeTypeEnum.SCRIPTS, type)){
                    result.add(node.getXmlTag());
                    continue;
                }

                String clazzValue = node.getClazz().getStringValue();
                if (clazzValue == null) {continue;}
                PsiClass aClass = javaService.getClassByQualifiedName(clazzValue);
                if (aClass == null) {continue;}
                result.add(aClass);
            }
        }

        result.addAll(springComponents);
        result.addAll(liteFlowComponents);
        result.addAll(methodComponents);
        return result.stream().distinct().filter(this::isLiteFlowComponent).toArray(PsiElement[]::new);
    }

    /**
     * 根据XmlTag获取LiteFlowComponent的名称
     * @param xmlTag xmlTag
     * @return 返回LiteFlowComponent的名称
     */
    public String getLiteFlowComponentNameByXmlTag(@NotNull XmlTag xmlTag){

        Node node = XmlUtils.transformToDomElement(xmlTag, Node.class);
        if (node == null){
            return null;
        }
        String componentValue = node.getId().getStringValue();
        if (StringUtil.isEmpty(componentValue)){
            return null;
        }
        return componentValue;
    }

    /**
     * 根据Method获取LiteFlowComponent的名称
     * @param psiMethod psi方法
     * @return 返回LiteFlowComponent的名称
     */
    public String getLiteFlowComponentNameByPsiMethod(@NotNull PsiMethod psiMethod){
        if (!this.isLiteFlowComponent(psiMethod)){
            return null;
        }
        String componentValue = javaService.getAnnotationAttributeValue(psiMethod, Annotation.LiteflowMethod, "nodeId");
        if (StringUtil.isEmpty(componentValue)){
            return null;
        }
        return componentValue;
    }
    
    /**
     * 根据Class获取LiteFlowComponent的名称
     * @param psiClass psi类
     * @return 返回LiteFlowComponent的名称
     */
    public String getLiteFlowComponentNameByPsiClass(@NotNull PsiClass psiClass){

        if (!this.isLiteFlowComponent(psiClass)){
            return null;
        }

        String className = psiClass.getName();
        if (className == null){
            return null;
        }

        String componentValue = javaService.getAnnotationAttributeValue(psiClass, Annotation.Component, "value");
        if (componentValue != null){
            /* 如果获取的value值为空，则默认使用字符串首字母小写的Class名称 */
            if (componentValue.equals("")){
                componentValue = StringUtils.lowerFirst(className);
            }
            return componentValue;
        }

        String liteFlowComponentValue =
                javaService.getAnnotationAttributeValue(psiClass, Annotation.LiteflowComponent, "value");
        String liteFlowComponentId =
                javaService.getAnnotationAttributeValue(psiClass, Annotation.LiteflowComponent, "id");

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

    private boolean _isLiteFlow(@NotNull PsiElement psiElement, @NotNull String clazz, @NotNull String nodeTypeEnum){
        if (psiElement instanceof PsiClass){
            PsiClass psiClass = (PsiClass) psiElement;
            // 排除所有包名以 com.yomahub.liteflow.core. 开头的Class
            if (psiClass.getQualifiedName().indexOf("com.yomahub.liteflow.core.") == 0){
                return false;
            }
            // 排除使用了 abstract 的 Class
            if (psiClass.getText().contains("abstract class")){
                return false;
            }
            PsiClass nodeClazz = JavaService.getInstance(project).getClassByQualifiedName(clazz);
            if (nodeClazz != null && psiClass.isInheritor(nodeClazz, true)){
                return true;
            }
            String nodeType = javaService.getAnnotationAttributeValue(psiClass, Annotation.LiteflowCmpDefine, "value");
            if (nodeType == null){
                return false;
            }
            return nodeTypeEnum.equals(nodeType);
        } else if (psiElement instanceof PsiMethod) {
            PsiMethod psiMethod = (PsiMethod) psiElement;
            // 排除所有包名以 com.yomahub.liteflow.core. 开头的Class
            if (psiMethod.getContainingClass().getQualifiedName().indexOf("com.yomahub.liteflow.core.") == 0){
                return false;
            }
            // 排除使用了 abstract 的 Class
            if (psiMethod.getContainingClass().getText().contains("abstract class")){
                return false;
            }
            if (!psiMethod.getContainingClass().hasAnnotation(Annotation.Component) && !psiMethod.getContainingClass().hasAnnotation(Annotation.LiteflowComponent)){
                return false;
            }
            if (psiMethod.getContainingClass().hasAnnotation(Annotation.LiteflowCmpDefine)){
                return false;
            }
            if (psiMethod.getContainingClass().isInheritor(javaService.getClassByQualifiedName(Clazz.NodeComponent), true)){
                return false;
            }
            String liteFlowMethod = javaService.getAnnotationAttributeValue(psiMethod, Annotation.LiteflowMethod, "value");
            if (!ArrayUtils.contains(LiteFlowMethodEnum.NECESSARY_PROCESS, liteFlowMethod)){
                return false;
            }
            String nodeId = javaService.getAnnotationAttributeValue(psiMethod, Annotation.LiteflowMethod, "nodeId");
            if (StringUtil.isEmpty(nodeId)){
                return false;
            }
            String nodeType = javaService.getAnnotationAttributeValue(psiMethod, Annotation.LiteflowMethod, "nodeType");
            return nodeTypeEnum.equals(nodeType);
        } else {
            return false;
        }

    }

    public boolean isLiteFlowNormalComponent(@NotNull PsiElement psiElement){
        return _isLiteFlow(psiElement, Clazz.NodeComponent, NodeTypeEnum.COMMON);
    }

    public boolean isLiteFlowSwitchComponent(@NotNull PsiElement psiElement){
        return _isLiteFlow(psiElement, Clazz.NodeSwitchComponent, NodeTypeEnum.SWITCH);
    }

    public boolean isLiteFlowIfComponent(@NotNull PsiElement psiElement){
        return _isLiteFlow(psiElement, Clazz.NodeIfComponent, NodeTypeEnum.IF);
    }

    public boolean isLiteFlowForComponent(@NotNull PsiElement psiElement){
        return _isLiteFlow(psiElement, Clazz.NodeForComponent, NodeTypeEnum.FOR);
    }

    public boolean isLiteFlowWhileComponent(@NotNull PsiElement psiElement){
        return _isLiteFlow(psiElement, Clazz.NodeWhileComponent, NodeTypeEnum.WHILE);
    }

    public boolean isLiteFlowBreakComponent(@NotNull PsiElement psiElement){
        return _isLiteFlow(psiElement, Clazz.NodeBreakComponent, NodeTypeEnum.BREAK);
    }

    private boolean isLiteFlowScriptComponent(PsiElement psiElement) {
        if (!(psiElement instanceof XmlTag)){
            return false;
        }
        Node node = XmlUtils.transformToDomElement(psiElement, Node.class);
        if (node == null){
            return false;
        }
        String id = node.getId().getStringValue();
        String type = node.getType().getStringValue();
        if (ArrayUtils.contains(NodeTypeEnum.SCRIPTS, type) && StringUtil.isNotEmpty(id)){
            return true;
        }
        return false;
    }

    public boolean isLiteFlowMultiComponent(@NotNull PsiClass psiClass){
        if (psiClass.hasAnnotation(Annotation.LiteflowCmpDefine)){
            return false;
        }
        for (PsiMethod method : psiClass.getMethods()) {
            if (isLiteFlowComponent(method)) {
                return true;
            }
        }
        return false;
    }

    /**
     * 判断是不是LiteFlowComponent
     * @param psiElement psi元素
     * @return 返回true或者false
     */
    public boolean isLiteFlowComponent(@NotNull PsiElement psiElement){
        return (
                isLiteFlowIfComponent(psiElement)    ||
                isLiteFlowSwitchComponent(psiElement)||
                isLiteFlowForComponent(psiElement)   ||
                isLiteFlowWhileComponent(psiElement) ||
                isLiteFlowBreakComponent(psiElement) ||
                isLiteFlowNormalComponent(psiElement)||
                isLiteFlowScriptComponent(psiElement)
        );
    }



}
