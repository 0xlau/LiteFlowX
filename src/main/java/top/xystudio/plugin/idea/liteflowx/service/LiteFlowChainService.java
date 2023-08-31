package top.xystudio.plugin.idea.liteflowx.service;

import com.intellij.openapi.application.ApplicationManager;
import com.intellij.openapi.components.Service;
import com.intellij.openapi.progress.ProgressManager;
import com.intellij.openapi.project.DumbService;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.roots.ProjectRootManager;
import com.intellij.openapi.util.text.StringUtil;
import com.intellij.psi.PsiElement;
import com.intellij.psi.impl.source.xml.XmlTagImpl;
import com.intellij.psi.search.GlobalSearchScope;
import com.intellij.psi.xml.XmlTag;
import com.intellij.util.xml.DomFileElement;
import com.intellij.util.xml.DomService;
import org.apache.commons.collections4.Trie;
import org.apache.commons.collections4.trie.PatriciaTrie;
import org.jetbrains.annotations.NotNull;
import top.xystudio.plugin.idea.liteflowx.common.dom.modal.DomChain;
import top.xystudio.plugin.idea.liteflowx.common.dom.modal.DomFlow;
import top.xystudio.plugin.idea.liteflowx.common.metadata.LiteFlowChainMetadata;
import top.xystudio.plugin.idea.liteflowx.common.metadata.LiteFlowNodeMetadata;
import top.xystudio.plugin.idea.liteflowx.common.util.XmlUtils;

import java.util.List;
import java.util.concurrent.Future;

@Service(Service.Level.PROJECT)
public final class LiteFlowChainService {

    private final Trie<String, LiteFlowChainMetadata> chainSearchIndex;

    private final Project project;

    private final DomService domService;

    private Future<?> currentReIndexExecution;

    public LiteFlowChainService(@NotNull Project project){
        this.chainSearchIndex = new PatriciaTrie<>();
        this.project = project;
        this.domService = ApplicationManager.getApplication().getService(DomService.class);
    }

    private void clearSearchIndex(){
        this.chainSearchIndex.clear();
    }

    /**
     * 初始化
     */
    public void init(){

        ApplicationManager.getApplication().runWriteAction(() -> {
            ProgressManager.getInstance().runProcessWithProgressSynchronously(this::index, "Indexing the Liteflow Chain...", true, project);
        });

    }

    /**
     * 索引操作
     */
    private void index() {

        List<DomFileElement<DomFlow>> flows = domService.getFileElements(DomFlow.class, this.project, GlobalSearchScope.allScope(this.project));
        for (DomFileElement<DomFlow> flow : flows) {
            for (DomChain domChain : flow.getRootElement().getChains()) {
                LiteFlowChainMetadata metadata = getLiteFlowChainMetadata(domChain.getXmlTag());
                if (metadata == null || metadata.getId() == null) continue;
                chainSearchIndex.put(metadata.getId(), metadata);
            }
        }

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


    public LiteFlowChainMetadata getLiteFlowChainMetadata(XmlTag xmlTag){
        LiteFlowChainMetadata metadata = new LiteFlowChainMetadata();

        DomChain domChain = XmlUtils.transformToDomElement(xmlTag, DomChain.class);
        if (domChain == null){
            return null;
        }

        String id = domChain.getId().getStringValue();
        String name = domChain.getName().getStringValue();
        if (StringUtil.isNotEmpty(id)) {
            metadata.setId(id);
        }else if (StringUtil.isNotEmpty(name)) {
            metadata.setId(name);
        }

        metadata.setModule(ProjectRootManager.getInstance(project).getFileIndex().getModuleForFile(xmlTag.getContainingFile().getVirtualFile()));
        metadata.setNaviTarget((XmlTagImpl) xmlTag);
        metadata.setPsiTarget(xmlTag);

        return metadata;
    }

    /**
     * 根据 chainId 获取 LiteFlowChainMetadata
     * @param chainId chainId
     * @return metadata
     */
    public LiteFlowChainMetadata getLiteFlowChainMetadataById(String chainId){
        return chainSearchIndex.getOrDefault(chainId, null);
    }

    /**
     * 直接取 SearchIndex
     * @return metadata
     */
    public Trie<String, LiteFlowChainMetadata> getChainSearchIndex(){
        return this.chainSearchIndex;
    }

}
