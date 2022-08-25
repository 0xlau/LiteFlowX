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

public class LiteFlowLiteFlowIf3ExpressImpl extends ASTWrapperPsiElement implements LiteFlowLiteFlowIf3Express {

  public LiteFlowLiteFlowIf3ExpressImpl(@NotNull ASTNode node) {
    super(node);
  }

  public void accept(@NotNull LiteFlowVisitor visitor) {
    visitor.visitLiteFlowIf3Express(this);
  }

  @Override
  public void accept(@NotNull PsiElementVisitor visitor) {
    if (visitor instanceof LiteFlowVisitor) accept((LiteFlowVisitor)visitor);
    else super.accept(visitor);
  }

  @Override
  @NotNull
  public List<LiteFlowLiteFlowAllExpress> getLiteFlowAllExpressList() {
    return PsiTreeUtil.getChildrenOfTypeAsList(this, LiteFlowLiteFlowAllExpress.class);
  }

  @Override
  @Nullable
  public LiteFlowLiteFlowIf3SubExpress getLiteFlowIf3SubExpress() {
    return findChildByClass(LiteFlowLiteFlowIf3SubExpress.class);
  }

  @Override
  @Nullable
  public LiteFlowLiteFlowNodeRef getLiteFlowNodeRef() {
    return findChildByClass(LiteFlowLiteFlowNodeRef.class);
  }

  @Override
  @Nullable
  public LiteFlowLiteFlowTagExpress getLiteFlowTagExpress() {
    return findChildByClass(LiteFlowLiteFlowTagExpress.class);
  }

}
