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

public class LiteFlowForStatementImpl extends ASTWrapperPsiElement implements LiteFlowForStatement {

  public LiteFlowForStatementImpl(@NotNull ASTNode node) {
    super(node);
  }

  public void accept(@NotNull LiteFlowVisitor visitor) {
    visitor.visitForStatement(this);
  }

  @Override
  public void accept(@NotNull PsiElementVisitor visitor) {
    if (visitor instanceof LiteFlowVisitor) accept((LiteFlowVisitor)visitor);
    else super.accept(visitor);
  }

  @Override
  @Nullable
  public LiteFlowAllExpress getAllExpress() {
    return findChildByClass(LiteFlowAllExpress.class);
  }

  @Override
  @NotNull
  public List<LiteFlowAssignExpress> getAssignExpressList() {
    return PsiTreeUtil.getChildrenOfTypeAsList(this, LiteFlowAssignExpress.class);
  }

  @Override
  @NotNull
  public LiteFlowCodes getCodes() {
    return findNotNullChildByClass(LiteFlowCodes.class);
  }

  @Override
  @Nullable
  public LiteFlowOpSelfExpress getOpSelfExpress() {
    return findChildByClass(LiteFlowOpSelfExpress.class);
  }

}
