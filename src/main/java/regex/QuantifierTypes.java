package regex;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class QuantifierTypes {
    public static void main(String[] args) {
        // This quantifier is greedy
        Pattern pattern1  = Pattern.compile("a*ardvark");
        Matcher matcher1 = pattern1.matcher("aardvark");
        System.out.println(matcher1.matches());

        // This quantifier is reluctant
        Pattern pattern2  = Pattern.compile("a*?ardvark");
        Matcher matcher2 = pattern2.matcher("aardvark");
        System.out.println(matcher2.matches());

        // This quantifier is possessive
        Pattern pattern3  = Pattern.compile("a*+ardvark");
        Matcher matcher3 = pattern3.matcher("aardvark");
        System.out.println(matcher3.matches());


    }

}
