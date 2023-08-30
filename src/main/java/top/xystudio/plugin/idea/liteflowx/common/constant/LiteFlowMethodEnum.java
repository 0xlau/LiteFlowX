package top.xystudio.plugin.idea.liteflowx.common.constant;

public class LiteFlowMethodEnum {

    public static final String PROCESS = "PROCESS(process, true)";

    public static final String PROCESS_SWITCH = "PROCESS_SWITCH(processSwitch, true)";

    public static final String PROCESS_IF = "PROCESS_IF(processIf, true)";

    public static final String PROCESS_FOR = "PROCESS_FOR(processFor, true)";

    public static final String PROCESS_ITERATOR = "PROCESS_ITERATOR(processIterator, true)";

    public static final String PROCESS_WHILE = "PROCESS_WHILE(processWhile, true)";

    public static final String PROCESS_BREAK = "PROCESS_BREAK(processBreak, true)";

    public static final String IS_ACCESS = "IS_ACCESS(isAccess, false)";

    public static final String IS_END = "IS_END(isEnd, false)";

    public static final String IS_CONTINUE_ON_ERROR = "IS_CONTINUE_ON_ERROR(isContinueOnError, false)";

    public static final String GET_NODE_EXECUTOR_CLASS = "GET_NODE_EXECUTOR_CLASS(getNodeExecutorClass, false)";

    public static final String ON_SUCCESS = "ON_SUCCESS(onSuccess, false)";

    public static final String ON_ERROR = "ON_ERROR(onError, false)";

    public static final String BEFORE_PROCESS = "BEFORE_PROCESS(beforeProcess, false)";

    public static final String AFTER_PROCESS = "AFTER_PROCESS(afterProcess, false)";

    public static final String GET_DISPLAY_NAME = "GET_DISPLAY_NAME(getDisplayName, false)";

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
