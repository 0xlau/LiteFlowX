// This is a generated file. Not intended for manual editing.
package top.xystudio.plugin.idea.liteflowx.system.language.psi;

import java.util.List;
import org.jetbrains.annotations.*;
import com.intellij.psi.PsiElement;

public interface LiteFlowForStatement extends PsiElement {

  @Nullable
  LiteFlowAllExpress getAllExpress();

  @NotNull
  List<LiteFlowAssignExpress> getAssignExpressList();

  @NotNull
  LiteFlowCodes getCodes();

  @Nullable
  LiteFlowOpSelfExpress getOpSelfExpress();

}
