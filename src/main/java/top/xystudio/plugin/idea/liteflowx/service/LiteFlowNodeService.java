package top.xystudio.plugin.idea.liteflowx.service;

import com.intellij.openapi.application.ApplicationManager;
import com.intellij.openapi.components.Service;
import com.intellij.openapi.progress.ProgressIndicator;
import com.intellij.openapi.progress.ProgressManager;
import com.intellij.openapi.progress.Task;
import com.intellij.openapi.project.DumbService;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.roots.ProjectRootManager;
import com.intellij.openapi.util.text.StringUtil;
import com.intellij.psi.PsiClass;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiMethod;
import com.intellij.psi.impl.source.xml.XmlTagImpl;
import com.intellij.psi.search.GlobalSearchScope;
import com.intellij.psi.xml.XmlTag;
import com.intellij.util.xml.DomFileElement;
import com.intellij.util.xml.DomService;
import org.apache.commons.collections4.Trie;
import org.apache.commons.collections4.trie.PatriciaTrie;
import org.apache.commons.lang.ArrayUtils;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import top.xystudio.plugin.idea.liteflowx.common.constant.Annotation;
import top.xystudio.plugin.idea.liteflowx.common.constant.Clazz;
import top.xystudio.plugin.idea.liteflowx.common.constant.LiteFlowMethodEnum;
import top.xystudio.plugin.idea.liteflowx.common.constant.NodeTypeEnum;
import top.xystudio.plugin.idea.liteflowx.common.enums.DefineTypeEnum;
import top.xystudio.plugin.idea.liteflowx.common.enums.LiteFlowNodeTypeEnum;
import top.xystudio.plugin.idea.liteflowx.common.metadata.LiteFlowNodeMetadata;
import top.xystudio.plugin.idea.liteflowx.common.util.StringUtils;
import top.xystudio.plugin.idea.liteflowx.common.util.XmlUtils;
import top.xystudio.plugin.idea.liteflowx.common.dom.modal.DomFlow;
import top.xystudio.plugin.idea.liteflowx.common.dom.modal.DomNode;
import top.xystudio.plugin.idea.liteflowx.common.dom.modal.DomNodes;

import java.util.*;
import java.util.concurrent.Future;

@Service(Service.Level.PROJECT)
public final class LiteFlowNodeService {

    private final Trie<String, LiteFlowNodeMetadata> nodeSearchIndex;

    private final Project project;

    private final JavaService javaService;

    private final DomService domService;

    private Future<?> currentReIndexExecution;

    public LiteFlowNodeService(@NotNull Project project){
        this.nodeSearchIndex = new PatriciaTrie<>();
        this.project = project;
        this.javaService = project.getService(JavaService.class);
        this.domService = ApplicationManager.getApplication().getService(DomService.class);
    }

    private void clearSearchIndex(){
        this.nodeSearchIndex.clear();
    }

    /**
     * 初始化
     */
    public void init(){

        ApplicationManager.getApplication().runWriteAction(() -> {
            ProgressManager.getInstance().runProcessWithProgressSynchronously(this::index, "Indexing the Liteflow Node...", true, project);
        });

    }

    /**
     * 重新索引
     */
    public void reIndex(){

        if (currentReIndexExecution != null && !currentReIndexExecution.isDone()){
            currentReIndexExecution.cancel(false);
        }
        currentReIndexExecution = ApplicationManager.getApplication().executeOnPooledThread(() -> {
            DumbService.getInstance(this.project).runWhenSmart(() -> {
                this.clearSearchIndex();
                this.index();
            });
        });

    }

