package top.xystudio.plugin.idea.liteflowx.common.metadata;

import com.intellij.openapi.module.Module;
import com.intellij.psi.NavigatablePsiElement;
import com.intellij.psi.PsiElement;
import top.xystudio.plugin.idea.liteflowx.common.enums.LiteFlowNodeTypeEnum;

public class LiteFlowChainMetadata {

    private String id;

    private String name;

    // 所在module位置
    private Module module;

    private PsiElement psiTarget;

    private NavigatablePsiElement naviTarget;

    public String getId() {
        return id;
    }

    public LiteFlowChainMetadata setId(String id) {
        this.id = id;
        return this;
    }

    public String getName() {
        return name;
    }

    public LiteFlowChainMetadata setName(String name) {
        this.name = name;
        return this;
    }

    public PsiElement getPsiTarget() {
        return psiTarget;
    }

    public LiteFlowChainMetadata setPsiTarget(PsiElement psiTarget) {
        this.psiTarget = psiTarget;
        return this;
    }

    public NavigatablePsiElement getNaviTarget() {
        return naviTarget;
    }

    public LiteFlowChainMetadata setNaviTarget(NavigatablePsiElement naviTarget) {
        this.naviTarget = naviTarget;
        return this;
    }

    public Module getModule() {
        return module;
    }

    public LiteFlowChainMetadata setModule(Module module) {
        this.module = module;
        return this;
    }
}
