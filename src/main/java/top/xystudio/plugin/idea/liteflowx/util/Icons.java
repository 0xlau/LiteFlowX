package top.xystudio.plugin.idea.liteflowx.util;

import com.intellij.openapi.util.IconLoader;

import javax.swing.*;

public interface Icons {

    /** 组件LINEMARKER图标 */
    Icon COMPONENT_LINE_MARKER_ICON = IconLoader.getIcon("/images/component_12x12.png", Icons.class);

    /** 节点LINEMARKER图标 */
    Icon NODE_LINE_MARKER_ICON = IconLoader.getIcon("/images/node_12x12.png", Icons.class);

    /** 链条LINEMARKER图标 */
    Icon CHAIN_LINE_MARKER_ICON = IconLoader.getIcon("/images/chain_12x12.png", Icons.class);

    /** XML文件图标 */
    Icon XML_FILE_ICON = IconLoader.getIcon("/images/xml_16x16.png", Icons.class);

    /** JSON文件图标 */
    Icon JSON_FILE_ICON = IconLoader.getIcon("/images/json_16x16.png", Icons.class);

    /** YML文件图标 */
    Icon YML_FILE_ICON = IconLoader.getIcon("/images/yml_16x16.png", Icons.class);

    /** SLOT类文件图标 */
    Icon SLOT_CLASS_FILE_ICON = IconLoader.getIcon("/images/slot_16x16.png", Icons.class);

    /** COMPONENT类文件图标 */
    Icon COMPONENT_CLASS_FILE_ICON =  IconLoader.getIcon("/images/component_16x16.png", Icons.class);

}