    /**
     * 索引操作
     */
    public void index(){

        Collection<PsiElement> result = new ArrayList<>();
        Collection<PsiClass> springComponents = javaService.getClassesByAnnotationQualifiedName(Annotation.Component);
        Collection<PsiClass> liteFlowComponents = javaService.getClassesByAnnotationQualifiedName(Annotation.LiteflowComponent);
        Collection<PsiMethod> methodComponents = javaService.getMethodsByAnnotationQualifiedName(Annotation.LiteflowMethod);

        // 根据xml文件定义的node也归为Component
        List<DomFileElement<DomFlow>> flows = domService.getFileElements(DomFlow.class, project, GlobalSearchScope.allScope(project));
        for (DomFileElement<DomFlow> flow : flows) {
            DomNodes domNodes = flow.getRootElement().getNodes();
            for (DomNode domNode : domNodes.getNodeList()) {
                String id = domNode.getId().getStringValue();
                if (StringUtil.isEmpty(id)) {continue;}

                String type = domNode.getType().getStringValue();
                if (ArrayUtils.contains(NodeTypeEnum.ScriptNodeType, type)){
                    result.add(domNode.getXmlTag());
                    continue;
                }

                String clazzValue = domNode.getClazz().getStringValue();
                if (clazzValue == null) {continue;}
                PsiClass aClass = javaService.getClassByQualifiedName(clazzValue);
                if (aClass == null) {continue;}
                result.add(aClass);
            }
        }
        result.addAll(springComponents);
        result.addAll(liteFlowComponents);
        result.addAll(methodComponents);
        for (PsiElement psiElement : result.stream().distinct().toList()) {
            LiteFlowNodeMetadata metadata = getLiteFlowNodeMetadata(psiElement);
            if (metadata == null || metadata.getId() == null) continue;
            nodeSearchIndex.put(metadata.getId(), metadata);
        }

    }

    /**
     * 根据 nodeId 获取 LiteFlowNodeMetadata
     * @param nodeId nodeId
     * @return metadata
     */
    public LiteFlowNodeMetadata getLiteFlowNodeMetadataById(String nodeId){
        return nodeSearchIndex.getOrDefault(nodeId, null);
    }

    /**
     * 直接取 SearchIndex
     * @return metadata
     */
    public Trie<String, LiteFlowNodeMetadata> getNodeSearchIndex(){
        return this.nodeSearchIndex;
    }

    /**
     * 传入 psiElement 直接转 metadata
     * @param psiElement psiElement
     * @return metadata
     */
    public LiteFlowNodeMetadata getLiteFlowNodeMetadata(PsiElement psiElement){

        if (psiElement instanceof PsiClass psiClass){ // 判断是否为类组件

            // 排除所有包名以 com.yomahub.liteflow.core. 开头的Class
            if (psiClass.getQualifiedName() == null || psiClass.getQualifiedName().indexOf("com.yomahub.liteflow.core.") == 0){
                return null;
            }
            // 排除使用了 abstract 的 Class
            if (psiClass.getText().contains("abstract class")){
                return null;
            }

            // 断定类组件
            LiteFlowNodeMetadata metadata = assumeInheritorClassComponent(psiClass);
            if (metadata == null){
                metadata = assumeDeclareClassComponent(psiClass);
            }
            return metadata;

        }else if(psiElement instanceof PsiMethod psiMethod){ // 判断是否为方法组件

            // 排除所有包名以 com.yomahub.liteflow.core. 开头的Class
            if (psiMethod.getContainingClass() == null ||
                    psiMethod.getContainingClass().getQualifiedName() == null ||
                    psiMethod.getContainingClass().getQualifiedName().indexOf("com.yomahub.liteflow.core.") == 0){
                return null;
            }
            // 排除使用了 abstract 的 Class
            if (psiMethod.getContainingClass().getText().contains("abstract class")){
                return null;
            }

            return assumeDeclareMethodComponent(psiMethod);

        }else if(psiElement instanceof XmlTag xmlTag){ // 判断是否为脚本组件

            return assumeScriptComponent((XmlTagImpl) xmlTag);

        }else {
            return null;
        }

    }

