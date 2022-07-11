package top.xystudio.plugin.idea.liteflowx.system.language.psi;

import com.intellij.lang.Language;
import com.intellij.psi.tree.IElementType;
import org.jetbrains.annotations.NonNls;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import top.xystudio.plugin.idea.liteflowx.system.language.LiteFlowLanguage;

public class LiteFlowTokenType extends IElementType {

    public LiteFlowTokenType(@NonNls @NotNull String debugName) {
        super(debugName, LiteFlowLanguage.INSTANCE);
    }

    @Override
    public String toString() {
        return "LiteFlowTokenType." + super.toString();
    }
}
