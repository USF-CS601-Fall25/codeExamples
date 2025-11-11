package regex;

import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class PasswordValidation {
    public static boolean isValid(String password) {
        Pattern pattern = Pattern.compile("(?=.*\\d)(?=.*[A-Z])(?=.*[@#$%])");
        Matcher matcher = pattern.matcher(password);
        if (matcher.find())
            return true;
        return false;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter the password: must contain one digit, one uppercase letter, one special character: @#$% ");
        String password = scanner.nextLine();
        System.out.println(isValid(password));
    }
}