    /**
     * 断定是继承类组件，如果不是则返回null
     * @param targetClass 目标 PsiClass
     * @return 如果不是则返回null
     */
    private @Nullable LiteFlowNodeMetadata assumeInheritorClassComponent(PsiClass targetClass){

        LiteFlowNodeMetadata metadata = new LiteFlowNodeMetadata();
        metadata.setDefineType(DefineTypeEnum.INHERITOR_CLASS);

        for (String qualifiedName : Clazz.LiteFlowComponentTypeQualifiedNames) {
            PsiClass liteFlowComponentClazz = this.javaService.getClassByQualifiedName(qualifiedName);
            if (liteFlowComponentClazz != null && targetClass.isInheritor(liteFlowComponentClazz, true)){
                metadata.setNodeType(LiteFlowNodeTypeEnum.getByQualifiedName(qualifiedName));
                metadata.setId(this.getLiteFlowComponentId(targetClass));
                metadata.setScript(false);
                metadata.setPsiTarget(targetClass);
                metadata.setNaviTarget(targetClass);
                metadata.setModule(ProjectRootManager.getInstance(project).getFileIndex().getModuleForFile(targetClass.getContainingFile().getVirtualFile()));
                return metadata;
            }
        }

        return null;
    }

    /**
     * 断定是声明式类组件，如果不是则返回null
     * @param targetClass 目标 PsiClass
     * @return 如果不是则返回null
     */
    private @Nullable LiteFlowNodeMetadata assumeDeclareClassComponent(PsiClass targetClass){

        LiteFlowNodeMetadata metadata = new LiteFlowNodeMetadata();
        metadata.setDefineType(DefineTypeEnum.DECLARED_CLASS);

        String realNodeType = javaService.getAnnotationAttributeValue(targetClass, Annotation.LiteflowCmpDefine, "value");
        if (realNodeType == null){
            return null;
        }
        for (String nodeType : NodeTypeEnum.StandardNodeType) {
            if (realNodeType.contains(nodeType)){
                metadata.setNodeType(LiteFlowNodeTypeEnum.getByNodeType(nodeType));
                metadata.setId(this.getLiteFlowComponentId(targetClass));
                metadata.setScript(false);
                metadata.setPsiTarget(targetClass);
                metadata.setNaviTarget(targetClass);
                metadata.setModule(ProjectRootManager.getInstance(project).getFileIndex().getModuleForFile(targetClass.getContainingFile().getVirtualFile()));
                return metadata;
            }
        }

        return null;
    }

    /**
     * 断定是声明式方法组件，如果不是则返回null
     * @param targetMethod 目标 PsiMethod
     * @return 如果不是则返回null
     */
    private @Nullable LiteFlowNodeMetadata assumeDeclareMethodComponent(@NotNull PsiMethod targetMethod){

        LiteFlowNodeMetadata metadata = new LiteFlowNodeMetadata();
        metadata.setDefineType(DefineTypeEnum.DECLARED_METHOD);

        // 如果方法的类上都没有 @Component 或 @LiteflowComponent，则判断为不是声明式方法组件
        if (targetMethod.getContainingClass() == null || (!targetMethod.getContainingClass().hasAnnotation(Annotation.Component) && !targetMethod.getContainingClass().hasAnnotation(Annotation.LiteflowComponent))){
            return null;
        }
        // 如果方法的类上有 @LiteflowCmpDefine，则判断为不是声明式方法组件
        if (targetMethod.getContainingClass().hasAnnotation(Annotation.LiteflowCmpDefine)){
            return null;
        }
        // 如果方法的类上继承了任何一个 LiteFlowComponentType，则判断为不是声明式方法组件
        for (String qualifiedName : Clazz.LiteFlowComponentTypeQualifiedNames) {
            PsiClass liteFlowComponentClazz = this.javaService.getClassByQualifiedName(qualifiedName);
            if (liteFlowComponentClazz != null && targetMethod.getContainingClass().isInheritor(liteFlowComponentClazz, true)){
                return null;
            }
        }
        // 如果 @LiteflowMethod 的 value 值不在 NECESSARY_PROCESS 里面，则判断为不是声明式方法组件
        String liteFlowMethod = javaService.getAnnotationAttributeValue(targetMethod, Annotation.LiteflowMethod, "value");
        if (liteFlowMethod == null) return null;
        if (!LiteFlowMethodEnum.isNecessaryProcess(liteFlowMethod)) return null;

        // 如果 @LiteflowMethod 的 nodeType 值不在 StandardNodeType 里面，则判断为不是声明式方法组件
        String nodeType = javaService.getAnnotationAttributeValue(targetMethod, Annotation.LiteflowMethod, "nodeType");
        if (nodeType == null) return null;
        if (!NodeTypeEnum.isStandardNodeType(nodeType)) return null;

        // 如果 @LiteflowMethod 的 nodeId 值是空，则判断为不是声明式方法组件
        String nodeId = javaService.getAnnotationAttributeValue(targetMethod, Annotation.LiteflowMethod, "nodeId");
        if (StringUtil.isEmpty(nodeId)){
            return null;
        }

        metadata.setId(this.getLiteFlowComponentId(targetMethod));
        metadata.setNodeType(LiteFlowNodeTypeEnum.getByNodeType(nodeType));
        metadata.setScript(false);
        metadata.setPsiTarget(targetMethod);
        metadata.setNaviTarget(targetMethod);
        metadata.setModule(ProjectRootManager.getInstance(project).getFileIndex().getModuleForFile(targetMethod.getContainingFile().getVirtualFile()));
        return metadata;

    }

