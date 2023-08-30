package top.xystudio.plugin.idea.liteflowx.toolWindow.beans;

import com.intellij.icons.AllIcons;

import javax.swing.*;

public class CategoryTree {

    /**
     * 分类名称
     */
    private final String categoryName;
    /**
     * 元素数量
     */
    private final Integer elementCounts;

    /**
     * 图标
     */
    private final Icon icon;

    public CategoryTree(String categoryName, Integer elementCounts) {
        this.categoryName = categoryName;
        this.elementCounts = elementCounts;
        this.icon = AllIcons.Modules.SourceRoot;
    }

    public CategoryTree(String categoryName, Integer elementCounts, Icon icon) {
        this.categoryName = categoryName;
        this.elementCounts = elementCounts;
        this.icon = icon;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public Integer getElementCounts() {
        return elementCounts;
    }

    public Icon getIcon() {
        return icon;
    }

    @Override
    public String toString() {
        return String.format(
                "[%d]%s",
                elementCounts,
                categoryName
        );
    }

}
