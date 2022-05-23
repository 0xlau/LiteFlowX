package top.xystudio.plugin.idea.liteflowx.system.toolWindow.frame;

import com.intellij.navigation.NavigationItem;
import com.intellij.openapi.actionSystem.*;
import com.intellij.openapi.module.Module;
import com.intellij.openapi.module.ModuleManager;
import com.intellij.openapi.project.DumbService;
import com.intellij.openapi.project.Project;
import com.intellij.psi.NavigatablePsiElement;
import com.intellij.psi.PsiClass;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiNamedElement;
import com.intellij.psi.impl.PsiElementBase;
import com.intellij.psi.xml.XmlTag;
import com.intellij.ui.JBSplitter;
import com.intellij.util.ArrayUtil;
import org.jetbrains.annotations.NotNull;
import top.xystudio.plugin.idea.liteflowx.service.LiteFlowService;
import top.xystudio.plugin.idea.liteflowx.system.toolWindow.beans.LiteFlowElement;
import top.xystudio.plugin.idea.liteflowx.system.toolWindow.beans.LiteFlowElementType;
import top.xystudio.plugin.idea.liteflowx.system.toolWindow.service.topic.LiteFlowTreeTopic;
import top.xystudio.plugin.idea.liteflowx.system.toolWindow.service.topic.RefreshLiteFlowTreeTopic;

import javax.swing.*;
import java.awt.*;
import java.util.*;
import java.util.List;
import java.util.stream.Collectors;

public class LiteFlowToolWindow extends JPanel {

    private final Project project;
    private final LiteFlowTree liteFlowTree;

    public LiteFlowToolWindow(@NotNull Project project) {
        super(new BorderLayout());
        this.project = project;

        this.liteFlowTree = new LiteFlowTree(project);

        AnAction action = ActionManager.getInstance().getAction("LiteFlowTool.Toolbar");
        ActionToolbar actionToolbar = ActionManager.getInstance().createActionToolbar(
                ActionPlaces.TOOLBAR,
                action instanceof ActionGroup ? ((ActionGroup) action) : new DefaultActionGroup(),
                true
        );
        actionToolbar.setTargetComponent(this);
        this.add(actionToolbar.getComponent(), BorderLayout.NORTH);

        JBSplitter splitter = new JBSplitter(true, LiteFlowTree.class.getName(), 0.5F);
        splitter.setFirstComponent(this.liteFlowTree);
        splitter.setSecondComponent(null);
        this.add(splitter, BorderLayout.CENTER);

        initEvent();

        DumbService.getInstance(project).smartInvokeLater(this::firstLoad);
    }

    private void firstLoad() {
        refreshTree();
    }

    public void refreshTree() {

        LiteFlowTreeTopic liteFlowTreeTopic = project.getMessageBus().syncPublisher(LiteFlowTreeTopic.TOPIC);
        DumbService.getInstance(project).runWhenSmart(() -> liteFlowTreeTopic.action(getElements()));

    }

    private Map<String, List<LiteFlowElement>> getElements() {
        Map<String, List<LiteFlowElement>> map = new HashMap<>();

        PsiClass[] liteFlowComponents = LiteFlowService.getInstance(project).findAllLiteFlowClass();
        map.put("Components", Arrays.stream(liteFlowComponents)
                .sorted(Comparator.comparing(NavigationItem::getName))
                .map(o -> new LiteFlowElement(LiteFlowElementType.COMPONENT, o.getName(), o))
                .collect(Collectors.toList()));

        PsiClass[] liteFlowSlots = LiteFlowService.getInstance(project).findAllLiteFlowSlot();
        map.put("Slots", Arrays.stream(liteFlowSlots)
                .sorted(Comparator.comparing(NavigationItem::getName))
                .map(o -> new LiteFlowElement(LiteFlowElementType.SLOT, o.getName(), o))
                .collect(Collectors.toList()));

        PsiElement[] liteFlowChains = LiteFlowService.getInstance(project).findAllLiteFlowChain();
        map.put("Chains", Arrays.stream(liteFlowChains)
                .map(o -> {
                    if (o instanceof XmlTag){
                        String name = ((XmlTag) o).getAttributeValue("name");
                        return new LiteFlowElement(LiteFlowElementType.CHAIN, name, o.getContainingFile());
                    }
                    return null;
                })
                .filter(o -> o != null)
                .collect(Collectors.toList()));

        PsiElement[] liteFlowNodes = LiteFlowService.getInstance(project).findAllLiteFlowNode();
        map.put("Nodes", Arrays.stream(liteFlowNodes)
                .map(o -> {
                    if (o instanceof XmlTag){
                        String id = ((XmlTag) o).getAttributeValue("id");
                        String clazz = ((XmlTag) o).getAttributeValue("class");
                        return new LiteFlowElement(LiteFlowElementType.NODE, String.format("%s -> %s", id, clazz), o.getContainingFile());
                    }
                    return null;
                })
                .filter(o -> o != null)
                .collect(Collectors.toList()));

        return map;

    }

    private void initEvent() {
        project.getMessageBus().connect().subscribe(LiteFlowTreeTopic.TOPIC, this.liteFlowTree::renderTree);
        project.getMessageBus().connect().subscribe(RefreshLiteFlowTreeTopic.TOPIC, this::refreshTree);
    }

}
