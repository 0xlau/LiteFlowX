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

public class LiteFlowLiteFlowConditionExpressImpl extends ASTWrapperPsiElement implements LiteFlowLiteFlowConditionExpress {

  public LiteFlowLiteFlowConditionExpressImpl(@NotNull ASTNode node) {
    super(node);
  }

  public void accept(@NotNull LiteFlowVisitor visitor) {
    visitor.visitLiteFlowConditionExpress(this);
  }

  @Override
  public void accept(@NotNull PsiElementVisitor visitor) {
    if (visitor instanceof LiteFlowVisitor) accept((LiteFlowVisitor)visitor);
    else super.accept(visitor);
  }

  @Override
  @Nullable
  public LiteFlowLiteFlowCatchExpress getLiteFlowCatchExpress() {
    return findChildByClass(LiteFlowLiteFlowCatchExpress.class);
  }

  @Override
  @Nullable
  public LiteFlowLiteFlowFinallyExpress getLiteFlowFinallyExpress() {
    return findChildByClass(LiteFlowLiteFlowFinallyExpress.class);
  }

  @Override
  @Nullable
  public LiteFlowLiteFlowForExpress getLiteFlowForExpress() {
    return findChildByClass(LiteFlowLiteFlowForExpress.class);
  }

  @Override
  @NotNull
  public List<LiteFlowLiteFlowIdExpress> getLiteFlowIdExpressList() {
    return PsiTreeUtil.getChildrenOfTypeAsList(this, LiteFlowLiteFlowIdExpress.class);
  }

  @Override
  @Nullable
  public LiteFlowLiteFlowIf2Express getLiteFlowIf2Express() {
    return findChildByClass(LiteFlowLiteFlowIf2Express.class);
  }

  @Override
  @Nullable
  public LiteFlowLiteFlowIf3Express getLiteFlowIf3Express() {
    return findChildByClass(LiteFlowLiteFlowIf3Express.class);
  }

  @Override
  @Nullable
  public LiteFlowLiteFlowIteratorExpress getLiteFlowIteratorExpress() {
    return findChildByClass(LiteFlowLiteFlowIteratorExpress.class);
  }

  @Override
  @NotNull
  public List<LiteFlowLiteFlowMaxWaitMilliseconds> getLiteFlowMaxWaitMillisecondsList() {
    return PsiTreeUtil.getChildrenOfTypeAsList(this, LiteFlowLiteFlowMaxWaitMilliseconds.class);
  }

  @Override
  @NotNull
  public List<LiteFlowLiteFlowMaxWaitSeconds> getLiteFlowMaxWaitSecondsList() {
    return PsiTreeUtil.getChildrenOfTypeAsList(this, LiteFlowLiteFlowMaxWaitSeconds.class);
  }

  @Override
  @Nullable
  public LiteFlowLiteFlowParExpress getLiteFlowParExpress() {
    return findChildByClass(LiteFlowLiteFlowParExpress.class);
  }

  @Override
  @Nullable
  public LiteFlowLiteFlowPreExpress getLiteFlowPreExpress() {
    return findChildByClass(LiteFlowLiteFlowPreExpress.class);
  }

  @Override
  @NotNull
  public List<LiteFlowLiteFlowRetryExpress> getLiteFlowRetryExpressList() {
    return PsiTreeUtil.getChildrenOfTypeAsList(this, LiteFlowLiteFlowRetryExpress.class);
  }

  @Override
  @Nullable
  public LiteFlowLiteFlowSerExpress getLiteFlowSerExpress() {
    return findChildByClass(LiteFlowLiteFlowSerExpress.class);
  }

  @Override
  @Nullable
  public LiteFlowLiteFlowSwitchExpress getLiteFlowSwitchExpress() {
    return findChildByClass(LiteFlowLiteFlowSwitchExpress.class);
  }

  @Override
  @NotNull
  public List<LiteFlowLiteFlowTagExpress> getLiteFlowTagExpressList() {
    return PsiTreeUtil.getChildrenOfTypeAsList(this, LiteFlowLiteFlowTagExpress.class);
  }

  @Override
  @Nullable
  public LiteFlowLiteFlowThenExpress getLiteFlowThenExpress() {
    return findChildByClass(LiteFlowLiteFlowThenExpress.class);
  }

  @Override
  @Nullable
  public LiteFlowLiteFlowWhenExpress getLiteFlowWhenExpress() {
    return findChildByClass(LiteFlowLiteFlowWhenExpress.class);
  }

  @Override
  @Nullable
  public LiteFlowLiteFlowWhileExpress getLiteFlowWhileExpress() {
    return findChildByClass(LiteFlowLiteFlowWhileExpress.class);
  }

}
