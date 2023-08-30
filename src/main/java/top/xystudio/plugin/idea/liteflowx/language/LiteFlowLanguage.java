package top.xystudio.plugin.idea.liteflowx.language;

import com.intellij.lang.Language;

public class LiteFlowLanguage extends Language {

    public static final LiteFlowLanguage INSTANCE = new LiteFlowLanguage();

    private LiteFlowLanguage(){
        super("LiteFlow");
    }

}
