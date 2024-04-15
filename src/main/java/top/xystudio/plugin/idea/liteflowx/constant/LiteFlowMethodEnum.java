package top.xystudio.plugin.idea.liteflowx.constant;

public class LiteFlowMethodEnum {

    public static final String PROCESS = "PROCESS";

    public static final String PROCESS_SWITCH = "PROCESS_SWITCH";

    public static final String PROCESS_IF = "PROCESS_IF";

    public static final String PROCESS_FOR = "PROCESS_FOR";

    public static final String PROCESS_ITERATOR = "PROCESS_ITERATOR";

    public static final String PROCESS_WHILE = "PROCESS_WHILE";

    public static final String PROCESS_BREAK = "PROCESS_BREAK";

    public static final String PROCESS_BOOLEAN = "PROCESS_BOOLEAN";

    public static final String IS_ACCESS = "IS_ACCESS";

    public static final String IS_END = "IS_END";

    public static final String IS_CONTINUE_ON_ERROR = "IS_CONTINUE_ON_ERROR";

    public static final String GET_NODE_EXECUTOR_CLASS = "GET_NODE_EXECUTOR_CLASS";

    public static final String ON_SUCCESS = "ON_SUCCESS";

    public static final String ON_ERROR = "ON_ERROR";

    public static final String BEFORE_PROCESS = "BEFORE_PROCESS";

    public static final String AFTER_PROCESS = "AFTER_PROCESS";

    public static final String[] NECESSARY_PROCESS = new String[]{
            PROCESS,
            PROCESS_IF,
            PROCESS_BREAK,
            PROCESS_SWITCH,
            PROCESS_FOR,
            PROCESS_ITERATOR,
            PROCESS_WHILE,
            PROCESS_BOOLEAN
    };

}
