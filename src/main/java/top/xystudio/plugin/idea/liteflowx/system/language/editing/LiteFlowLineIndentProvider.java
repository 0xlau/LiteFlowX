package top.xystudio.plugin.idea.liteflowx.system.language.editing;

import com.google.common.collect.ImmutableMap;
import com.intellij.lang.Language;
import com.intellij.psi.TokenType;
import com.intellij.psi.impl.source.codeStyle.SemanticEditorPosition;
import com.intellij.psi.impl.source.codeStyle.lineIndent.JavaLikeLangLineIndentProvider;
import com.intellij.psi.tree.IElementType;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import top.xystudio.plugin.idea.liteflowx.system.language.LiteFlowLanguage;
import top.xystudio.plugin.idea.liteflowx.system.language.psi.LiteFlowTypes;

public class LiteFlowLineIndentProvider extends JavaLikeLangLineIndentProvider {

    private static final ImmutableMap<IElementType, SemanticEditorPosition.SyntaxElement> SYNTAX_MAP;

    static {
        SYNTAX_MAP =
                ImmutableMap.<IElementType, SemanticEditorPosition.SyntaxElement>builder()
                        .put(TokenType.WHITE_SPACE, JavaLikeElement.Whitespace)
                        .put(LiteFlowTypes.LITEFLOW_BRACE_LEFT, JavaLikeElement.BlockOpeningBrace)
                        .put(LiteFlowTypes.LITEFLOW_BRACE_RIGHT, JavaLikeElement.BlockClosingBrace)
                        .put(LiteFlowTypes.LITEFLOW_PAREN_LEFT, JavaLikeElement.BlockOpeningBrace)
                        .put(LiteFlowTypes.LITEFLOW_PAREN_RIGHT, JavaLikeElement.BlockClosingBrace)
                        .put(LiteFlowTypes.LITEFLOW_BRACK_LEFT, JavaLikeElement.ArrayOpeningBracket)
                        .put(LiteFlowTypes.LITEFLOW_BRACK_RIGHT, JavaLikeElement.ArrayClosingBracket)
                        .put(LiteFlowTypes.LITEFLOW_SEMICOLON, JavaLikeElement.Semicolon)
                        .put(LiteFlowTypes.LITEFLOW_COMMA, JavaLikeElement.Comma)
                        .put(LiteFlowTypes.LITEFLOW_BLOCK_COMMENT, JavaLikeElement.BlockComment)
                        .put(LiteFlowTypes.LITEFLOW_LINE_COMMENT, JavaLikeElement.LineComment)
                        .build();
    }

    @Nullable
    @Override
    protected SemanticEditorPosition.SyntaxElement mapType(@NotNull IElementType tokenType) {
        return SYNTAX_MAP.get(tokenType);
    }

    @Override
    public boolean isSuitableForLanguage(@NotNull Language language) {
        return language.isKindOf(LiteFlowLanguage.INSTANCE);
    }
}
