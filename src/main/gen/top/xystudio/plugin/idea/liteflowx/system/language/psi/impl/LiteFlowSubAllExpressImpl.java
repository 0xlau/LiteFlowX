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

public class LiteFlowSubAllExpressImpl extends ASTWrapperPsiElement implements LiteFlowSubAllExpress {

  public LiteFlowSubAllExpressImpl(@NotNull ASTNode node) {
    super(node);
  }

  public void accept(@NotNull LiteFlowVisitor visitor) {
    visitor.visitSubAllExpress(this);
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
  public LiteFlowArrayExpress getArrayExpress() {
    return findChildByClass(LiteFlowArrayExpress.class);
  }

  @Override
  @Nullable
  public LiteFlowAssignExpress getAssignExpress() {
    return findChildByClass(LiteFlowAssignExpress.class);
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

  @Override
  @Nullable
  public LiteFlowLiteFlowConditionExpress getLiteFlowConditionExpress() {
    return findChildByClass(LiteFlowLiteFlowConditionExpress.class);
  }

  @Override
  @Nullable
  public LiteFlowLiteFlowNodeStringExpress getLiteFlowNodeStringExpress() {
    return findChildByClass(LiteFlowLiteFlowNodeStringExpress.class);
  }

  @Override
  @Nullable
  public LiteFlowNewExpress getNewExpress() {
    return findChildByClass(LiteFlowNewExpress.class);
  }

  @Override
  @NotNull
  public List<LiteFlowOp> getOpList() {
    return PsiTreeUtil.getChildrenOfTypeAsList(this, LiteFlowOp.class);
  }

  @Override
  @Nullable
  public LiteFlowOpSelfExpress getOpSelfExpress() {
    return findChildByClass(LiteFlowOpSelfExpress.class);
  }

  @Override
  @Nullable
  public LiteFlowThreeExpress getThreeExpress() {
    return findChildByClass(LiteFlowThreeExpress.class);
  }

  @Override
  @Nullable
  public LiteFlowUseArrayExpress getUseArrayExpress() {
    return findChildByClass(LiteFlowUseArrayExpress.class);
  }

}
