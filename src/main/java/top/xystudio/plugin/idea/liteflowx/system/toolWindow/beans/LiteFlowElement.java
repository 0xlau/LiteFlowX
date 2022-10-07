package top.xystudio.plugin.idea.liteflowx.system.toolWindow.beans;

import com.intellij.psi.NavigatablePsiElement;
import com.intellij.psi.PsiClass;
import com.intellij.psi.PsiMethod;
import icons.LiteFlowIcons;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import javax.swing.*;
import java.util.Objects;


public class LiteFlowElement {

    private final NavigatablePsiElement psiElement;
    @Nullable
    private LiteFlowElementType liteFlowElementType;
    @Nullable
    private String name;
    @Nullable
    private String subName;

    public LiteFlowElement(LiteFlowElementType elementType, @Nullable String name, @Nullable NavigatablePsiElement psiElement) {

        this.setLiteFlowElementType(elementType);

        if (name != null) {
            this.setName(name);
        }
        this.psiElement = psiElement;

        switch (elementType){
            case NORMAL_COMPONENT:
            case IF_COMPONENT:
            case FOR_COMPONENT:
            case BREAK_COMPONENT:
            case WHILE_COMPONENT:
            case SWITCH_COMPONENT:
                if (psiElement instanceof PsiClass){
                    this.subName = ((PsiClass)psiElement).getQualifiedName();
                } else if (psiElement instanceof PsiMethod) {
                    this.subName = ((PsiMethod)psiElement).getContainingClass().getQualifiedName() + "#" + ((PsiMethod)psiElement).getName();
                }
                break;
            case CHAIN:
                this.subName = psiElement.getContainingFile().getVirtualFile().getName();
                break;
            case Element:
            default:
                this.subName = null;
                break;
        }
    }

    public LiteFlowElement(LiteFlowElementType elementType, @Nullable String name, @Nullable String subName, @Nullable NavigatablePsiElement psiElement) {
        this.setLiteFlowElementType(elementType);
        if (name != null) {
            this.setName(name);
        }
        this.psiElement = psiElement;
        this.subName = subName;
    }

    public void navigate(boolean focus) {
        if (psiElement != null) {
            psiElement.navigate(focus);
        }
    }

    @Nullable
    public NavigatablePsiElement getPsiElement() {
        return psiElement;
    }

    public LiteFlowElementType getLiteFlowElementType() {
        return liteFlowElementType;
    }

    public void setLiteFlowElementType(LiteFlowElementType liteFlowElementType) {
        this.liteFlowElementType = liteFlowElementType;
    }

    @Nullable
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Nullable
    public String getSubName(){
        return this.subName;
    }

    @NotNull
    public Icon getIcon() {
        switch (liteFlowElementType){
            case NORMAL_COMPONENT:
                return LiteFlowIcons.NORMAL_COMPONENT_CLASS_FILE_ICON;
            case WHILE_COMPONENT:
                return LiteFlowIcons.WHI_COMPONENT_CLASS_FILE_ICON;
            case FOR_COMPONENT:
                return LiteFlowIcons.FOR_COMPONENT_CLASS_FILE_ICON;
            case IF_COMPONENT:
                return LiteFlowIcons.IF_COMPONENT_CLASS_FILE_ICON;
            case SWITCH_COMPONENT:
                return LiteFlowIcons.SW_COMPONENT_CLASS_FILE_ICON;
            case BREAK_COMPONENT:
                return LiteFlowIcons.BRK_COMPONENT_CLASS_FILE_ICON;
            case CHAIN:
                return LiteFlowIcons.CHAIN_LINE_MARKER_ICON_14x14;
            case Element:
            default:
                return LiteFlowIcons.TOOL_WINDOW_ICON;
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        LiteFlowElement request = (LiteFlowElement) o;
        if (liteFlowElementType != request.liteFlowElementType) {
            return false;
        }
        return Objects.equals(name, request.name);
    }

    @Override
    public int hashCode() {
        int result = liteFlowElementType != null ? liteFlowElementType.hashCode() : 0;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        return result;
    }

}
