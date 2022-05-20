package top.xystudio.plugin.idea.liteflowx.util;

import top.xystudio.plugin.idea.liteflowx.parse.RegexEntity;

import java.util.ArrayList;
import java.util.List;

public class ParseUtils {

    public static List<RegexEntity> parseExpression(String expression) {
        List<RegexEntity> result = new ArrayList<>();
        String[] condArray = expression.split(",");
        for (String s : condArray){
            String itemExpression = s.trim();
            if (!itemExpression.equals("")) {
                result.add(RegexEntity.parse(itemExpression));
            }
        }
        return result;
    }

}
