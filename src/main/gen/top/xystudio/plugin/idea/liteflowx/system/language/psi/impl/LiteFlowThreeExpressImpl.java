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

public class LiteFlowThreeExpressImpl extends ASTWrapperPsiElement implements LiteFlowThreeExpress {

  public LiteFlowThreeExpressImpl(@NotNull ASTNode node) {
    super(node);
  }

  public void accept(@NotNull LiteFlowVisitor visitor) {
    visitor.visitThreeExpress(this);
  }

  @Override
  public void accept(@NotNull PsiElementVisitor visitor) {
    if (visitor instanceof LiteFlowVisitor) accept((LiteFlowVisitor)visitor);
    else super.accept(visitor);
  }

  @Override
  @NotNull
  public List<LiteFlowAllExpress> getAllExpressList() {
    return PsiTreeUtil.getChildrenOfTypeAsList(this, LiteFlowAllExpress.class);
  }

  @Override
  @Nullable
  public LiteFlowExpress getExpress() {
    return findChildByClass(LiteFlowExpress.class);
  }

  @Override
  @Nullable
  public LiteFlowFnInvokeExpress getFnInvokeExpress() {
    return findChildByClass(LiteFlowFnInvokeExpress.class);
  }

}
