package regex;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class NonCapturingGroupConsumesCharacters {
    public static void main(String[] args) {
        Pattern p = Pattern.compile("(\\w+)(?:\\s+)(\\d+)");
        //(?:\s+) does not store the matched substring
        String text = "Hao 2025";
        Matcher m = p.matcher(text);

        while (m.find()) {
            System.out.println("Name: " + m.group(1));
            System.out.println("Graduated: " + m.group(2));
        }
    }
}
