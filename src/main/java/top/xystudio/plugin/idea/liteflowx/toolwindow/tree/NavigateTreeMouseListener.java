package top.xystudio.plugin.idea.liteflowx.toolwindow.tree;

import com.intellij.psi.PsiClass;
import com.intellij.ui.treeStructure.SimpleTree;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import javax.swing.*;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.TreePath;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class NavigateTreeMouseListener extends MouseAdapter {

    private SimpleTree simpleTree;

    public NavigateTreeMouseListener(SimpleTree navigateTree) {
        simpleTree = navigateTree;
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        if (SwingUtilities.isLeftMouseButton(e) && e.getClickCount() > 0 && e.getClickCount() % 2 == 0){
            NavigateTreeNode node = getLastSelectedTreeNode(this.simpleTree);
            if (node != null && node.getType() == NavigateTreeNodeType.COMPONENT && e.getClickCount() == 2) {
                if (node.getLiteFlowNode() != null) node.getLiteFlowNode().getNaviTarget().navigate(true);
            }
        }
    }

    @Nullable
    private NavigateTreeNode getLastSelectedTreeNode(@NotNull JTree tree) {
        return (NavigateTreeNode) tree.getLastSelectedPathComponent();
    }
}
