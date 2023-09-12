package top.xystudio.plugin.idea.liteflowx.toolwindow.tree;

import javax.swing.event.TreeModelListener;
import javax.swing.tree.TreeModel;
import javax.swing.tree.TreePath;

public class NavigateTreeModel implements TreeModel {

    public NavigateTreeModel(){}

    public NavigateTreeModel(NavigateTreeNode navigateTreeNode){
        this.navigateTreeNode = navigateTreeNode;
    }

    private NavigateTreeNode navigateTreeNode;

    public void setRoot(NavigateTreeNode navigateTreeNode) {
        this.navigateTreeNode = navigateTreeNode;
    }

    @Override
    public Object getRoot() {
        return this.navigateTreeNode;
    }

    @Override
    public Object getChild(Object parent, int index) {
        NavigateTreeNode parentNode = (NavigateTreeNode) parent;
        return parentNode.getChildren().get(index);
    }

    @Override
    public int getChildCount(Object parent) {
        NavigateTreeNode parentNode = (NavigateTreeNode) parent;
        return parentNode.getChildren().size();
    }

    @Override
    public boolean isLeaf(Object node) {
        NavigateTreeNode treeNode = (NavigateTreeNode) node;
        return treeNode.getChildren() == null || treeNode.getChildren().isEmpty();
    }

    @Override
    public void valueForPathChanged(TreePath path, Object newValue) {

    }

    @Override
    public int getIndexOfChild(Object parent, Object child) {
        NavigateTreeNode parentNode = (NavigateTreeNode) parent;
        NavigateTreeNode childNode = (NavigateTreeNode) child;
        return parentNode.getChildren().indexOf(childNode);
    }

    @Override
    public void addTreeModelListener(TreeModelListener l) {

    }

    @Override
    public void removeTreeModelListener(TreeModelListener l) {

    }
}
