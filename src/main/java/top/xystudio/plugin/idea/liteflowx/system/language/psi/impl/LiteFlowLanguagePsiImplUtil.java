package top.xystudio.plugin.idea.liteflowx.system.language.psi.impl;

import com.intellij.lang.ASTNode;
import top.xystudio.plugin.idea.liteflowx.system.language.psi.LiteFlowAssignExpress;
import top.xystudio.plugin.idea.liteflowx.system.language.psi.LiteFlowTypes;

public class LiteFlowLanguagePsiImplUtil {

    public static String getTypeRef(LiteFlowAssignExpress element) {
        ASTNode keyNode = element.getNode().findChildByType(LiteFlowTypes.LITEFLOW_TYPE_REF);
        if (keyNode != null) {
            // IMPORTANT: Convert embedded escaped spaces to simple spaces
            return keyNode.getText();
        } else {
            return null;
        }
    }

}
