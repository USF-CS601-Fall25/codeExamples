package sockets.movieserver;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

// Lab 10/31 solution (problem 2).
public class MovieServer {
    public static int PORT = 6500;
    private List<Movie> movies;

    public MovieServer() {
        movies = new ArrayList<>();
    }

    public void addMovie(Movie m) {
        movies.add(m);
    }

    public JsonObject serialize(String searchParameter) {
        JsonObject obj = new JsonObject();
        JsonArray jsonArr = new JsonArray();
        int count = 0;
        for (Movie movie: movies) {
            if (movie.getName().contains(searchParameter)) {
                JsonObject movieObj = movie.serializeMovie();
                jsonArr.add(movieObj);
                count++;
            }
        }
        if (count > 0)
            obj.add("movies", jsonArr);
        return obj;
    }

    public void runServer() {
        ServerSocket welcomingSocket = null;
        Socket connectionSocket = null;
        try { // the server responds to 5 requests before shutting down
            welcomingSocket = new ServerSocket(PORT);
            int count = 0;
            while (count < 5) {
                System.out.println("Server: Waiting for connection...");
                connectionSocket = welcomingSocket.accept();
                System.out.println("Server: Client connected.");
                BufferedReader br = new BufferedReader(new InputStreamReader(connectionSocket.getInputStream()));
                PrintWriter pr = new PrintWriter(connectionSocket.getOutputStream(), true);
                String line = br.readLine();
                System.out.println(line);
                if (line != null && line.contains("GET") && line.contains("/movies")) {
                    // extract the search parameter (name of the movie)
                    // GET /movies?search=name HTTP/1.1
                    String[] words = line.split(" ");
                    if (words.length == 3) {
                        String[] queryParts = words[1].split("\\?");
                        System.out.println("Query parts " + Arrays.toString(queryParts));
                        if (queryParts.length == 1) {
                            // Just /movies, no search parameter
                            System.out.println("No search parameter provided. ");
                            continue;
                        }
                        String[] searchParts = queryParts[1].split("=");
                        // Browser will replace a white space with %20, we need to replace it back with " "
                        searchParts[1] = searchParts[1].replaceAll("%20", " ");
                        String response = createResponse(searchParts[1]);
                        pr.print(response);
                        pr.flush();
                    }
                    else {
                        System.out.println("Invalid request. ");
                    }
                    count++;
                }
            }

        }
        catch(IOException e){
                System.out.println(e);
        }
        finally {
            if (connectionSocket != null && welcomingSocket != null) {
                try {
                    connectionSocket.close();
                    welcomingSocket.close();
                }
                catch(IOException e) {
                    System.out.println(e);
                }
            }

        }
    }

    public String createResponse(String movieName) {
        StringBuilder response = new StringBuilder();
        String json = serialize(movieName).toString();
        byte[] jsonBytes = json.getBytes(StandardCharsets.UTF_8);
        int contentLength = jsonBytes.length;
        String header = "HTTP/1.1 200 OK" + "\r\n" +
                "Content-Type: application/json" + "\r\n" +
                "Content-Length: " + contentLength + "\r\n";
        // date could be added, but non-essential
        response.append(header).append("\r\n").append(json);
        return response.toString();
    }

    public static void main(String[] args) {
        MovieServer movieServer = new MovieServer();
        Movie m1 = new Movie("Inception", "Christopher Nolan", 2010);
        Movie m2 = new Movie("Titanic", "James Cameron", 1997);
        Movie m3 = new Movie("The Godfather", "Francis Ford Coppola", 1972);
        movieServer.addMovie(m1);
        movieServer.addMovie(m2);
        movieServer.addMovie(m3);

        movieServer.runServer();
    }

}
