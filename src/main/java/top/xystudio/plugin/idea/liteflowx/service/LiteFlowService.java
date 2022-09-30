package top.xystudio.plugin.idea.liteflowx.service;

import com.intellij.openapi.components.ServiceManager;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.util.text.StringUtil;
import com.intellij.psi.PsiAnnotation;
import com.intellij.psi.PsiClass;
import com.intellij.psi.PsiElement;
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
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

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
    private Collection<PsiClass> _getAllLiteFlowPsiClass(){
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
        return result.stream().distinct().collect(Collectors.toList());
    }

    public PsiClass[] findAllLiteFlowNormalComponent(){
        Collection<PsiClass> result = _getAllLiteFlowPsiClass();
        return result.stream().distinct().filter(this::isLiteFlowNormalComponentClass).toArray(PsiClass[]::new);
    }

    public PsiClass[] findAllLiteFlowIfComponent(){
        Collection<PsiClass> result = _getAllLiteFlowPsiClass();
        return result.stream().distinct().filter(this::isLiteFlowIfComponentClass).toArray(PsiClass[]::new);
    }

    public PsiClass[] findAllLiteFlowSwitchComponent(){
        Collection<PsiClass> result = _getAllLiteFlowPsiClass();
        return result.stream().distinct().filter(this::isLiteFlowSwitchComponentClass).toArray(PsiClass[]::new);
    }

    public PsiClass[] findAllLiteFlowForComponent(){
        Collection<PsiClass> result = _getAllLiteFlowPsiClass();
        return result.stream().distinct().filter(this::isLiteFlowForComponentClass).toArray(PsiClass[]::new);
    }

    public PsiClass[] findAllLiteFlowWhileComponent(){
        Collection<PsiClass> result = _getAllLiteFlowPsiClass();
        return result.stream().distinct().filter(this::isLiteFlowWhileComponentClass).toArray(PsiClass[]::new);
    }

    public PsiClass[] findAllLiteFlowBreakComponent(){
        Collection<PsiClass> result = _getAllLiteFlowPsiClass();
        return result.stream().distinct().filter(this::isLiteFlowBreakComponentClass).toArray(PsiClass[]::new);
    }

    public PsiClass[] findAllLiteFlowComponent(){
        Collection<PsiClass> result = _getAllLiteFlowPsiClass();
        return result.stream().distinct().filter(this::isLiteFlowClass).toArray(PsiClass[]::new);
    }

    /**
     * 根据Class获取LiteFlowComponent的名称
     * @param psiClass psi类
     * @return 返回LiteFlowComponent的名称
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

    private boolean _isLiteFlow(@NotNull PsiClass psiClass, @NotNull String clazz, @NotNull String annotation){
        if (psiClass.getText().contains("abstract class")){
            return false;
        }
        PsiClass nodeClazz = JavaService.getInstance(project).getClassByQualifiedName(clazz);
        if (nodeClazz != null && psiClass.isInheritor(nodeClazz, true)){
            return true;
        }
        PsiAnnotation psiClassAnnotation = psiClass.getAnnotation(annotation);
        return psiClassAnnotation != null;
    }

    public boolean isLiteFlowNormalComponentClass(@NotNull PsiClass psiClass){
        return _isLiteFlow(psiClass, Clazz.NodeComponent, Annotation.LiteflowCmpDefine);
    }

    public boolean isLiteFlowSwitchComponentClass(@NotNull PsiClass psiClass){
        return _isLiteFlow(psiClass, Clazz.NodeSwitchComponent, Annotation.LiteflowSwitchCmpDefine);
    }

    public boolean isLiteFlowIfComponentClass(@NotNull PsiClass psiClass){
        return _isLiteFlow(psiClass, Clazz.NodeIfComponent, Annotation.LiteflowIfCmpDefine);
    }

    public boolean isLiteFlowForComponentClass(@NotNull PsiClass psiClass){
        return _isLiteFlow(psiClass, Clazz.NodeForComponent, Annotation.LiteflowForCmpDefine);
    }

    public boolean isLiteFlowWhileComponentClass(@NotNull PsiClass psiClass){
        return _isLiteFlow(psiClass, Clazz.NodeWhileComponent, Annotation.LiteflowWhileCmpDefine);
    }

    public boolean isLiteFlowBreakComponentClass(@NotNull PsiClass psiClass){
        return _isLiteFlow(psiClass, Clazz.NodeBreakComponent, Annotation.LiteflowBreakCmpDefine);
    }

    /**
     * 判断是不是LiteFlowClass
     * 如果继承了LiteFlow的指定Component，则判断为是
     * 如果没继承以上两个Class，而使用声明式注解，同样判断为是
     * 否则为不是
     * @param psiClass psi类
     * @return 返回true或者false
     */
    public boolean isLiteFlowClass(@NotNull PsiClass psiClass){
        return (
                isLiteFlowIfComponentClass(psiClass)    ||
                isLiteFlowSwitchComponentClass(psiClass)||
                isLiteFlowForComponentClass(psiClass)   ||
                isLiteFlowWhileComponentClass(psiClass) ||
                isLiteFlowBreakComponentClass(psiClass) ||
                isLiteFlowNormalComponentClass(psiClass)
        );
    }

}
