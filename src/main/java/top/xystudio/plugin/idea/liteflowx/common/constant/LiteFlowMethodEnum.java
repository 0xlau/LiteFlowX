package top.xystudio.plugin.idea.liteflowx.common.constant;

public class LiteFlowMethodEnum {

    public static final String PROCESS = "com.yomahub.liteflow.enums.LiteFlowMethodEnum.PROCESS";

    public static final String PROCESS_SWITCH = "com.yomahub.liteflow.enums.LiteFlowMethodEnum.PROCESS_SWITCH";

    public static final String PROCESS_IF = "com.yomahub.liteflow.enums.LiteFlowMethodEnum.PROCESS_IF";

    public static final String PROCESS_FOR = "com.yomahub.liteflow.enums.LiteFlowMethodEnum.PROCESS_FOR";

    public static final String PROCESS_ITERATOR = "com.yomahub.liteflow.enums.LiteFlowMethodEnum.PROCESS_ITERATOR";

    public static final String PROCESS_WHILE = "com.yomahub.liteflow.enums.LiteFlowMethodEnum.PROCESS_WHILE";

    public static final String PROCESS_BREAK = "com.yomahub.liteflow.enums.LiteFlowMethodEnum.PROCESS_BREAK";

    public static final String IS_ACCESS = "com.yomahub.liteflow.enums.LiteFlowMethodEnum.IS_ACCESS";

    public static final String IS_END = "com.yomahub.liteflow.enums.LiteFlowMethodEnum.IS_END";

    public static final String IS_CONTINUE_ON_ERROR = "com.yomahub.liteflow.enums.LiteFlowMethodEnum.IS_CONTINUE_ON_ERROR";

    public static final String GET_NODE_EXECUTOR_CLASS = "com.yomahub.liteflow.enums.LiteFlowMethodEnum.GET_NODE_EXECUTOR_CLASS";

    public static final String ON_SUCCESS = "com.yomahub.liteflow.enums.LiteFlowMethodEnum.ON_SUCCESS";

    public static final String ON_ERROR = "com.yomahub.liteflow.enums.LiteFlowMethodEnum.ON_ERROR";

    public static final String BEFORE_PROCESS = "com.yomahub.liteflow.enums.LiteFlowMethodEnum.BEFORE_PROCESS";

    public static final String AFTER_PROCESS = "com.yomahub.liteflow.enums.LiteFlowMethodEnum.AFTER_PROCESS";

    public static final String GET_DISPLAY_NAME = "com.yomahub.liteflow.enums.LiteFlowMethodEnum.GET_DISPLAY_NAME";

    public static final String[] NECESSARY_PROCESS = new String[]{
            PROCESS,
            PROCESS_IF,
            PROCESS_BREAK,
            PROCESS_SWITCH,
            PROCESS_FOR,
            PROCESS_ITERATOR,
            PROCESS_WHILE
    };

    public static boolean isNecessaryProcess(String methodEnum){
        for (String necessaryProcess : NECESSARY_PROCESS) {
            if (methodEnum.contains(necessaryProcess)){
                return true;
            }
        }
        return false;
    }

}
