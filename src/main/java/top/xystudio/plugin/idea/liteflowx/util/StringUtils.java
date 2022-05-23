package top.xystudio.plugin.idea.liteflowx.util;

public class StringUtils {

    public static String lowerFirst(String text){
        char[] chars = text.toCharArray();
        chars[0] = Character.toLowerCase(chars[0]);
        return String.valueOf(chars);
    }

}
