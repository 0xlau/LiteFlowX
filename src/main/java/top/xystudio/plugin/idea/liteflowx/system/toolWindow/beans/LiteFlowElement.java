package top.xystudio.plugin.idea.liteflowx.system.toolWindow.beans;

import com.intellij.psi.NavigatablePsiElement;
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

    public LiteFlowElement(LiteFlowElementType elementType, @Nullable String name, @Nullable NavigatablePsiElement psiElement) {
        this.setLiteFlowElementType(elementType);
        if (name != null) {
            this.setName(name);
        }
        this.psiElement = psiElement;
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

    @NotNull
    public Icon getIcon() {
        switch (liteFlowElementType){
            case COMPONENT:
                return LiteFlowIcons.COMPONENT_CLASS_FILE_ICON;
            case NODE:
                return LiteFlowIcons.NODE_LINE_MARKER_ICON;
            case CHAIN:
                return LiteFlowIcons.CHAIN_LINE_MARKER_ICON;
            case SLOT:
                return LiteFlowIcons.SLOT_CLASS_FILE_ICON;
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
