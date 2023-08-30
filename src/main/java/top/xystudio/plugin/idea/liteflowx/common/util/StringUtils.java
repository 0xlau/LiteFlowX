package top.xystudio.plugin.idea.liteflowx.common.util;

public class StringUtils {

    /**
     * 首字母小学
     * @param text
     * @return
     */
    public static String lowerFirst(String text){
        char[] chars = text.toCharArray();
        chars[0] = Character.toLowerCase(chars[0]);
        return String.valueOf(chars);
    }

}
