package top.xystudio.plugin.idea.liteflowx.system.toolWindow.beans;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Arrays;

public enum LiteFlowElementType {

    Element,
    COMPONENT,
    CHAIN,
    NODE,
    SLOT;

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
