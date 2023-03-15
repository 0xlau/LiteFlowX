package top.xystudio.plugin.idea.liteflowx.system.language.highlight;

import com.intellij.lexer.Lexer;
import com.intellij.openapi.editor.DefaultLanguageHighlighterColors;
import com.intellij.openapi.editor.HighlighterColors;
import com.intellij.openapi.editor.colors.TextAttributesKey;
import com.intellij.openapi.fileTypes.SyntaxHighlighterBase;
import com.intellij.psi.TokenType;
import com.intellij.psi.tree.IElementType;
import org.jetbrains.annotations.NotNull;
import top.xystudio.plugin.idea.liteflowx.system.language.LiteFlowLexerAdapter;
import top.xystudio.plugin.idea.liteflowx.system.language.psi.LiteFlowTypes;

import static com.intellij.openapi.editor.colors.TextAttributesKey.createTextAttributesKey;

public class LiteFlowSyntaxHighlighter extends SyntaxHighlighterBase {

    public static final TextAttributesKey BLOCK_COMMENT =
            createTextAttributesKey("blockComment", DefaultLanguageHighlighterColors.BLOCK_COMMENT);

    public static final TextAttributesKey LINE_COMMENT =
            createTextAttributesKey("lineComment", DefaultLanguageHighlighterColors.LINE_COMMENT);

    public static final TextAttributesKey QLEXPRESS_VARIABLES =
            createTextAttributesKey("QLExpressVariables", DefaultLanguageHighlighterColors.LOCAL_VARIABLE);

    public static final TextAttributesKey QLEXPRESS_KEYWORDS =
            createTextAttributesKey("QLExpressKeywords", DefaultLanguageHighlighterColors.KEYWORD);

    public static final TextAttributesKey LITEFLOW_KEYWORDS =
            createTextAttributesKey("LiteFlowKeywords", DefaultLanguageHighlighterColors.KEYWORD);

    public static final TextAttributesKey LITEFLOW_NODE_REF =
            createTextAttributesKey("LiteFlowNodeRef", DefaultLanguageHighlighterColors.GLOBAL_VARIABLE);

    public static final TextAttributesKey NUMBER =
            createTextAttributesKey("number", DefaultLanguageHighlighterColors.NUMBER);

    public static final TextAttributesKey STRING =
            createTextAttributesKey("string", DefaultLanguageHighlighterColors.STRING);

    public static final TextAttributesKey BOOLEAN =
            createTextAttributesKey("boolean", DefaultLanguageHighlighterColors.KEYWORD);

    public static final TextAttributesKey BAD_CHARACTER =
            createTextAttributesKey("SIMPLE_BAD_CHARACTER", HighlighterColors.BAD_CHARACTER);

    private static final TextAttributesKey[] BAD_CHAR_KEYS = new TextAttributesKey[] { BAD_CHARACTER };
    private static final TextAttributesKey[] BLOCK_COMMENTS_KEYS = new TextAttributesKey[] { BLOCK_COMMENT };
    private static final TextAttributesKey[] LINE_COMMENTS_KEYS = new TextAttributesKey[] { LINE_COMMENT };
    private static final TextAttributesKey[] QLEXPRESS_KEYWORDS_KEYS = new TextAttributesKey[] { QLEXPRESS_KEYWORDS };
    private static final TextAttributesKey[] LITEFLOW_KEYWORDS_KEYS = new TextAttributesKey[] { LITEFLOW_KEYWORDS };
    private static final TextAttributesKey[] STRING_KEYS = new TextAttributesKey[] { STRING };
    private static final TextAttributesKey[] NUMBER_KEYS = new TextAttributesKey[] { NUMBER };
    private static final TextAttributesKey[] BOOLEAN_KEYS = new TextAttributesKey[] { BOOLEAN };
    private static final TextAttributesKey[] EMPTY_KEYS = new TextAttributesKey[0];

    @NotNull
    @Override
    public Lexer getHighlightingLexer() {
        return new LiteFlowLexerAdapter();
    }

