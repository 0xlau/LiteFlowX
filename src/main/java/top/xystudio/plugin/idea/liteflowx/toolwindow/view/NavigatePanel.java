package top.xystudio.plugin.idea.liteflowx.toolwindow.view;

import com.intellij.icons.AllIcons;
import com.intellij.openapi.actionSystem.ActionManager;
import com.intellij.openapi.actionSystem.ActionToolbar;
import com.intellij.openapi.actionSystem.DefaultActionGroup;
import com.intellij.ui.IdeBorderFactory;
import com.intellij.ui.ScrollPaneFactory;
import com.intellij.ui.SideBorder;
import com.intellij.ui.components.JBScrollPane;
import com.intellij.ui.components.JBViewport;
import com.intellij.ui.tree.ui.DefaultTreeUI;
import com.intellij.ui.treeStructure.SimpleTree;
import top.xystudio.plugin.idea.liteflowx.toolwindow.action.MyAnAction;
import top.xystudio.plugin.idea.liteflowx.toolwindow.constant.NavigateWindowConstant;
import top.xystudio.plugin.idea.liteflowx.toolwindow.service.NavigateWindowService;
import top.xystudio.plugin.idea.liteflowx.toolwindow.tree.NavigateTreeCellRenderer;
import top.xystudio.plugin.idea.liteflowx.toolwindow.tree.NavigateTreeModel;
import top.xystudio.plugin.idea.liteflowx.toolwindow.tree.NavigateTreeMouseListener;

import javax.swing.*;
import java.awt.*;

public class NavigatePanel extends JPanel implements NavigateWindowConstant {


    private SimpleTree navigateTree;
    private ActionToolbar toolWindowPanel;
    private NavigateWindowService navigateWindowService;


    public NavigatePanel(NavigateWindowService service){
        navigateWindowService = service;
        buildGUI();
    }

    private void buildGUI() {

        this.setLayout(new BorderLayout());

        // 建立 Toolbar
        DefaultActionGroup actionGroup = new DefaultActionGroup();
        actionGroup.add(new MyAnAction("Refresh", null, AllIcons.Actions.Refresh, e -> {
            this.navigateWindowService.getLiteFlowNodeService().reIndexSync();
            this.navigateWindowService.reloadNavigateTreeNode();
            this.navigateTree.setModel(new NavigateTreeModel(navigateWindowService.getNavigateRootTreeNode()));
        }));
        actionGroup.addSeparator();
        actionGroup.add(new MyAnAction("Expend All", null, AllIcons.Actions.Expandall, e -> {
            for (int i = 0; i < this.navigateTree.getRowCount(); i++) {
                this.navigateTree.expandRow(i);
            }
        }));
        actionGroup.add(new MyAnAction("Collapse All", null, AllIcons.Actions.Collapseall, e -> {
            for (int i = 0; i < this.navigateTree.getRowCount(); i++) {
                this.navigateTree.collapseRow(i);
            }
        }));
        this.toolWindowPanel = ActionManager.getInstance().createActionToolbar(ID_ACTION_TOOLBAR, actionGroup, true);

        // 建立 navigateTree
        this.navigateTree = new SimpleTree(new NavigateTreeModel(navigateWindowService.getNavigateRootTreeNode()));
        this.navigateTree.setCellRenderer(new NavigateTreeCellRenderer());
        this.navigateTree.addMouseListener(new NavigateTreeMouseListener(this.navigateTree));
        this.navigateTree.getEmptyText().setText("Please press \"Refresh\" button to load resources about liteflow");
        this.navigateTree.setRootVisible(false);
        this.navigateTree.setShowsRootHandles(true);
        this.navigateTree.setDragEnabled(false);
        this.navigateTree.setEditable(false);
        this.navigateTree.setToggleClickCount(1);
        this.navigateTree.setUI(new DefaultTreeUI());

        JBViewport viewport = new JBViewport();
        JBScrollPane scrollPane = new JBScrollPane(this.navigateTree);

        scrollPane.setBorder(IdeBorderFactory.createBorder(SideBorder.NONE));
        viewport.setView(scrollPane);

        this.add(this.toolWindowPanel.getComponent(), BorderLayout.NORTH);
        this.add(viewport, BorderLayout.CENTER);
    }

}
