// This is a generated file. Not intended for manual editing.
package top.xystudio.plugin.idea.liteflowx.system.language.psi;

import java.util.List;
import org.jetbrains.annotations.*;
import com.intellij.psi.PsiElement;

public interface LiteFlowSubAllExpress extends PsiElement {

  @NotNull
  List<LiteFlowAllExpress> getAllExpressList();

  @Nullable
  LiteFlowArrayExpress getArrayExpress();

  @Nullable
  LiteFlowAssignExpress getAssignExpress();

  @Nullable
  LiteFlowExpress getExpress();

  @Nullable
  LiteFlowFnInvokeExpress getFnInvokeExpress();

  @Nullable
  LiteFlowLiteFlowConditionExpress getLiteFlowConditionExpress();

  @Nullable
  LiteFlowNewExpress getNewExpress();

  @NotNull
  List<LiteFlowOp> getOpList();

  @Nullable
  LiteFlowOpSelfExpress getOpSelfExpress();

  @Nullable
  LiteFlowThreeExpress getThreeExpress();

  @Nullable
  LiteFlowUseArrayExpress getUseArrayExpress();

}
