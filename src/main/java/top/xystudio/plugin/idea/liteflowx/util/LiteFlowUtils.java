package top.xystudio.plugin.idea.liteflowx.util;

import com.intellij.json.JsonUtil;
import com.intellij.json.psi.JsonArray;
import com.intellij.json.psi.JsonFile;
import com.intellij.json.psi.JsonObject;
import com.intellij.json.psi.JsonValue;
import com.intellij.openapi.project.Project;
import com.intellij.psi.*;
import com.intellij.psi.search.GlobalSearchScope;
import com.intellij.util.xml.DomFileElement;
import com.intellij.util.xml.DomService;
import top.xystudio.plugin.idea.liteflowx.constant.Annotation;
import top.xystudio.plugin.idea.liteflowx.constant.Clazz;
import top.xystudio.plugin.idea.liteflowx.dom.modal.Chain;
import top.xystudio.plugin.idea.liteflowx.dom.modal.Flow;
import top.xystudio.plugin.idea.liteflowx.dom.modal.Node;
import top.xystudio.plugin.idea.liteflowx.dom.modal.Nodes;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class LiteFlowUtils {

    public static String[] getComponentNamesByExpression(String expression) {
        return expression.replace(" ", "").replaceAll("\\[.*?\\]","").split(",|\\(|\\||\\)");
    }

    public static PsiClass[] findAllLiteFlowComponent(Project project){
        Collection<PsiClass> result = new ArrayList<>();
        PsiClass componentAnnotation = JavaUtils.getClassByQualifiedName(project, Annotation.Component);
        PsiClass liteFlowComponentAnnotation = JavaUtils.getClassByQualifiedName(project, Annotation.LiteflowComponent);
        result.addAll(JavaUtils.getClassesByAnnotation(project, componentAnnotation));
        result.addAll(JavaUtils.getClassesByAnnotation(project, liteFlowComponentAnnotation));
        List<PsiClass> collect = result.stream().filter(
                psiClass -> {
                    String qualifiedName = psiClass.getSuperClass().getQualifiedName();
                    return qualifiedName.equals(Clazz.NodeComponent) || qualifiedName.equals(Clazz.NodeCondComponent);
                }
        ).collect(Collectors.toList());
        return collect.toArray(new PsiClass[0]);
    }

    public static PsiElement[] findAllLiteFlowChain(Project project){
        Collection<PsiElement> result = new ArrayList<>();
        List<DomFileElement<Flow>> flows = DomService.getInstance().getFileElements(Flow.class, project, GlobalSearchScope.allScope(project));
        for (DomFileElement<Flow> flow : flows) {
            for (Chain chain : flow.getRootElement().getChains()) {
                result.add(chain.getXmlTag());
            }
        }
        Collection<JsonFile> jsonFiles = FileUtils.getAllJsonFiles(project);
        for (PsiFile psiFile : jsonFiles) {
            try {
                JsonObject jsonObject = JsonUtil.getTopLevelObject((JsonFile) psiFile);
                JsonObject flow = (JsonObject) jsonObject.findProperty("flow").getValue();
                JsonArray chains = (JsonArray) flow.findProperty("chain").getValue();
                for (JsonValue jsonValue : chains.getValueList()) {
                    JsonObject chain = (JsonObject) jsonValue;
                    result.add(chain);
                }
            }catch (Exception e){
                continue;
            }
        }
        return result.toArray(new PsiElement[0]);
    }

    public static PsiElement[] findAllLiteFlowNode(Project project){
        Collection<PsiElement> result = new ArrayList<>();
        List<DomFileElement<Flow>> flows = DomService.getInstance().getFileElements(Flow.class, project, GlobalSearchScope.allScope(project));
        for (DomFileElement<Flow> flow : flows) {
            Nodes nodes = flow.getRootElement().getNodes();
            for (Node node : nodes.getNodeList()) {
                result.add(node.getXmlTag());
            }
        }
        Collection<JsonFile> jsonFiles = FileUtils.getAllJsonFiles(project);
        for (PsiFile psiFile : jsonFiles) {
            try {
                JsonObject jsonObject = JsonUtil.getTopLevelObject((JsonFile) psiFile);
                JsonObject flow = (JsonObject) jsonObject.findProperty("flow").getValue();
                JsonObject nodes = (JsonObject) flow.findProperty("nodes").getValue();
                JsonArray node = (JsonArray) nodes.findProperty("node").getValue();
                for (JsonValue jsonValue : node.getValueList()) {
                    JsonObject nodeObject = (JsonObject) jsonValue;
                    result.add(nodeObject);
                }
            }catch (Exception e){
                continue;
            }
        }
        return result.toArray(new PsiElement[0]);
    }

    public static PsiAnnotation getLiteFlowAnnotationByClass(PsiClass psiClass){
        PsiAnnotation annotation = psiClass.getAnnotation(Annotation.Component);
        if (annotation != null){
            return annotation;
        }
        return psiClass.getAnnotation(Annotation.LiteflowComponent);
    }

    /**
     * Find Class by component name
     * @param project
     * @param componentName
     * @return the PsiClass
     */
    public static PsiClass findClassByComponentName(Project project, String componentName){
        if (componentName == null || componentName.equals("")){
            return null;
        }

        PsiClass[] allComponent = findAllLiteFlowComponent(project);
        for (PsiClass psiClass : allComponent) {
            String componentValue = JavaUtils.getAnnotationComponentValue(psiClass, psiClass.getAnnotation(Annotation.Component));
            String liteFlowComponentValue = JavaUtils.getAnnotationComponentValue(psiClass, psiClass.getAnnotation(Annotation.LiteflowComponent));
            if (liteFlowComponentValue != null && liteFlowComponentValue.equals(componentName)){
                return psiClass;
            }
            if (componentValue != null && componentValue.equals(componentName)){
                return psiClass;
            }
        }
        return null;
    }

    /**
     * Find chain by component name
     * @param project
     * @param componentName
     * @return the XmlTag
     */
    public static PsiElement findChainByComponentName(Project project, String componentName){
        if (componentName == null || componentName.equals("")){
            return null;
        }
        /* find chain on all xml file */
        List<DomFileElement<Flow>> flows = DomService.getInstance().getFileElements(Flow.class, project, GlobalSearchScope.allScope(project));
        for (DomFileElement<Flow> flow : flows) {
            for (Chain chain : flow.getRootElement().getChains()) {
                if (chain.getName().getStringValue().equals(componentName)){
                    return chain.getXmlTag();
                }
            }
        }
        /* find chain on all json file */
        Collection<JsonFile> jsonFiles = FileUtils.getAllJsonFiles(project);
        for (PsiFile psiFile : jsonFiles) {
            try {
                JsonObject jsonObject = JsonUtil.getTopLevelObject((JsonFile) psiFile);
                JsonObject flow = (JsonObject) jsonObject.findProperty("flow").getValue();
                JsonArray chains = (JsonArray) flow.findProperty("chain").getValue();
                for (JsonValue jsonValue : chains.getValueList()) {
                    JsonObject chain = (JsonObject) jsonValue;
                    String name = chain.findProperty("name").getValue().getText().replace("\"", "");
                    if (name.equals(componentName)) {
                        return chain;
                    }
                }
            }catch (Exception e){
                continue;
            }
        }
        return null;
    }

    /**
     * Find node by component name
     * @param project
     * @param componentName
     * @return the PsiElement
     */
    private static PsiElement findNodeByComponentName(Project project, String componentName) {
        if (componentName == null || componentName.equals("")){
            return null;
        }
        /* find node on all xml file */
        List<DomFileElement<Flow>> flows = DomService.getInstance().getFileElements(Flow.class, project, GlobalSearchScope.allScope(project));
        for (DomFileElement<Flow> flow : flows) {
            Nodes nodes = flow.getRootElement().getNodes();
            if (nodes != null){
                for (Node node : nodes.getNodeList()) {
                    if (node.getId().getStringValue().equals(componentName)){
                        return node.getXmlTag();
                    }
                }
            }
        }
        /* find node on all json file */
        Collection<JsonFile> jsonFiles = FileUtils.getAllJsonFiles(project);
        for (PsiFile psiFile : jsonFiles) {
            try {
                JsonObject jsonObject = JsonUtil.getTopLevelObject((JsonFile) psiFile);
                JsonObject flow = (JsonObject) jsonObject.findProperty("flow").getValue();
                JsonObject nodes = (JsonObject) flow.findProperty("nodes").getValue();
                JsonArray node = (JsonArray) nodes.findProperty("node").getValue();
                for (JsonValue jsonValue : node.getValueList()) {
                    JsonObject nodeObject = (JsonObject) jsonValue;
                    String name = nodeObject.findProperty("id").getValue().getText().replace("\"", "");
                    if (name.equals(componentName)) {
                        return nodeObject;
                    }
                }
            }catch (Exception e){
                continue;
            }
        }
        return null;
    }

    public static Optional<PsiClass[]> findClassesByComponentName(Project project, String[] componentNames){
        Collection<PsiClass> result = new ArrayList<>();
        for (String componentName : componentNames) {
            PsiClass clazz = findClassByComponentName(project, componentName);
            if (clazz != null){
                result.add(clazz);
            }
        }
        return Optional.of(result.toArray(new PsiClass[0]));
    }

    public static Optional<PsiElement[]> findChainsByComponentName(Project project, String[] componentNames){
        Collection<PsiElement> result = new ArrayList<>();
        for (String componentName : componentNames) {
            PsiElement element = findChainByComponentName(project, componentName);
            if (element != null){
                result.add(element);
            }
        }
        return Optional.of(result.toArray(new PsiElement[0]));
    }


    public static Optional<PsiElement[]> findNodesByComponentName(Project project, String[] componentNames) {
        Collection<PsiElement> result = new ArrayList<>();
        for (String componentName : componentNames) {
            PsiElement element = findNodeByComponentName(project, componentName);
            if (element != null){
                result.add(element);
            }
        }
        return Optional.of(result.toArray(new PsiElement[0]));
    }


}
