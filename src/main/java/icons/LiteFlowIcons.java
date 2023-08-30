package icons;

import com.intellij.openapi.util.IconLoader;

import javax.swing.*;

public interface LiteFlowIcons {

    /** CHAIN 图标 */
    Icon CHAIN_ICON = IconLoader.getIcon("/images/chain.svg", LiteFlowIcons.class);

    /** 多组件 图标 */
    Icon MULTI_COMPONENT_ICON =  IconLoader.getIcon("/images/multi.svg", LiteFlowIcons.class);

    /** COMPONENT类文件图标 */
    Icon COMMON_COMPONENT_ICON =  IconLoader.getIcon("/images/nodeComponent/common.svg", LiteFlowIcons.class);

    Icon IF_COMPONENT_ICON =  IconLoader.getIcon("/images/nodeComponent/if.svg", LiteFlowIcons.class);

    Icon SW_COMPONENT_ICON =  IconLoader.getIcon("/images/nodeComponent/switch.svg", LiteFlowIcons.class);

    Icon FOR_COMPONENT_ICON =  IconLoader.getIcon("/images/nodeComponent/for.svg", LiteFlowIcons.class);


    Icon ITERATOR_COMPONENT_ICON =  IconLoader.getIcon("/images/nodeComponent/iterator.svg", LiteFlowIcons.class);

    Icon WHI_COMPONENT_ICON =  IconLoader.getIcon("/images/nodeComponent/while.svg", LiteFlowIcons.class);

    Icon BRK_COMPONENT_ICON =  IconLoader.getIcon("/images/nodeComponent/break.svg", LiteFlowIcons.class);

    /** SCRIPT类文件图标 */
    Icon COMMON_SCRIPT_ICON = IconLoader.getIcon("/images/scriptComponent/script_common.svg", LiteFlowIcons.class);

    Icon IF_SCRIPT_ICON =  IconLoader.getIcon("/images/scriptComponent/script_if.svg", LiteFlowIcons.class);

    Icon SW_SCRIPT_ICON =  IconLoader.getIcon("/images/scriptComponent/script_switch.svg", LiteFlowIcons.class);

    Icon FOR_SCRIPT_ICON =  IconLoader.getIcon("/images/scriptComponent/script_for.svg", LiteFlowIcons.class);

    Icon WHI_SCRIPT_ICON =  IconLoader.getIcon("/images/scriptComponent/script_while.svg", LiteFlowIcons.class);

    Icon BRK_SCRIPT_ICON =  IconLoader.getIcon("/images/scriptComponent/script_break.svg", LiteFlowIcons.class);

    /** TOOLWINDOW图标 */
    Icon TOOL_WINDOW_ICON =  IconLoader.getIcon("/images/toolWindow/toolboxIcon.svg", LiteFlowIcons.class);

    /** LITEFLOWX 图标 */
    Icon LITEFLOWX_ICON =  IconLoader.getIcon("/images/liteflowx.svg", LiteFlowIcons.class);

    /** XML 文件图标 */
    Icon XML_ICON = LITEFLOWX_ICON;



}
