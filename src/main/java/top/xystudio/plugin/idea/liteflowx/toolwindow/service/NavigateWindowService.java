package top.xystudio.plugin.idea.liteflowx.toolwindow.service;

import B.B.N;
import com.intellij.ide.highlighter.XmlFileType;
import com.intellij.ide.projectView.ProjectViewNode;
import com.intellij.lang.xml.XMLLanguage;
import com.intellij.openapi.components.Service;
import com.intellij.openapi.fileTypes.FileType;
import com.intellij.openapi.module.Module;
import com.intellij.openapi.module.ModuleManager;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.roots.ContentEntry;
import com.intellij.openapi.roots.ModuleRootManager;
import com.intellij.openapi.roots.SourceFolder;
import com.intellij.openapi.vfs.VfsUtil;
import com.intellij.openapi.vfs.VirtualFile;
import com.intellij.openapi.wm.ToolWindow;
import com.intellij.psi.PsiFile;
import com.intellij.psi.PsiManager;
import com.intellij.psi.xml.XmlFile;
import org.jetbrains.annotations.NotNull;
import top.xystudio.plugin.idea.liteflowx.common.enums.DefineTypeEnum;
import top.xystudio.plugin.idea.liteflowx.common.enums.LiteFlowNodeTypeEnum;
import top.xystudio.plugin.idea.liteflowx.common.metadata.LiteFlowNodeMetadata;
import top.xystudio.plugin.idea.liteflowx.service.LiteFlowChainService;
import top.xystudio.plugin.idea.liteflowx.service.LiteFlowNodeService;
import top.xystudio.plugin.idea.liteflowx.toolwindow.tree.NavigateTreeNode;
import top.xystudio.plugin.idea.liteflowx.toolwindow.tree.NavigateTreeNodeType;
import top.xystudio.plugin.idea.liteflowx.toolwindow.view.NavigatePanel;

import java.io.File;
import java.util.*;
import java.util.stream.Collectors;

@Service(Service.Level.PROJECT)
public final class NavigateWindowService {

    private Project project;
    private ToolWindow toolWindow;
    private LiteFlowNodeService liteFlowNodeService;
    private LiteFlowChainService liteFlowChainService;
    private NavigatePanel navigatePanel;
    private NavigateTreeNode navigateRootTreeNode = new NavigateTreeNode();

    public NavigateWindowService(Project project){
        this.project = project;
        this.liteFlowNodeService = project.getService(LiteFlowNodeService.class);
        this.liteFlowChainService = project.getService(LiteFlowChainService.class);
    }

    public NavigatePanel initToolWindow(@NotNull ToolWindow toolWindow){
        this.toolWindow = toolWindow;
        this.navigatePanel = new NavigatePanel(this);
        return navigatePanel;
    }

    public void reloadNavigateTreeNode(){

        NavigateTreeNode rootTreeNode = new NavigateTreeNode();

        Map<Module, List<LiteFlowNodeMetadata>> groupingByModules = this.liteFlowNodeService.getNodeSearchIndex().values().stream().collect(Collectors.groupingBy(LiteFlowNodeMetadata::getModule));
        for (Module module : groupingByModules.keySet()) {

            NavigateTreeNode moduleTreeNode = new NavigateTreeNode(module.getName(), NavigateTreeNodeType.MODULE);

            for (VirtualFile sourceRoot : ModuleRootManager.getInstance(module).getSourceRoots(false)) {

                NavigateTreeNode sourceRootNode = new NavigateTreeNode(sourceRoot.getName(), NavigateTreeNodeType.MAIN);
                if (sourceRoot.getUrl().endsWith("/resources")){
                    sourceRootNode.setType(NavigateTreeNodeType.RESOURCE);
                }
                sourceRootNode.addChildren(buildTreeNodeByDFS(VfsUtil.getChildren(sourceRoot)));

                if (!sourceRootNode.getChildren().isEmpty()) moduleTreeNode.addChildren(sourceRootNode);
            }

            if (!moduleTreeNode.getChildren().isEmpty()) {
                rootTreeNode.addChildren(moduleTreeNode);
            }

        }
        this.navigateRootTreeNode = rootTreeNode;
    }

