package top.xystudio.plugin.idea.liteflowx.util;

import com.intellij.json.JsonUtil;
import com.intellij.json.psi.JsonArray;
import com.intellij.json.psi.JsonFile;
import com.intellij.json.psi.JsonObject;
import com.intellij.json.psi.JsonValue;
import com.intellij.openapi.project.Project;
import com.intellij.psi.PsiAnnotation;
import com.intellij.psi.PsiClass;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiFile;
import com.intellij.psi.search.GlobalSearchScope;
import com.intellij.util.xml.DomFileElement;
import com.intellij.util.xml.DomService;
import top.xystudio.plugin.idea.liteflowx.constant.Annotation;
import top.xystudio.plugin.idea.liteflowx.dom.modal.Chain;
import top.xystudio.plugin.idea.liteflowx.dom.modal.Flow;
import top.xystudio.plugin.idea.liteflowx.dom.modal.Node;
import top.xystudio.plugin.idea.liteflowx.dom.modal.Nodes;
import top.xystudio.plugin.idea.liteflowx.parse.RegexEntity;
import top.xystudio.plugin.idea.liteflowx.parse.RegexNodeEntity;

import java.util.*;
import java.util.function.BiFunction;

public class LiteFlowUtils {

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
     * 根据被解析后得到的List<RegexEntity> 查找对应的目标PsiElement
     * @param project
     * @param regexEntities
     * @return
     */
    public static Optional<? extends PsiElement[]> findTargetsByRegexEntities(Project project, List<RegexEntity> regexEntities, BiFunction<Project, RegexNodeEntity, PsiElement> biFunction) {
        Collection<PsiElement> result = new ArrayList<>();
        List<RegexNodeEntity> nodes = new ArrayList<>();
        for (RegexEntity regexEntity : regexEntities) {
            nodes.add(regexEntity.getItem());
            if (regexEntity.getRealItemArray() != null){
                nodes.addAll(Arrays.asList(regexEntity.getRealItemArray()));
            }
        }
        for (RegexNodeEntity node : nodes){
            PsiElement element = biFunction.apply(project, node);
            if (element != null){
                result.add(element);
            }
        }
        return Optional.of(result.toArray(new PsiElement[0]));
    }
}
