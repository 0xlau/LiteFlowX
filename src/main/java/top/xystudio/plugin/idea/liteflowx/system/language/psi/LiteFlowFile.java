package top.xystudio.plugin.idea.liteflowx.system.language.psi;

import com.intellij.extapi.psi.PsiFileBase;
import com.intellij.openapi.fileTypes.FileType;
import com.intellij.psi.FileViewProvider;
import org.jetbrains.annotations.NotNull;
import top.xystudio.plugin.idea.liteflowx.system.language.LiteFlowFileType;
import top.xystudio.plugin.idea.liteflowx.system.language.LiteFlowLanguage;

public class LiteFlowFile extends PsiFileBase {

    public LiteFlowFile(@NotNull FileViewProvider viewProvider) {
        super(viewProvider, LiteFlowLanguage.INSTANCE);
    }

    @Override
    public @NotNull FileType getFileType() {
        return LiteFlowFileType.INSTANCE;
    }

    /*asd */
    @Override
    public String toString() {
        return "LiteFlow File";
    }
}
