package top.xystudio.plugin.idea.liteflowx.toolwindow.tree;

import com.intellij.psi.PsiFile;
import top.xystudio.plugin.idea.liteflowx.common.metadata.LiteFlowChainMetadata;
import top.xystudio.plugin.idea.liteflowx.common.metadata.LiteFlowNodeMetadata;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class NavigateTreeNode {

    public NavigateTreeNode(){
        this.title = "ROOT";
        this.subTitle = "ROOT";
        this.type = NavigateTreeNodeType.ROOT;
    }

    public NavigateTreeNode(String title, NavigateTreeNodeType type){
        this.title = title;
        this.type = type;
    }

    public NavigateTreeNode(String title, String subTitle, NavigateTreeNodeType type){
        this.title = title;
        this.subTitle = subTitle;
        this.type = type;
    }

    private String title;

    private String subTitle;

    private NavigateTreeNodeType type;

    private LiteFlowNodeMetadata liteFlowNode;

    private LiteFlowChainMetadata liteFlowChain;

    private PsiFile liteFlowPsiFile;

    private List<NavigateTreeNode> children;

    public String getSubTitle() {
        return subTitle;
    }

    public NavigateTreeNode setSubTitle(String subTitle) {
        this.subTitle = subTitle;
        return this;
    }

    public String getTitle() {
        return title;
    }

    public NavigateTreeNode setTitle(String title) {
        this.title = title;
        return this;
    }

    public NavigateTreeNodeType getType() {
        return type;
    }

    public NavigateTreeNode setType(NavigateTreeNodeType type) {
        this.type = type;
        return this;
    }

    public LiteFlowNodeMetadata getLiteFlowNode() {
        return liteFlowNode;
    }

    public NavigateTreeNode setLiteFlowNode(LiteFlowNodeMetadata liteFlowNode) {
        this.liteFlowNode = liteFlowNode;
        return this;
    }

    public LiteFlowChainMetadata getLiteFlowChain() {
        return liteFlowChain;
    }

    public NavigateTreeNode setLiteFlowChain(LiteFlowChainMetadata liteFlowChain) {
        this.liteFlowChain = liteFlowChain;
        return this;
    }

    public PsiFile getLiteFlowPsiFile() {
        return liteFlowPsiFile;
    }

    public NavigateTreeNode setLiteFlowPsiFile(PsiFile liteFlowPsiFile) {
        this.liteFlowPsiFile = liteFlowPsiFile;
        return this;
    }

    public List<NavigateTreeNode> getChildren() {
        return children;
    }

    public NavigateTreeNode addChildren(NavigateTreeNode navigateTreeNode) {
        if (children == null) {
            children = new ArrayList<>();
        }
        children.add(navigateTreeNode);
        return this;
    }

    public NavigateTreeNode addChildren(NavigateTreeNode[] navigateTreeNode) {
        if (children == null) {
            children = new ArrayList<>();
        }
        children.addAll(List.of(navigateTreeNode));
        return this;
    }

    public NavigateTreeNode addChildren(List<NavigateTreeNode> navigateTreeNode) {
        if (children == null) {
            children = new ArrayList<>();
        }
        children.addAll(navigateTreeNode);
        return this;
    }

    public void setChildren(List<NavigateTreeNode> navigateTreeNode){
        this.children = navigateTreeNode;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof NavigateTreeNode treeNode)) return false;
        return Objects.equals(getTitle(), treeNode.getTitle()) && getType() == treeNode.getType() && Objects.equals(getLiteFlowNode(), treeNode.getLiteFlowNode()) && Objects.equals(getLiteFlowChain(), treeNode.getLiteFlowChain()) && Objects.equals(getLiteFlowPsiFile(), treeNode.getLiteFlowPsiFile()) && Objects.equals(getChildren(), treeNode.getChildren());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getTitle(), getType(), getLiteFlowNode(), getLiteFlowChain(), getLiteFlowPsiFile(), getChildren());
    }
}
