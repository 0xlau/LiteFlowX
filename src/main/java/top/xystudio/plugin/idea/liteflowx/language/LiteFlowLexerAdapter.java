package top.xystudio.plugin.idea.liteflowx.language;

import com.intellij.lexer.FlexAdapter;
import top.xystudio.plugin.idea.liteflowx.system.language._LiteFlowLexer;

public class LiteFlowLexerAdapter extends FlexAdapter {

    public LiteFlowLexerAdapter(){
        super(new _LiteFlowLexer(null));
    }

}
