package top.xystudio.plugin.idea.liteflowx.toolWindow.frame;

import com.intellij.openapi.actionSystem.*;
import com.intellij.openapi.project.DumbService;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.util.text.StringUtil;
import com.intellij.psi.PsiClass;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiMethod;
import com.intellij.psi.impl.source.xml.XmlTagImpl;
import com.intellij.psi.xml.XmlTag;
import com.intellij.ui.JBSplitter;
import org.jetbrains.annotations.NotNull;
import top.xystudio.plugin.idea.liteflowx.service.LiteFlowService;
import top.xystudio.plugin.idea.liteflowx.toolWindow.beans.LiteFlowElement;
import top.xystudio.plugin.idea.liteflowx.toolWindow.beans.LiteFlowElementType;
import top.xystudio.plugin.idea.liteflowx.toolWindow.service.topic.LiteFlowTreeTopic;
import top.xystudio.plugin.idea.liteflowx.toolWindow.service.topic.RefreshLiteFlowTreeTopic;
import top.xystudio.plugin.idea.liteflowx.common.util.AsyncUtils;

import javax.swing.*;
import java.awt.*;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class LiteFlowToolWindow extends JPanel {

    private final Project project;
    private final LiteFlowTree liteFlowTree;
    private final LiteFlowService liteFlowService;

    public LiteFlowToolWindow(@NotNull Project project) {
        super(new BorderLayout());
        this.project = project;

        this.liteFlowService = LiteFlowService.getInstance(project);
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
        DumbService.getInstance(project).runWhenSmart(() -> {
            AsyncUtils.runRead(project, this::getElements, data -> liteFlowTreeTopic.action(data));
        });

    }

    private Map<String, List<LiteFlowElement>> getElements() {
        Map<String, List<LiteFlowElement>> map = new HashMap<>();

        PsiElement[] liteFlowComponents = LiteFlowService.getInstance(project).findAllLiteFlowComponent();
        map.put("Components", Arrays.stream(liteFlowComponents)
//                .sorted(Comparator.comparing(NavigationItem::getName))
                .map(o -> {
                    if (o instanceof PsiClass){
                        PsiClass c = (PsiClass) o;
                        if (liteFlowService.isLiteFlowIfComponent(o)){
                            return new LiteFlowElement(LiteFlowElementType.IF_COMPONENT, liteFlowService.getLiteFlowComponentNameByPsiClass(c), c);
                        } else if (liteFlowService.isLiteFlowSwitchComponent(o)) {
                            return new LiteFlowElement(LiteFlowElementType.SWITCH_COMPONENT, liteFlowService.getLiteFlowComponentNameByPsiClass(c),c);
                        }else if (liteFlowService.isLiteFlowForComponent(o)) {
                            return new LiteFlowElement(LiteFlowElementType.FOR_COMPONENT, liteFlowService.getLiteFlowComponentNameByPsiClass(c), c);
                        }else if (liteFlowService.isLiteFlowIteratorComponent(o)) {
                            return new LiteFlowElement(LiteFlowElementType.ITERATOR_COMPONENT, liteFlowService.getLiteFlowComponentNameByPsiClass(c), c);
                        }else if (liteFlowService.isLiteFlowWhileComponent(o)) {
                            return new LiteFlowElement(LiteFlowElementType.WHILE_COMPONENT, liteFlowService.getLiteFlowComponentNameByPsiClass(c), c);
                        }else if (liteFlowService.isLiteFlowBreakComponent(o)) {
                            return new LiteFlowElement(LiteFlowElementType.BREAK_COMPONENT, liteFlowService.getLiteFlowComponentNameByPsiClass(c), c);
                        }else {
                            return new LiteFlowElement(LiteFlowElementType.NORMAL_COMPONENT, liteFlowService.getLiteFlowComponentNameByPsiClass(c), c);
                        }
                    } else if (o instanceof PsiMethod) {
                        PsiMethod c = (PsiMethod) o;
                        if (liteFlowService.isLiteFlowIfComponent(o)){
                            return new LiteFlowElement(LiteFlowElementType.IF_COMPONENT, liteFlowService.getLiteFlowComponentNameByPsiMethod(c), c);
                        } else if (liteFlowService.isLiteFlowSwitchComponent(o)) {
                            return new LiteFlowElement(LiteFlowElementType.SWITCH_COMPONENT, liteFlowService.getLiteFlowComponentNameByPsiMethod(c),c);
                        }else if (liteFlowService.isLiteFlowForComponent(o)) {
                            return new LiteFlowElement(LiteFlowElementType.FOR_COMPONENT, liteFlowService.getLiteFlowComponentNameByPsiMethod(c), c);
                        }else if (liteFlowService.isLiteFlowIteratorComponent(o)) {
                            return new LiteFlowElement(LiteFlowElementType.ITERATOR_COMPONENT, liteFlowService.getLiteFlowComponentNameByPsiMethod(c), c);
                        }else if (liteFlowService.isLiteFlowWhileComponent(o)) {
                            return new LiteFlowElement(LiteFlowElementType.WHILE_COMPONENT, liteFlowService.getLiteFlowComponentNameByPsiMethod(c), c);
                        }else if (liteFlowService.isLiteFlowBreakComponent(o)) {
                            return new LiteFlowElement(LiteFlowElementType.BREAK_COMPONENT, liteFlowService.getLiteFlowComponentNameByPsiMethod(c), c);
                        }else {
                            return new LiteFlowElement(LiteFlowElementType.NORMAL_COMPONENT, liteFlowService.getLiteFlowComponentNameByPsiMethod(c), c);
                        }
                    } else if (o instanceof XmlTag){
                        XmlTagImpl c = (XmlTagImpl) o;
                        if (liteFlowService.isLiteFlowScriptIfComponent(o)){
                            return new LiteFlowElement(LiteFlowElementType.IF_SCRIPT, liteFlowService.getLiteFlowComponentNameByXmlTag(c), c);
                        } else if (liteFlowService.isLiteFlowScriptSwitchComponent(o)) {
                            return new LiteFlowElement(LiteFlowElementType.SWITCH_SCRIPT, liteFlowService.getLiteFlowComponentNameByXmlTag(c), c);
                        }else if (liteFlowService.isLiteFlowScriptForComponent(o)) {
                            return new LiteFlowElement(LiteFlowElementType.FOR_SCRIPT, liteFlowService.getLiteFlowComponentNameByXmlTag(c), c);
                        }else if (liteFlowService.isLiteFlowScriptWhileComponent(o)) {
                            return new LiteFlowElement(LiteFlowElementType.WHILE_SCRIPT, liteFlowService.getLiteFlowComponentNameByXmlTag(c), c);
                        }else if (liteFlowService.isLiteFlowScriptBreakComponent(o)) {
                            return new LiteFlowElement(LiteFlowElementType.BREAK_SCRIPT, liteFlowService.getLiteFlowComponentNameByXmlTag(c), c);
                        }else {
                            return new LiteFlowElement(LiteFlowElementType.NORMAL_SCRIPT, liteFlowService.getLiteFlowComponentNameByXmlTag(c), c);
                        }
                    }
                    return null;
                })
                .collect(Collectors.toList()));

        PsiElement[] liteFlowChains = LiteFlowService.getInstance(project).findAllLiteFlowChain();
        map.put("Chains", Arrays.stream(liteFlowChains)
                .map(o -> {
                    if (o instanceof XmlTag){
                        String id = ((XmlTag) o).getAttributeValue("id");
                        if (StringUtil.isEmpty(id)){
                            id = ((XmlTag) o).getAttributeValue("name");
                        }
                        return new LiteFlowElement(LiteFlowElementType.CHAIN, id, o);
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
