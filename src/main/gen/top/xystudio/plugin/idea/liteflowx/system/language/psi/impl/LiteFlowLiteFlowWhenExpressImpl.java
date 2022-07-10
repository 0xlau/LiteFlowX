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

public class LiteFlowLiteFlowWhenExpressImpl extends ASTWrapperPsiElement implements LiteFlowLiteFlowWhenExpress {

  public LiteFlowLiteFlowWhenExpressImpl(@NotNull ASTNode node) {
    super(node);
  }

  public void accept(@NotNull LiteFlowVisitor visitor) {
    visitor.visitLiteFlowWhenExpress(this);
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
  @NotNull
  public List<LiteFlowLiteFlowAnyExpress> getLiteFlowAnyExpressList() {
    return PsiTreeUtil.getChildrenOfTypeAsList(this, LiteFlowLiteFlowAnyExpress.class);
  }

  @Override
  @NotNull
  public List<LiteFlowLiteFlowIdExpress> getLiteFlowIdExpressList() {
    return PsiTreeUtil.getChildrenOfTypeAsList(this, LiteFlowLiteFlowIdExpress.class);
  }

  @Override
  @NotNull
  public List<LiteFlowLiteFlowIgnoreErrorExpress> getLiteFlowIgnoreErrorExpressList() {
    return PsiTreeUtil.getChildrenOfTypeAsList(this, LiteFlowLiteFlowIgnoreErrorExpress.class);
  }

  @Override
  @NotNull
  public List<LiteFlowLiteFlowThreadPoolExpress> getLiteFlowThreadPoolExpressList() {
    return PsiTreeUtil.getChildrenOfTypeAsList(this, LiteFlowLiteFlowThreadPoolExpress.class);
  }

}
