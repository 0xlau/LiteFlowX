package top.xystudio.plugin.idea.liteflowx.toolWindow.beans;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Arrays;

public enum LiteFlowElementType {

    Element,
    NORMAL_COMPONENT,
    IF_COMPONENT,
    SWITCH_COMPONENT,
    FOR_COMPONENT,
    ITERATOR_COMPONENT,
    WHILE_COMPONENT,
    BREAK_COMPONENT,
    CHAIN,
    IF_SCRIPT,
    SWITCH_SCRIPT,
    FOR_SCRIPT,
    WHILE_SCRIPT,
    BREAK_SCRIPT,
    NORMAL_SCRIPT;

    @NotNull
    public static LiteFlowElementType[] getValues(){
        return Arrays.stream(LiteFlowElementType.values()).filter(o -> !o.equals(LiteFlowElementType.Element)).toArray(LiteFlowElementType[]::new);
    }


    @NotNull
    public static LiteFlowElementType parse(@Nullable Object object) {
        try {
            assert object != null;
            if (object instanceof LiteFlowElementType) {
                return (LiteFlowElementType) object;
            }
            return LiteFlowElementType.valueOf(object.toString());
        } catch (Exception ignore) {
            return LiteFlowElementType.Element;
        }
    }

}
