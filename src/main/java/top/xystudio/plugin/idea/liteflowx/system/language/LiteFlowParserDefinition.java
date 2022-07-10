package top.xystudio.plugin.idea.liteflowx.system.language;

import com.intellij.lang.ASTNode;
import com.intellij.lang.ParserDefinition;
import com.intellij.lang.PsiParser;
import com.intellij.lexer.Lexer;
import com.intellij.openapi.project.Project;
import com.intellij.psi.FileViewProvider;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiFile;
import com.intellij.psi.tree.IFileElementType;
import com.intellij.psi.tree.TokenSet;
import org.jetbrains.annotations.NotNull;
import top.xystudio.plugin.idea.liteflowx.system.language.parse.LiteFlowParser;
import top.xystudio.plugin.idea.liteflowx.system.language.psi.LiteFlowFile;
import top.xystudio.plugin.idea.liteflowx.system.language.psi.LiteFlowTypes;

public class LiteFlowParserDefinition implements ParserDefinition {

    public static final IFileElementType FILE = new IFileElementType(LiteFlowLanguage.INSTANCE);

    /**
     * 词法分析器
     *
     * @param project
     * @return
     */
    @NotNull
    @Override
    public Lexer createLexer(Project project) {
        return new LiteFlowLexerAdapter();
    }

    /**
     * 语法分析器
     *
     * @param project
     * @return
     */
    @NotNull
    @Override
    public PsiParser createParser(final Project project) {
        return new LiteFlowParser();
    }

    /**
     * 注释
     *
     * @return
     */
    @Override
    public @NotNull TokenSet getCommentTokens() {
        return TokenSet.EMPTY;
    }

    @NotNull
    @Override
    public TokenSet getStringLiteralElements() {
        return TokenSet.EMPTY;
    }

    @Override
    public IFileElementType getFileNodeType() {
        return FILE;
    }

    @Override
    public PsiFile createFile(FileViewProvider viewProvider) {
        return new LiteFlowFile(viewProvider);
    }

    @Override
    public SpaceRequirements spaceExistenceTypeBetweenTokens(ASTNode left, ASTNode right) {
        return SpaceRequirements.MAY;
    }

    @NotNull
    @Override
    public PsiElement createElement(ASTNode node) {
        return LiteFlowTypes.Factory.createElement(node);
    }

}
