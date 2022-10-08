package top.xystudio.plugin.idea.liteflowx.system.toolWindow.renderer;

import com.intellij.ui.ColoredTreeCellRenderer;
import com.intellij.ui.SimpleTextAttributes;
import org.jetbrains.annotations.NotNull;
import top.xystudio.plugin.idea.liteflowx.system.toolWindow.beans.CategoryTree;
import top.xystudio.plugin.idea.liteflowx.system.toolWindow.beans.LiteFlowElement;
import top.xystudio.plugin.idea.liteflowx.system.toolWindow.frame.LiteFlowTree;

import javax.swing.*;

public class LiteFlowTreeCellRenderer extends ColoredTreeCellRenderer {


    @Override
    public void customizeCellRenderer(@NotNull JTree tree, Object value, boolean selected, boolean expanded, boolean leaf, int row, boolean hasFocus) {
        if (value instanceof LiteFlowTree.CategoryNode){
            LiteFlowTree.CategoryNode node = (LiteFlowTree.CategoryNode) value;
            CategoryTree data = node.getData();
            setIcon(data.getIcon());
            append(data.toString());
        }else if(value instanceof LiteFlowTree.LiteFlowElementNode){
            LiteFlowTree.LiteFlowElementNode node = (LiteFlowTree.LiteFlowElementNode) value;
            LiteFlowElement data = node.getData();
            setIcon(data.getIcon());
            append(data.getName());
            String subName = data.getSubName();
            if (subName != null){
                append(" - " + subName, SimpleTextAttributes.GRAYED_ATTRIBUTES);
            }
        }else if (value instanceof LiteFlowTree.TreeNode<?>) {
            LiteFlowTree.TreeNode<?> node = (LiteFlowTree.TreeNode<?>) value;
            append(node.toString());
        }
    }
}
