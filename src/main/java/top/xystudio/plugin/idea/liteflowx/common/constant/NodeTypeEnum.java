package top.xystudio.plugin.idea.liteflowx.common.constant;

public class NodeTypeEnum {

    public static final String COMMON = "com.yomahub.liteflow.enums.NodeTypeEnum.COMMON";

    public static final String SWITCH = "com.yomahub.liteflow.enums.NodeTypeEnum.SWITCH";

    public static final String IF = "com.yomahub.liteflow.enums.NodeTypeEnum.IF";

    public static final String FOR = "com.yomahub.liteflow.enums.NodeTypeEnum.FOR";

    public static final String ITERATOR = "com.yomahub.liteflow.enums.NodeTypeEnum.ITERATOR";

    public static final String WHILE = "com.yomahub.liteflow.enums.NodeTypeEnum.WHILE";

    public static final String BREAK = "com.yomahub.liteflow.enums.NodeTypeEnum.BREAK";

    public static final String[] StandardNodeType = {COMMON, SWITCH, IF, FOR, ITERATOR, WHILE, BREAK};

    public static boolean isStandardNodeType(String nodeType){
        for (String snt : StandardNodeType) {
            if (nodeType.contains(snt)){
                return true;
            }
        }
        return false;
    }

    public static final String SCRIPT = "script";

    public static final String SWITCH_SCRIPT = "switch_script";

    public static final String IF_SCRIPT = "if_script";

    public static final String FOR_SCRIPT = "for_script";

    public static final String WHILE_SCRIPT = "while_script";

    public static final String ITERATOR_SCRIPT = "iterator_script";

    public static final String BREAK_SCRIPT = "break_script";
    public static final String[] ScriptNodeType = {SWITCH_SCRIPT, IF_SCRIPT, FOR_SCRIPT, WHILE_SCRIPT, BREAK_SCRIPT, ITERATOR_SCRIPT, SCRIPT};
}
