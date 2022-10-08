package top.xystudio.plugin.idea.liteflowx.system.toolWindow.frame;

import com.intellij.openapi.project.Project;
import com.intellij.psi.PsiElement;
import com.intellij.ui.border.CustomLineBorder;
import com.intellij.ui.components.JBScrollPane;
import com.intellij.ui.treeStructure.SimpleTree;
import com.intellij.ui.treeStructure.Tree;
import com.intellij.util.ui.JBUI;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import top.xystudio.plugin.idea.liteflowx.system.toolWindow.beans.CategoryTree;
import top.xystudio.plugin.idea.liteflowx.system.toolWindow.beans.LiteFlowElement;
import top.xystudio.plugin.idea.liteflowx.system.toolWindow.renderer.LiteFlowTreeCellRenderer;

import javax.swing.*;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;


public class LiteFlowTree extends JBScrollPane{

    private final Project project;

    private final Tree tree;

    private final Map<PsiElement, LiteFlowElementNode> elementNodeMap;

    public LiteFlowTree(@NotNull Project project) {

        this.project = project;
        this.elementNodeMap = new HashMap<>();
        this.tree = new SimpleTree();

        this.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        this.setBorder(new CustomLineBorder(JBUI.insetsTop(1)));

        DefaultTreeModel model = (DefaultTreeModel) tree.getModel();
        model.setRoot(new DefaultMutableTreeNode());
        tree.setCellRenderer(new LiteFlowTreeCellRenderer());
        tree.setRootVisible(true);
        tree.setShowsRootHandles(false);
        this.setViewportView(tree);

        initEvent();

    }

    private void initEvent() {
        // LiteFlowTree子项双击监听
        tree.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                final int doubleClick = 2;
                if (SwingUtilities.isLeftMouseButton(e) && e.getClickCount() > 0 && e.getClickCount() % doubleClick == 0) {
                    LiteFlowElement node = getLastSelectedTreeNode(tree);
                    if (node != null && e.getClickCount() == doubleClick) {
                        node.navigate(true);
                    }
                }
            }
        });
    }

    @Nullable
    private LiteFlowElement getLastSelectedTreeNode(@NotNull JTree tree) {
        DefaultMutableTreeNode mutableTreeNode = (DefaultMutableTreeNode) tree.getLastSelectedPathComponent();
        if (mutableTreeNode == null) {
            return null;
        }
        Object userObject = mutableTreeNode.getUserObject();
        if (userObject instanceof LiteFlowElement) {
            return (LiteFlowElement) userObject;
        }
        return null;
    }

    public void renderTree(@NotNull Map<String, List<LiteFlowElement>> map) {
        AtomicInteger elementCount = new AtomicInteger();

        TreeNode<String> root = new TreeNode<>("Not found any liteflow elements");
        map.forEach((itemName, elements) -> {
            CategoryNode categoryNode = new CategoryNode(new CategoryTree(itemName, elements.size()));
            if (elements.size() > 0){
                elements.forEach(element -> {
                    LiteFlowElementNode node = new LiteFlowElementNode(element);
                    categoryNode.add(node);
                    elementCount.incrementAndGet();
                });
                root.add(categoryNode);
            }
        });


        tree.firePropertyChange(JTree.ROOT_VISIBLE_PROPERTY, tree.isRootVisible(), elementCount.get() < 1);
        tree.setEnabled(elementCount.get() > 0);

        ((DefaultTreeModel) this.tree.getModel()).setRoot(root);

    }

    public static class TreeNode<T> extends DefaultMutableTreeNode {

        private final T data;

        public TreeNode(@NotNull T data) {
            super(data);
            this.data = data;
        }

        @NotNull
        public T getData() {
            return data;
        }
    }

    public static class CategoryNode extends TreeNode<CategoryTree> {

        public CategoryNode(@NotNull CategoryTree data) {
            super(data);
        }
    }

    public static class LiteFlowElementNode extends TreeNode<LiteFlowElement> {

        public LiteFlowElementNode(@NotNull LiteFlowElement data) {
            super(data);
        }
    }
}
