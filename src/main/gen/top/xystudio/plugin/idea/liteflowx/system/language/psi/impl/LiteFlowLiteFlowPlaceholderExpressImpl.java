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

public class LiteFlowLiteFlowPlaceholderExpressImpl extends ASTWrapperPsiElement implements LiteFlowLiteFlowPlaceholderExpress {

  public LiteFlowLiteFlowPlaceholderExpressImpl(@NotNull ASTNode node) {
    super(node);
  }

  public void accept(@NotNull LiteFlowVisitor visitor) {
    visitor.visitLiteFlowPlaceholderExpress(this);
  }

  @Override
  public void accept(@NotNull PsiElementVisitor visitor) {
    if (visitor instanceof LiteFlowVisitor) accept((LiteFlowVisitor)visitor);
    else super.accept(visitor);
  }

  @Override
  @NotNull
  public LiteFlowLiteFlowConditionExpress getLiteFlowConditionExpress() {
    return findNotNullChildByClass(LiteFlowLiteFlowConditionExpress.class);
  }

  @Override
  @Nullable
  public LiteFlowRefExpress getRefExpress() {
    return findChildByClass(LiteFlowRefExpress.class);
  }

  @Override
  @Nullable
  public PsiElement getNumber() {
    return findChildByType(LITEFLOW_NUMBER);
  }

}
