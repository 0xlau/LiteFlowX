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

public class LiteFlowLiteFlowIf2ElifExpressImpl extends ASTWrapperPsiElement implements LiteFlowLiteFlowIf2ElifExpress {

  public LiteFlowLiteFlowIf2ElifExpressImpl(@NotNull ASTNode node) {
    super(node);
  }

  public void accept(@NotNull LiteFlowVisitor visitor) {
    visitor.visitLiteFlowIf2ElifExpress(this);
  }

  @Override
  public void accept(@NotNull PsiElementVisitor visitor) {
    if (visitor instanceof LiteFlowVisitor) accept((LiteFlowVisitor)visitor);
    else super.accept(visitor);
  }

  @Override
  @Nullable
  public LiteFlowLiteFlowAllExpress getLiteFlowAllExpress() {
    return findChildByClass(LiteFlowLiteFlowAllExpress.class);
  }

  @Override
  @Nullable
  public LiteFlowLiteFlowAllLogicExpress getLiteFlowAllLogicExpress() {
    return findChildByClass(LiteFlowLiteFlowAllLogicExpress.class);
  }

  @Override
  @Nullable
  public LiteFlowLiteFlowIf2ElifSubExpress getLiteFlowIf2ElifSubExpress() {
    return findChildByClass(LiteFlowLiteFlowIf2ElifSubExpress.class);
  }

  @Override
  @Nullable
  public LiteFlowLiteFlowNodeRefExpress getLiteFlowNodeRefExpress() {
    return findChildByClass(LiteFlowLiteFlowNodeRefExpress.class);
  }

}
