package aiagent;

import dev.langchain4j.agent.tool.Tool;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class LibraryTools {

    private final LibraryCatalog catalog;

    public LibraryTools(LibraryCatalog catalog) {
        this.catalog = catalog;
    }

    @Tool("List all available books in the library")
    public String listAvailableBooks() {
        System.out.println("[Tool] listAvailableBooks()");

        StringBuilder sb = new StringBuilder();
        catalog.getAvailableBooks().forEach(b -> sb.append(b).append(System.lineSeparator()));
        return sb.isEmpty() ? "No books available." : sb.toString();
    }

    @Tool("Checkout a book by title")
    public String checkoutBook(String title) {
        System.out.println("[Tool] checkoutBook(" + title + ")");
        boolean success = catalog.checkoutBook(title);
        return success ? "Checked out: " + title : "Book not available: " + title;
    }

    @Tool("Search Google Books API for book information given a title")
    public String searchExternalBookInfo(String title) {
        System.out.println("[Tool] GoogleApis/books for (" + title + ")");

        try {
            String urlStr = "https://www.googleapis.com/books/v1/volumes?q="
                    + title.replace(" ", "+");
            URL url = new URL(urlStr);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");

            BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            StringBuilder response = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null) {
                response.append(line);
            }
            reader.close();

            return response.toString(); // raw JSON
        } catch (Exception e) {
            return "Error fetching book info: " + e.getMessage();
        }
    }
}
