package design.encapsulation;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

public class LibraryCatalog  {
    private List<Book> books;

    // TODO: implement all functions with the ArrayList first;
    //  When you are done, add a hash map that maps a title to a book, and change implementations of addBook, checkoutBook, returnBook
    //private HashMap<String, Book> bookMap;

    public LibraryCatalog() {
        books = new ArrayList<>();
    }

    /**
     * Add a book to the ArrayList books
     * @param title title of the book
     * @param author author of the book
     */
    public void addBook(String title, String author) {
        Book book = new Book(title, author);
        books.add(book);
    }

    /**
     * Find the book by title and if it's available, check it out and return true.
     * If the book is not found or not available, return false
     * @param title title of the book
     * @return true if we could check out the book, and false otherwise
     */
    public boolean checkoutBook(String title) {
        // FILL IN CODE
        for (Book book : books) {
            if (book.getTitle().equals(title))
                return book.checkout();
        }
        return false;
    }

    /**
     * Find the book by title (you can add a private method), and if it's currently checked out, return it to the library and return true.
     * If the book is not found or not checked out, return false.
     * @param title title of the book
     * @return true if we could return the book to the library, and false otherwise
     */
    public boolean returnBook(String title) {
        // FILL IN CODE
        for (Book book : books) {
            if (book.getTitle().equals(title))
                return book.returnToLibrary();
        }

        return false;
    }

    /**
     * Print books that are not checked out
     */
    public void printAvailableBooks() {
        // FILL IN CODE
        for (Book book : books) {
            if (!book.isCheckedOut()) {
                System.out.println(book);
            }
        }
    }

    // Method that breaks encapsulation: bad practice
    public List<Book> getBooks1() {
        return books;
    }

    // Better
    public List<Book> getBooks2() {
        //return new ArrayList<>(books);
        return books.stream().map(b -> new Book(b.getTitle(), b.getAuthor())).toList();
    }

    // Better
    public List<Book> getBooks3() {
        return Collections.unmodifiableList(books);
    }

    public Iterator<Book> iterator() {
        return books.iterator();
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (Book b: books) {
            sb.append(b + System.lineSeparator());
        }
        return sb.toString();
    }

}
