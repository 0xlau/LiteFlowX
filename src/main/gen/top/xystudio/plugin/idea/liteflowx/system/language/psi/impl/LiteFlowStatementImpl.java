// This is a generated file. Not intended for manual editing.
package top.xystudio.plugin.idea.liteflowx.system.language.psi.impl;

import java.util.List;
import org.jetbrains.annotations.*;
import com.intellij.lang.ASTNode;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiElementVisitor;
import com.intellij.psi.util.PsiTreeUtil;
import static top.xystudio.plugin.idea.liteflowx.system.language.psi.LiteFlowTypes.*;
import com.intellij.extapi.psi.ASTWrapperPsiElement;
import top.xystudio.plugin.idea.liteflowx.system.language.psi.*;

public class LiteFlowStatementImpl extends ASTWrapperPsiElement implements LiteFlowStatement {

  public LiteFlowStatementImpl(@NotNull ASTNode node) {
    super(node);
  }

  public void accept(@NotNull LiteFlowVisitor visitor) {
    visitor.visitStatement(this);
  }

  @Override
  public void accept(@NotNull PsiElementVisitor visitor) {
    if (visitor instanceof LiteFlowVisitor) accept((LiteFlowVisitor)visitor);
    else super.accept(visitor);
  }

  @Override
  @Nullable
  public LiteFlowAssignStatement getAssignStatement() {
    return findChildByClass(LiteFlowAssignStatement.class);
  }

  @Override
  @Nullable
  public LiteFlowBreakStatement getBreakStatement() {
    return findChildByClass(LiteFlowBreakStatement.class);
  }

  @Override
  @Nullable
  public LiteFlowContinueStatement getContinueStatement() {
    return findChildByClass(LiteFlowContinueStatement.class);
  }

  @Override
  @Nullable
  public LiteFlowFnInvokeStatement getFnInvokeStatement() {
    return findChildByClass(LiteFlowFnInvokeStatement.class);
  }

  @Override
  @Nullable
  public LiteFlowForStatement getForStatement() {
    return findChildByClass(LiteFlowForStatement.class);
  }

  @Override
  @Nullable
  public LiteFlowIfStatement getIfStatement() {
    return findChildByClass(LiteFlowIfStatement.class);
  }

  @Override
  @Nullable
  public LiteFlowLiteFlowAllLogicStatement getLiteFlowAllLogicStatement() {
    return findChildByClass(LiteFlowLiteFlowAllLogicStatement.class);
  }

  @Override
  @Nullable
  public LiteFlowLiteFlowPlaceHolderStatement getLiteFlowPlaceHolderStatement() {
    return findChildByClass(LiteFlowLiteFlowPlaceHolderStatement.class);
  }

  @Override
  @Nullable
  public LiteFlowLiteFlowStatement getLiteFlowStatement() {
    return findChildByClass(LiteFlowLiteFlowStatement.class);
  }

  @Override
  @Nullable
  public LiteFlowNewStatement getNewStatement() {
    return findChildByClass(LiteFlowNewStatement.class);
  }

  @Override
  @Nullable
  public LiteFlowOpSelfStatement getOpSelfStatement() {
    return findChildByClass(LiteFlowOpSelfStatement.class);
  }

  @Override
  @Nullable
  public LiteFlowReturnStatement getReturnStatement() {
    return findChildByClass(LiteFlowReturnStatement.class);
  }

  @Override
  @NotNull
  public List<LiteFlowStatement> getStatementList() {
    return PsiTreeUtil.getChildrenOfTypeAsList(this, LiteFlowStatement.class);
  }

  @Override
  @Nullable
  public LiteFlowThreeStatement getThreeStatement() {
    return findChildByClass(LiteFlowThreeStatement.class);
  }

  @Override
  @Nullable
  public LiteFlowWhileStatement getWhileStatement() {
    return findChildByClass(LiteFlowWhileStatement.class);
  }

  @Override
  @Nullable
  public PsiElement getBlockComment() {
    return findChildByType(LITEFLOW_BLOCK_COMMENT);
  }

  @Override
  @Nullable
  public PsiElement getLineComment() {
    return findChildByType(LITEFLOW_LINE_COMMENT);
  }

}
