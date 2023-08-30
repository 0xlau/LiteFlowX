package top.xystudio.plugin.idea.liteflowx.language.editing;

import com.intellij.lang.BracePair;
import com.intellij.lang.PairedBraceMatcher;
import com.intellij.psi.PsiFile;
import com.intellij.psi.tree.IElementType;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import top.xystudio.plugin.idea.liteflowx.system.language.psi.LiteFlowTypes;

public class LiteFlowBraceMatcher implements PairedBraceMatcher {

    private static final BracePair[] PAIRS = {
            new BracePair(LiteFlowTypes.LITEFLOW_BRACE_LEFT, LiteFlowTypes.LITEFLOW_BRACE_RIGHT, true),
            new BracePair(LiteFlowTypes.LITEFLOW_BRACK_LEFT, LiteFlowTypes.LITEFLOW_BRACK_RIGHT, false),
            new BracePair(LiteFlowTypes.LITEFLOW_PAREN_LEFT, LiteFlowTypes.LITEFLOW_PAREN_RIGHT, false),
    };

    @Override
    public BracePair @NotNull [] getPairs() {
        return PAIRS;
    }

    @Override
    public boolean isPairedBracesAllowedBeforeType(@NotNull IElementType lbraceType, @Nullable IElementType contextType) {
        return true;
    }

    @Override
    public int getCodeConstructStart(PsiFile file, int openingBraceOffset) {
        return openingBraceOffset;
    }
}
