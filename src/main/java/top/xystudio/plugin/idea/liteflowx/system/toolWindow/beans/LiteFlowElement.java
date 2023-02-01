package top.xystudio.plugin.idea.liteflowx.system.toolWindow.beans;

import com.intellij.openapi.editor.Document;
import com.intellij.pom.Navigatable;
import com.intellij.psi.*;
import com.intellij.psi.xml.XmlTag;
import com.intellij.util.DocumentUtil;
import icons.LiteFlowIcons;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import javax.swing.*;
import java.util.Objects;


public class LiteFlowElement {

    private final PsiElement psiElement;
    @Nullable
    private LiteFlowElementType liteFlowElementType;
    @Nullable
    private String name;
    @Nullable
    private String subName;

    public LiteFlowElement(LiteFlowElementType elementType, @Nullable String name, @Nullable PsiElement psiElement) {

        Document document = PsiDocumentManager.getInstance(psiElement.getProject()).getDocument(psiElement.getContainingFile());
        int lineNumber = document.getLineNumber(psiElement.getTextOffset()) + 1;

        this.setLiteFlowElementType(elementType);

        if (name != null) {
            this.setName(name);
        }
        this.psiElement = psiElement;

        switch (elementType){
            case NORMAL_SCRIPT:
            case IF_SCRIPT:
            case SWITCH_SCRIPT:
            case FOR_SCRIPT:
            case WHILE_SCRIPT:
            case BREAK_SCRIPT:{
                if (psiElement instanceof XmlTag){
                    this.subName = psiElement.getContainingFile().getVirtualFile().getName() + ": " + lineNumber;
                }
                break;
            }
            case NORMAL_COMPONENT:
            case IF_COMPONENT:
            case FOR_COMPONENT:
            case ITERATOR_COMPONENT:
            case BREAK_COMPONENT:
            case WHILE_COMPONENT:
            case SWITCH_COMPONENT: {
                if (psiElement instanceof PsiClass) {
                    this.subName = ((PsiClass) psiElement).getQualifiedName();
                } else if (psiElement instanceof PsiMethod) {
                    this.subName = ((PsiMethod) psiElement).getContainingClass().getQualifiedName() + "# " + ((PsiMethod) psiElement).getName();
                }
                break;
            }
            case CHAIN:{
                this.subName = psiElement.getContainingFile().getVirtualFile().getName() + ": " + lineNumber;
                break;
            }
            case Element:
            default: {
                this.subName = null;
                break;
            }
        }
    }

    public void navigate(boolean focus) {
        if (psiElement != null) {
            ((Navigatable)psiElement).navigate(focus);
        }
    }

    @Nullable
    public PsiElement getPsiElement() {
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
            case NORMAL_SCRIPT:
                return LiteFlowIcons.COMMON_SCRIPT_ICON;
            case IF_SCRIPT:
                return LiteFlowIcons.IF_SCRIPT_ICON;
            case FOR_SCRIPT:
                return LiteFlowIcons.FOR_SCRIPT_ICON;
            case SWITCH_SCRIPT:
                return LiteFlowIcons.SW_SCRIPT_ICON;
            case WHILE_SCRIPT:
                return LiteFlowIcons.WHI_SCRIPT_ICON;
            case BREAK_SCRIPT:
                return LiteFlowIcons.BRK_SCRIPT_ICON;
            case NORMAL_COMPONENT:
                return LiteFlowIcons.COMMON_COMPONENT_ICON;
            case WHILE_COMPONENT:
                return LiteFlowIcons.WHI_COMPONENT_ICON;
            case ITERATOR_COMPONENT:
                return LiteFlowIcons.ITERATOR_COMPONENT_ICON;
            case FOR_COMPONENT:
                return LiteFlowIcons.FOR_COMPONENT_ICON;
            case IF_COMPONENT:
                return LiteFlowIcons.IF_COMPONENT_ICON;
            case SWITCH_COMPONENT:
                return LiteFlowIcons.SW_COMPONENT_ICON;
            case BREAK_COMPONENT:
                return LiteFlowIcons.BRK_COMPONENT_ICON;
            case CHAIN:
                return LiteFlowIcons.CHAIN_ICON;
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
