package top.xystudio.plugin.idea.liteflowx.constant;

public class NodeTypeEnum {

    public static final String COMMON = "COMMON(common,普通, false, NodeComponent.class)";

    public static final String SWITCH = "SWITCH(switch, 选择, false, NodeSwitchComponent.class)";

    public static final String IF = "IF(if, 条件, false, NodeIfComponent.class)";

    public static final String FOR = "FOR(for, 循环次数, false, NodeForComponent.class)";

    public static final String WHILE = "WHILE(while, 循环条件, false, NodeWhileComponent.class)";

    public static final String BREAK = "BREAK(break, 循环跳出, false, NodeBreakComponent.class)";

    public static final String SCRIPT = "SCRIPT(script, 脚本, true, ScriptCommonComponent.class)";

    public static final String SWITCH_SCRIPT = "SWITCH_SCRIPT(switch_script, 选择脚本, true, ScriptSwitchComponent.class)";

    public static final String IF_SCRIPT = "IF_SCRIPT(if_script, 条件脚本, true, ScriptIfComponent.class)";

    public static final String FOR_SCRIPT = "FOR_SCRIPT(for_script, 循环次数脚本, true, ScriptForComponent.class)";

    public static final String WHILE_SCRIPT = "WHILE_SCRIPT(while_script, 循环条件脚本, true, ScriptWhileComponent.class)";

    public static final String BREAK_SCRIPT = "BREAK_SCRIPT(break_script, 循环跳出脚本, true, ScriptBreakComponent.class)";


}
