// This is a generated file. Not intended for manual editing.
package top.xystudio.plugin.idea.liteflowx.system.language.psi;

import java.util.List;
import org.jetbrains.annotations.*;
import com.intellij.psi.PsiElement;

public interface LiteFlowStatement extends PsiElement {

  @Nullable
  LiteFlowAssignStatement getAssignStatement();

  @Nullable
  LiteFlowBreakStatement getBreakStatement();

  @Nullable
  LiteFlowContinueStatement getContinueStatement();

  @Nullable
  LiteFlowFnInvokeStatement getFnInvokeStatement();

  @Nullable
  LiteFlowForStatement getForStatement();

  @Nullable
  LiteFlowIfStatement getIfStatement();

  @Nullable
  LiteFlowLiteFlowStatement getLiteFlowStatement();

  @Nullable
  LiteFlowNewStatement getNewStatement();

  @Nullable
  LiteFlowOpSelfStatement getOpSelfStatement();

  @Nullable
  LiteFlowReturnStatement getReturnStatement();

  @NotNull
  List<LiteFlowStatement> getStatementList();

  @Nullable
  LiteFlowThreeStatement getThreeStatement();

  @Nullable
  LiteFlowWhileStatement getWhileStatement();

  @Nullable
  PsiElement getBlockComment();

}
