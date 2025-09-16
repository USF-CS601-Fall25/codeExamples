package aiagent;

import dev.langchain4j.agent.tool.Tool;

public class LibraryTools {

    private final LibraryCatalog catalog;

    public LibraryTools(LibraryCatalog catalog) {
        this.catalog = catalog;
    }

    @Tool("List all available books in the library")
    public String listAvailableBooks() {
        StringBuilder sb = new StringBuilder();
        catalog.getAvailableBooks().forEach(b -> sb.append(b).append(System.lineSeparator()));
        return sb.isEmpty() ? "No books available." : sb.toString();
    }

    @Tool("Checkout a book by title")
    public String checkoutBook(String title) {
        boolean success = catalog.checkoutBook(title);
        return success ? "Checked out: " + title : "Book not available: " + title;
    }
}
