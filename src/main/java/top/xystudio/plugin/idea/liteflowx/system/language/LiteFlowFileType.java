package top.xystudio.plugin.idea.liteflowx.system.language;

import com.intellij.openapi.fileTypes.LanguageFileType;
import com.intellij.openapi.util.NlsContexts;
import com.intellij.openapi.util.NlsSafe;
import icons.LiteFlowIcons;
import org.jetbrains.annotations.NonNls;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import javax.swing.*;

public class LiteFlowFileType extends LanguageFileType {

    public static final LiteFlowFileType INSTANCE = new LiteFlowFileType();

    private LiteFlowFileType(){
        super(LiteFlowLanguage.INSTANCE);
    }

    @Override
    public @NonNls @NotNull String getName() {
        return "LiteFlow File";
    }

    @Override
    public @NlsContexts.Label @NotNull String getDescription() {
        return "LiteFlow expression based on QLExpress syntax";
    }

    @Override
    public @NlsSafe @NotNull String getDefaultExtension() {
        return "elf";
    }

    @Override
    public @Nullable Icon getIcon() {
        return LiteFlowIcons.FILE_ICON;
    }
}
