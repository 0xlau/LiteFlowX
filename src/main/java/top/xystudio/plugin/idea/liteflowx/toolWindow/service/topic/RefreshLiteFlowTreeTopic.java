package top.xystudio.plugin.idea.liteflowx.toolWindow.service.topic;

import com.intellij.util.messages.Topic;

public interface RefreshLiteFlowTreeTopic extends BaseTopic<Void>{

    Topic<RefreshLiteFlowTreeTopic> TOPIC = Topic.create("BaseTopic.RefreshLiteFlowTreeTopic", RefreshLiteFlowTreeTopic.class);

    /**
     * refresh
     */
    void refresh();
}