    /**
     * 断定是脚本组件，如果不是则返回null
     * @param targetXmlTag 目标 XmlTag
     * @return 如果不是则返回null
     */
    private @Nullable LiteFlowNodeMetadata assumeScriptComponent(XmlTagImpl targetXmlTag){

        LiteFlowNodeMetadata metadata = new LiteFlowNodeMetadata();
        metadata.setDefineType(DefineTypeEnum.XML);

        // 判断是否脚本组件
        DomNode domNode = XmlUtils.transformToDomElement(targetXmlTag, DomNode.class);
        if (domNode == null){
            return null;
        }
        // 如果 node 的 type 值不包含在 ScriptNodeType 里面，则直接返回 null
        if (!ArrayUtils.contains(NodeTypeEnum.ScriptNodeType, domNode.getType().getStringValue())){
            return null;
        }

        metadata.setId(this.getLiteFlowComponentId(targetXmlTag));
        metadata.setName(domNode.getName().getStringValue());
        metadata.setScript(true);
        metadata.setPsiTarget(targetXmlTag);
        metadata.setNaviTarget(targetXmlTag);
        metadata.setModule(ProjectRootManager.getInstance(project).getFileIndex().getModuleForFile(targetXmlTag.getContainingFile().getVirtualFile()));

        return metadata;
    }

    /**
     * 根据XmlTag获取LiteFlowComponent的名称
     * @param xmlTag xmlTag
     * @return 返回LiteFlowComponent的名称
     */
    private @Nullable String getLiteFlowComponentId(@NotNull XmlTag xmlTag){

        DomNode domNode = XmlUtils.transformToDomElement(xmlTag, DomNode.class);
        if (domNode == null){
            return null;
        }
        String componentValue = domNode.getId().getStringValue();
        if (StringUtil.isEmpty(componentValue)){
            return null;
        }
        return componentValue;
    }

    /**
     * 根据Method获取LiteFlowComponent的id
     * @param psiMethod psi方法
     * @return 返回LiteFlowComponent的id
     */
    private @Nullable String getLiteFlowComponentId(@NotNull PsiMethod psiMethod){
        String componentValue = javaService.getAnnotationAttributeValue(psiMethod, Annotation.LiteflowMethod, "nodeId");
        if (StringUtil.isEmpty(componentValue)){
            return null;
        }
        return componentValue;
    }

    /**
     * 根据Class获取LiteFlowComponent的id
     * @param psiClass psi类
     * @return 返回LiteFlowComponent的id
     */
    private @Nullable String getLiteFlowComponentId(@NotNull PsiClass psiClass){

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
            if (name.isEmpty()){
                name = StringUtils.lowerFirst(className);
            }
            return name;
        }

        // 根据xml文件定义的node也归为Component
        List<DomFileElement<DomFlow>> flows = DomService.getInstance().getFileElements(DomFlow.class, this.project, GlobalSearchScope.allScope(this.project));
        for (DomFileElement<DomFlow> flow : flows) {
            DomNodes domNodes = flow.getRootElement().getNodes();
            for (DomNode domNode : domNodes.getNodeList()) {
                String clazzValue = domNode.getClazz().getStringValue();
                String idValue = domNode.getId().getStringValue();
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





}
