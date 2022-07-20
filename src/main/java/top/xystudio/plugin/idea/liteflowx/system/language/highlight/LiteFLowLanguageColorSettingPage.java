package top.xystudio.plugin.idea.liteflowx.system.language.highlight;

import com.intellij.openapi.editor.colors.TextAttributesKey;
import com.intellij.openapi.fileTypes.SyntaxHighlighter;
import com.intellij.openapi.options.colors.AttributesDescriptor;
import com.intellij.openapi.options.colors.ColorDescriptor;
import com.intellij.openapi.options.colors.ColorSettingsPage;
import icons.LiteFlowIcons;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import javax.swing.*;
import java.util.Map;

public class LiteFLowLanguageColorSettingPage implements ColorSettingsPage {

    private static final AttributesDescriptor[] DESCRIPTORS = new AttributesDescriptor[] {
            
            new AttributesDescriptor("BlockComment", LiteFlowSyntaxHighlighter.BLOCK_COMMENT),
            new AttributesDescriptor("LineComment", LiteFlowSyntaxHighlighter.LINE_COMMENT),

            new AttributesDescriptor("QLExpressKeyWords", LiteFlowSyntaxHighlighter.QLEXPRESS_KEYWORDS),
            new AttributesDescriptor("QLExpressVariables", LiteFlowSyntaxHighlighter.QLEXPRESS_VARIABLES),


            new AttributesDescriptor("String", LiteFlowSyntaxHighlighter.STRING),
            new AttributesDescriptor("Number", LiteFlowSyntaxHighlighter.NUMBER),
            new AttributesDescriptor("Boolean", LiteFlowSyntaxHighlighter.BOOLEAN),

            new AttributesDescriptor("LiteFlowKeyWords", LiteFlowSyntaxHighlighter.LITEFLOW_KEYWORDS),
            new AttributesDescriptor("LiteFlowNodeRef", LiteFlowSyntaxHighlighter.LITEFLOW_NODE_REF),

            new AttributesDescriptor("Bad Value", LiteFlowSyntaxHighlighter.BAD_CHARACTER)

    };


    @Nullable
    @Override
    public Icon getIcon() {
        return LiteFlowIcons.FILE_ICON;
    }

    @NotNull
    @Override
    public SyntaxHighlighter getHighlighter() {
        return new LiteFlowSyntaxHighlighter();
    }

    @NotNull
    @Override
    public String getDemoText() {
        return  "/** 复杂编排 **/\n" +
                "THEN(\n" +
                "    A,\n" +
                "    WHEN(\n" +
                "        THEN(B, C),\n" +
                "        THEN(D, E, F),\n" +
                "        THEN(\n" +
                "            SWITCH(G).to(\n" +
                "            THEN(H, I, WHEN(J, K)).id(\"t1\"),\n" +
                "                THEN(L, M).id(\"t2\")\n" +
                "            ),\n" +
                "            N\n" +
                "        )\n" +
                "    ),\n" +
                "    Z\n" +
                ");\n\n" +
                "/** QLExpress 通用表达式 **/\n" +
                "n = 10;\n" +
                "sum = 0;\n" +
                "for(i = 0; i < n; i++) {\n" +
                "   sum = sum + i;\n" +
                "}\n" +
                "return sum;\n" +
                "\n" +
                "a = 1;\n" +
                "b = 2;\n" +
                "maxnum = a > b ? a : b;\n" +
                "\n" +
                "keys = new ArrayList();\n" +
                "deviceName2Value = new HashMap();\n" +
                "deviceNames = [\"ng\", \"si\", \"umid\", \"ut\", \"mac\", \"imsi\", \"imei\"];\n" +
                "mins = [5, 30];\n" +
                "\n" +
                "keySet = map.keySet();\n" +
                "objArr = keySet.toArray();\n" +
                "for (i = 0; i < objArr.length; i++) {\n" +
                "    key = objArr[i];\n" +
                "    System.out.println(map.get(key));\n" +
                "}"
                ;
    }

    @Nullable
    @Override
    public Map<String, TextAttributesKey> getAdditionalHighlightingTagToDescriptorMap() {
        return null;
    }

    @NotNull
    @Override
    public AttributesDescriptor[] getAttributeDescriptors() {
        return DESCRIPTORS;
    }

    @NotNull
    @Override
    public ColorDescriptor[] getColorDescriptors() {
        return ColorDescriptor.EMPTY_ARRAY;
    }

    @NotNull
    @Override
    public String getDisplayName() {
        return "LiteFlow";
    }


}
