package top.xystudio.plugin.idea.liteflowx.toolwindow.tree;

import com.intellij.icons.AllIcons;
import com.intellij.ui.ColoredTreeCellRenderer;
import icons.LiteFlowIcons;
import org.jetbrains.annotations.NotNull;

import javax.swing.*;

public class NavigateTreeCellRenderer extends ColoredTreeCellRenderer {

    @Override
    public void customizeCellRenderer(@NotNull JTree tree, Object value, boolean selected, boolean expanded, boolean leaf, int row, boolean hasFocus) {

        NavigateTreeNode treeNode = (NavigateTreeNode) value;
        if (treeNode == null) return;

        // 设置 Icon
        switch (treeNode.getType()){
            case ROOT -> setIcon(AllIcons.General.Tree);
            case MODULE -> setIcon(AllIcons.Nodes.Module);
            case PACKAGE -> setIcon(AllIcons.Nodes.Package);
            case MAIN -> setIcon(AllIcons.Modules.SourceRoot);
            case TEST -> setIcon(AllIcons.Modules.TestRoot);
            case RESOURCE -> setIcon(AllIcons.Modules.ResourcesRoot);
            case CLASS -> setIcon(LiteFlowIcons.MULTI_COMPONENT_ICON);
            case COMPONENT -> {
                switch (treeNode.getLiteFlowNode().getNodeType()){
                    case ITERATOR -> setIcon(LiteFlowIcons.ITERATOR_COMPONENT_ICON);
                    case WHILE -> setIcon(LiteFlowIcons.WHI_COMPONENT_ICON);
                    case SWITCH -> setIcon(LiteFlowIcons.SW_COMPONENT_ICON);
                    case BREAK -> setIcon(LiteFlowIcons.BRK_COMPONENT_ICON);
                    case FOR -> setIcon(LiteFlowIcons.FOR_COMPONENT_ICON);
                    case IF -> setIcon(LiteFlowIcons.IF_COMPONENT_ICON);
                    case COMMON -> setIcon(LiteFlowIcons.COMMON_COMPONENT_ICON);
                }
            }
            case ELF -> setIcon(LiteFlowIcons.LITEFLOWX_ICON);
            case CHAIN -> setIcon(LiteFlowIcons.CHAIN_ICON);
        }

        // 设置文本
        append(treeNode.getTitle());

    }
}
