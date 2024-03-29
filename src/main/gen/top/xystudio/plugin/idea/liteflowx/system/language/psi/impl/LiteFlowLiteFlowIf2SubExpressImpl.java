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

public class LiteFlowLiteFlowIf2SubExpressImpl extends ASTWrapperPsiElement implements LiteFlowLiteFlowIf2SubExpress {

  public LiteFlowLiteFlowIf2SubExpressImpl(@NotNull ASTNode node) {
    super(node);
  }

  public void accept(@NotNull LiteFlowVisitor visitor) {
    visitor.visitLiteFlowIf2SubExpress(this);
  }

  @Override
  public void accept(@NotNull PsiElementVisitor visitor) {
    if (visitor instanceof LiteFlowVisitor) accept((LiteFlowVisitor)visitor);
    else super.accept(visitor);
  }

  @Override
  @Nullable
  public LiteFlowLiteFlowIf2ElifExpress getLiteFlowIf2ElifExpress() {
    return findChildByClass(LiteFlowLiteFlowIf2ElifExpress.class);
  }

  @Override
  @Nullable
  public LiteFlowLiteFlowIf2ElseExpress getLiteFlowIf2ElseExpress() {
    return findChildByClass(LiteFlowLiteFlowIf2ElseExpress.class);
  }

}
