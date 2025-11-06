package regex;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MovingConsonants {
    public static void main(String[] args) {
        System.out.println(moveConsonantsFromFrontToEnd("string"));
    }

    public static String moveConsonantsFromFrontToEnd(String word) {
        Pattern p = Pattern.compile("([^aeiou]*)(.*)");
        Matcher m = p.matcher(word);
        if (m.matches()) {
            return m.group(2) + m.group(1);
        }
        System.out.println("No pattern found");
        return word;
    }
}