    @NotNull
    @Override
    public TextAttributesKey[] getTokenHighlights(IElementType tokenType) {
        if (tokenType.equals(LiteFlowTypes.LITEFLOW_BREAK)
                || tokenType.equals(LiteFlowTypes.LITEFLOW_FOR)
                || tokenType.equals(LiteFlowTypes.LITEFLOW_IF)
                || tokenType.equals(LiteFlowTypes.LITEFLOW_ELSE)
                || tokenType.equals(LiteFlowTypes.LITEFLOW_IN)
                || tokenType.equals(LiteFlowTypes.LITEFLOW_NEW)
                || tokenType.equals(LiteFlowTypes.LITEFLOW_NULL)
                || tokenType.equals(LiteFlowTypes.LITEFLOW_RETURN)
                || tokenType.equals(LiteFlowTypes.LITEFLOW_WHILE)
                || tokenType.equals(LiteFlowTypes.LITEFLOW_CONTINUE)
                || tokenType.equals(LiteFlowTypes.LITEFLOW_FALSE)
                || tokenType.equals(LiteFlowTypes.LITEFLOW_TRUE)
        ) {
            return QLEXPRESS_KEYWORDS_KEYS;
        } else if(tokenType.equals(LiteFlowTypes.LITEFLOW_WHEN)
                || tokenType.equals(LiteFlowTypes.LITEFLOW_THEN)
                || tokenType.equals(LiteFlowTypes.LITEFLOW_FINALLY)
                || tokenType.equals(LiteFlowTypes.LITEFLOW_PRE)
                || tokenType.equals(LiteFlowTypes.LITEFLOW_SWITCH)
                || tokenType.equals(LiteFlowTypes.LITEFLOW_ID)
                || tokenType.equals(LiteFlowTypes.LITEFLOW_IGNOREERROR)
                || tokenType.equals(LiteFlowTypes.LITEFLOW_ANY)
                || tokenType.equals(LiteFlowTypes.LITEFLOW_THREADPOOL)
                || tokenType.equals(LiteFlowTypes.LITEFLOW_TO)
                || tokenType.equals(LiteFlowTypes.LITEFLOW_TAG)
                || tokenType.equals(LiteFlowTypes.LITEFLOW_DATA)
                || tokenType.equals(LiteFlowTypes.LITEFLOW_NODE)
                || tokenType.equals(LiteFlowTypes.LITEFLOW_LCATCH)
                || tokenType.equals(LiteFlowTypes.LITEFLOW_LIF)
                || tokenType.equals(LiteFlowTypes.LITEFLOW_LELIF)
                || tokenType.equals(LiteFlowTypes.LITEFLOW_LELSE)
                || tokenType.equals(LiteFlowTypes.LITEFLOW_LFOR)
                || tokenType.equals(LiteFlowTypes.LITEFLOW_LITERATOR)
                || tokenType.equals(LiteFlowTypes.LITEFLOW_LWHILE)
                || tokenType.equals(LiteFlowTypes.LITEFLOW_DO)
                || tokenType.equals(LiteFlowTypes.LITEFLOW_UNODE)
                || tokenType.equals(LiteFlowTypes.LITEFLOW_UTO)
                || tokenType.equals(LiteFlowTypes.LITEFLOW_LBREAK)
                || tokenType.equals(LiteFlowTypes.LITEFLOW_DEFAULT)
        ) {
            return LITEFLOW_KEYWORDS_KEYS;
        } else if (tokenType.equals(LiteFlowTypes.LITEFLOW_BLOCK_COMMENT)) {
            return BLOCK_COMMENTS_KEYS;        }
        else if (tokenType.equals(LiteFlowTypes.LITEFLOW_LINE_COMMENT)) {
            return LINE_COMMENTS_KEYS;
        } else if (tokenType.equals(LiteFlowTypes.LITEFLOW_NUMBER)) {
            return NUMBER_KEYS;
        } else if (tokenType.equals(LiteFlowTypes.LITEFLOW_BOOLEAN)) {
            return BOOLEAN_KEYS;
        } else if (tokenType.equals(LiteFlowTypes.LITEFLOW_STRING)) {
            return STRING_KEYS;
        } else if (tokenType.equals(TokenType.BAD_CHARACTER)) {
            return BAD_CHAR_KEYS;
        } else {
            return EMPTY_KEYS;
        }
    }


}
