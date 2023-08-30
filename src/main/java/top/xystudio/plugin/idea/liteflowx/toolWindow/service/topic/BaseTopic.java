package top.xystudio.plugin.idea.liteflowx.toolWindow.service.topic;

public interface BaseTopic<T> {

    /**
     * action
     *
     * @param data data
     */
    default void action(T data) {
        // default
    }

}
