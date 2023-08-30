package top.xystudio.plugin.idea.liteflowx.common.constant;

public class NodeTypeEnum {

    public static final String COMMON = "COMMON(common, 普通, false, NodeComponent.class)";

    public static final String SWITCH = "SWITCH(switch, 选择, false, NodeSwitchComponent.class)";

    public static final String IF = "IF(if, 条件, false, NodeIfComponent.class)";

    public static final String FOR = "FOR(for, 循环次数, false, NodeForComponent.class)";

    public static final String ITERATOR = "ITERATOR(iterator, 循环迭代, false, NodeIteratorComponent.class)";

    public static final String WHILE = "WHILE(while, 循环条件, false, NodeWhileComponent.class)";

    public static final String BREAK = "BREAK(break, 循环跳出, false, NodeBreakComponent.class)";

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
