package top.xystudio.plugin.idea.liteflowx.toolwindow.view;

import com.intellij.icons.AllIcons;
import com.intellij.openapi.actionSystem.DefaultActionGroup;
import top.xystudio.plugin.idea.liteflowx.toolwindow.service.CategoryWindowService;

import javax.swing.*;

public class CategoryPanel extends JPanel {

    private CategoryWindowService categoryWindowService;

    public CategoryPanel(CategoryWindowService service){
        categoryWindowService = service;
        buildGUI();
    }

    private void buildGUI() {
        DefaultActionGroup actionGroup = new DefaultActionGroup();
    }



}
