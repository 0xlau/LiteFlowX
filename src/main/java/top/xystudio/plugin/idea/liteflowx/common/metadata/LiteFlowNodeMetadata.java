package top.xystudio.plugin.idea.liteflowx.common.metadata;

import com.intellij.openapi.module.Module;
import com.intellij.psi.NavigatablePsiElement;
import com.intellij.psi.PsiElement;
import top.xystudio.plugin.idea.liteflowx.common.enums.DefineTypeEnum;
import top.xystudio.plugin.idea.liteflowx.common.enums.LiteFlowNodeTypeEnum;

public class LiteFlowNodeMetadata {

    private String id;

    private String name;

    // 所在module位置
    private Module module;

    private PsiElement psiTarget;

    private NavigatablePsiElement naviTarget;

    private LiteFlowNodeTypeEnum nodeType;

    private DefineTypeEnum defineType;

    private boolean isScript;

    public String getId() {
        return id;
    }

    public LiteFlowNodeMetadata setId(String id) {
        this.id = id;
        return this;
    }

    public String getName() {
        return name;
    }

    public LiteFlowNodeMetadata setName(String name) {
        this.name = name;
        return this;
    }

    public PsiElement getPsiTarget() {
        return psiTarget;
    }

    public LiteFlowNodeMetadata setPsiTarget(PsiElement psiTarget) {
        this.psiTarget = psiTarget;
        return this;
    }

    public NavigatablePsiElement getNaviTarget() {
        return naviTarget;
    }

    public LiteFlowNodeMetadata setNaviTarget(NavigatablePsiElement naviTarget) {
        this.naviTarget = naviTarget;
        return this;
    }

    public LiteFlowNodeTypeEnum getNodeType() {
        return nodeType;
    }

    public LiteFlowNodeMetadata setNodeType(LiteFlowNodeTypeEnum nodeType) {
        this.nodeType = nodeType;
        return this;
    }

    public boolean isScript() {
        return isScript;
    }

    public LiteFlowNodeMetadata setScript(boolean script) {
        isScript = script;
        return this;
    }

    public Module getModule() {
        return module;
    }

    public LiteFlowNodeMetadata setModule(Module module) {
        this.module = module;
        return this;
    }

    public DefineTypeEnum getDefineType() {
        return defineType;
    }

    public LiteFlowNodeMetadata setDefineType(DefineTypeEnum defineType) {
        this.defineType = defineType;
        return this;
    }
}
