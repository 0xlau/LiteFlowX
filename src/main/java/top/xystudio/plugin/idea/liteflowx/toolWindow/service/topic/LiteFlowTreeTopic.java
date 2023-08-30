package top.xystudio.plugin.idea.liteflowx.toolWindow.service.topic;

import com.intellij.util.messages.Topic;
import org.jetbrains.annotations.NotNull;
import top.xystudio.plugin.idea.liteflowx.toolWindow.beans.LiteFlowElement;

import java.util.List;
import java.util.Map;

public interface LiteFlowTreeTopic extends BaseTopic<Map<String, List<LiteFlowElement>>> {

    Topic<LiteFlowTreeTopic> TOPIC = Topic.create("BaseTopic.LiteFlowTreeTopic", LiteFlowTreeTopic.class);

    /**
     * action
     *
     * @param data data
     */
    @Override
    void action(@NotNull Map<String, List<LiteFlowElement>> data);

}
