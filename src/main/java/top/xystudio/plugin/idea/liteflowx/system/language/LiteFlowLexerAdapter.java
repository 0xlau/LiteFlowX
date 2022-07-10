package top.xystudio.plugin.idea.liteflowx.system.language;

import com.intellij.lexer.FlexAdapter;

public class LiteFlowLexerAdapter extends FlexAdapter {

    public LiteFlowLexerAdapter(){
        super(new _LiteFlowLexer(null));
    }

}
