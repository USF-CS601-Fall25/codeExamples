package design.encapsulation;

import java.util.Scanner;

public class UserInterface {
    private LibraryCatalog catalog;

    public UserInterface(LibraryCatalog catalog) {
        this.catalog = catalog;
    }

    public void interact() {
        // Allow the user to check out a book, return a book, see all available books, or exit the program.
        Scanner sc = new Scanner(System.in);
        String userResponse = " ";
        while (!userResponse.equals("4")) {
            System.out.println("""
                1. Check out a book
                2. Return a book
                3. Print the list of available books
                4. Exit the program
                """);
            String userInput = sc.nextLine();
            String title  = "";
            switch (userInput) {
                case "1" -> {
                    System.out.println("You would like check out a book. Please enter the title: ");
                    title = sc.nextLine();
                    if (catalog.checkoutBook(title)) {
                        System.out.println("Successfully checked out.");
                    } else {
                        System.out.println("Could not check out the book");
                    }
                }
                case "2" -> {
                    System.out.println("You would like to return a book. Please enter the title: ");
                    title = sc.nextLine();
                    if (catalog.returnBook(title)) {
                        System.out.println("Successfully returned.");
                    } else {
                        System.out.println("Could not return the book");
                    }
                }
                case "3" -> {
                    System.out.println("Show available books");
                    catalog.printAvailableBooks();
                }
                case "4" -> {
                    System.out.println("Exit the program");
                    return;
                }
                default -> System.out.println("You did not enter 1-4. Try again. ");
            }
        }
    }
}
