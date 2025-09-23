package design.patterns.creatingobjects.staticfactory;

import java.util.Optional;
import java.util.Scanner;

public class UsingOptional {

    public static Optional<String> generateUsername() {
        Scanner sc = new Scanner(System.in);
        System.out.println("Please enter your name: ");
        String name = sc.nextLine();
        if (!name.isEmpty() && name.length() > 2) {
            int num = (int)(10 * Math.random());
            return Optional.of(name.toLowerCase().substring(0, 3) + num +"%");
        }
        return Optional.ofNullable(null); // an empty Optional
    }

    public static void main(String[] args) {
        String result;
        Optional<String> username = generateUsername();
        if (username.isPresent()) {
            result = username.get();
            System.out.println("Generated a username: " + result);
        }
        else
            System.out.println("Sorry, invalid input, no username generated");
    }
}