    /**
     * 深度遍历构建 TreeNode
     * @param virtualFiles virtualFiles
     * @return List<NavigateTreeNode>
     */
    private List<NavigateTreeNode> buildTreeNodeByDFS(VirtualFile[] virtualFiles){

        List<NavigateTreeNode> treeNodes = new ArrayList<>();
        for (VirtualFile virtualFile : virtualFiles) {
            NavigateTreeNode node = new NavigateTreeNode(virtualFile.getName(), NavigateTreeNodeType.PACKAGE);
            if (virtualFile.isDirectory()) {
                List<NavigateTreeNode> nodes = buildTreeNodeByDFS(VfsUtil.getChildren(virtualFile));
                if (nodes.size() == 1 && nodes.get(0).getType() == NavigateTreeNodeType.PACKAGE){
                    node.setTitle(node.getTitle() + "." + nodes.get(0).getTitle());
                    node.setChildren(nodes.get(0).getChildren());
                }else{
                    node.addChildren(nodes);
                }
                treeNodes.add(node);
            }else{
                PsiFile psiFile = PsiManager.getInstance(this.project).findFile(virtualFile);
                LiteFlowNodeMetadata nodeMetadata = this.liteFlowNodeService.getLiteFlowNodeMetadataByPsiFile(psiFile);
                if (nodeMetadata != null && (nodeMetadata.getDefineType() == DefineTypeEnum.DECLARED_CLASS || nodeMetadata.getDefineType() == DefineTypeEnum.INHERITOR_CLASS)) {
                    node.setType(NavigateTreeNodeType.COMPONENT);
                    node.setLiteFlowNode(nodeMetadata);
                    node.setTitle(nodeMetadata.getId());
                }else if(nodeMetadata != null && nodeMetadata.getDefineType() == DefineTypeEnum.DECLARED_METHOD){
                    node.setType(NavigateTreeNodeType.CLASS);
                    node.setTitle(virtualFile.getNameWithoutExtension());
                    for (LiteFlowNodeMetadata metadata : this.liteFlowNodeService.listLiteFlowNodeMetadataByPsiFileAndDefineType(psiFile, DefineTypeEnum.DECLARED_METHOD)) {
                        node.addChildren(new NavigateTreeNode(metadata.getId(), NavigateTreeNodeType.COMPONENT).setLiteFlowNode(metadata));
                    }
                }else if(Objects.requireNonNull(psiFile).getLanguage().isKindOf(XMLLanguage.INSTANCE)){
                    node.setType(NavigateTreeNodeType.ELF);
                    node.setTitle(virtualFile.getName());
                    for (LiteFlowNodeMetadata metadata : this.liteFlowNodeService.listLiteFlowNodeMetadataByPsiFileAndDefineType(psiFile, DefineTypeEnum.XML)) {
                        node.addChildren(new NavigateTreeNode(metadata.getId(), NavigateTreeNodeType.COMPONENT).setLiteFlowNode(metadata));
                    }
                }else{
                    continue;
                }
                treeNodes.add(node);
            }
        }
        return treeNodes;
    }

    public Project getProject() {
        return project;
    }

    public NavigateWindowService setProject(Project project) {
        this.project = project;
        return this;
    }

    public ToolWindow getToolWindow() {
        return toolWindow;
    }

    public NavigateWindowService setToolWindow(ToolWindow toolWindow) {
        this.toolWindow = toolWindow;
        return this;
    }

    public LiteFlowNodeService getLiteFlowNodeService() {
        return liteFlowNodeService;
    }

    public NavigateWindowService setLiteFlowNodeService(LiteFlowNodeService liteFlowNodeService) {
        this.liteFlowNodeService = liteFlowNodeService;
        return this;
    }

    public LiteFlowChainService getLiteFlowChainService() {
        return liteFlowChainService;
    }

    public NavigateWindowService setLiteFlowChainService(LiteFlowChainService liteFlowChainService) {
        this.liteFlowChainService = liteFlowChainService;
        return this;
    }

    public NavigatePanel getNavigatePanel() {
        return navigatePanel;
    }

    public NavigateWindowService setNavigatePanel(NavigatePanel navigatePanel) {
        this.navigatePanel = navigatePanel;
        return this;
    }

    public NavigateTreeNode getNavigateRootTreeNode() {
        return navigateRootTreeNode;
    }

    public NavigateWindowService setNavigateRootTreeNode(NavigateTreeNode navigateRootTreeNode) {
        this.navigateRootTreeNode = navigateRootTreeNode;
        return this;
    }
}
