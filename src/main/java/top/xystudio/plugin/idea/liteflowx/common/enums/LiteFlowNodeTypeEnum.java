package top.xystudio.plugin.idea.liteflowx.common.enums;

import com.intellij.psi.PsiClass;
import top.xystudio.plugin.idea.liteflowx.common.constant.Clazz;
import top.xystudio.plugin.idea.liteflowx.common.constant.NodeTypeEnum;

import java.util.Objects;

/**
 * 七种LiteFlow组件类型，不区分脚本
 */
public enum LiteFlowNodeTypeEnum {

    COMMON, SWITCH, IF, FOR, WHILE, ITERATOR, BREAK;

    public static LiteFlowNodeTypeEnum getByPsiClass(PsiClass psiClass){
        if (psiClass == null) return null;
        switch (Objects.requireNonNull(psiClass.getQualifiedName())) {
            case Clazz.NodeComponent -> {return COMMON;}
            case Clazz.NodeSwitchComponent -> {return SWITCH;}
            case Clazz.NodeIfComponent -> {return IF;}
            case Clazz.NodeForComponent -> {return FOR;}
            case Clazz.NodeWhileComponent -> {return WHILE;}
            case Clazz.NodeIteratorComponent -> {return ITERATOR;}
            case Clazz.NodeBreakComponent -> {return BREAK;}
            default -> {return null;}
        }
    }

    public static LiteFlowNodeTypeEnum getByQualifiedName(String qualifiedName){
        if (qualifiedName == null) return null;
        switch (qualifiedName) {
            case Clazz.NodeComponent -> {return COMMON;}
            case Clazz.NodeSwitchComponent -> {return SWITCH;}
            case Clazz.NodeIfComponent -> {return IF;}
            case Clazz.NodeForComponent -> {return FOR;}
            case Clazz.NodeWhileComponent -> {return WHILE;}
            case Clazz.NodeIteratorComponent -> {return ITERATOR;}
            case Clazz.NodeBreakComponent -> {return BREAK;}
            default -> {return null;}
        }
    }

    public static LiteFlowNodeTypeEnum getByNodeType(String nodeType) {
        if (nodeType == null) return null;
        switch (nodeType) {
            case NodeTypeEnum.COMMON, NodeTypeEnum.SCRIPT -> {return COMMON;}
            case NodeTypeEnum.SWITCH, NodeTypeEnum.SWITCH_SCRIPT -> {return SWITCH;}
            case NodeTypeEnum.IF, NodeTypeEnum.IF_SCRIPT -> {return IF;}
            case NodeTypeEnum.FOR, NodeTypeEnum.FOR_SCRIPT -> {return FOR;}
            case NodeTypeEnum.WHILE, NodeTypeEnum.WHILE_SCRIPT -> {return WHILE;}
            case NodeTypeEnum.ITERATOR, NodeTypeEnum.ITERATOR_SCRIPT -> {return ITERATOR;}
            case NodeTypeEnum.BREAK, NodeTypeEnum.BREAK_SCRIPT -> {return BREAK;}
            default -> {return null;}
        }
    }


}
