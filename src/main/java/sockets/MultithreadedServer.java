package sockets;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class MultithreadedServer {
        public static final String EOT = "EOT";
        public static final String SHUTDOWN = "SHUTDOWN";
        public static final int PORT = 8000;
        private volatile boolean alive = true;

        public static void main(String[] args) {
            MultithreadedServer server = new MultithreadedServer();
            server.startServer();
        }

        public void startServer() {
            ExecutorService pool = Executors.newFixedThreadPool(4);
            Runnable serverTask = () -> {
                ServerSocket welcomingSocket = null; //a welcoming socket
                Socket connectionSocket = null;
                try {
                    // for listening for connection requests from clients
                    welcomingSocket = new ServerSocket(PORT);
                    while (alive) {
                        System.out.println("Server: Waiting for connection...");
                        // Waits for a client to connect, performs a 3 way handshake, and creates a new connection
                        // socket for interacting with this client.
                        connectionSocket = welcomingSocket.accept();
                        // Create a new Runnable Client Task to process each request
                        pool.submit(new SocketWorker(connectionSocket));
                    }
                } catch (IOException e) {
                    System.out.println(e);
                }
            };
            pool.submit(serverTask);
        }

        /** Server "helper" - Runnable task for interacting with one client */
        class SocketWorker implements Runnable {
            private final Socket connectionSocket;

            private SocketWorker(Socket connectionSocket) {
                this.connectionSocket = connectionSocket;
            }

            @Override
            public void run() {
                System.out.println("A client connected.");
                try (BufferedReader reader = new BufferedReader(new InputStreamReader(connectionSocket.getInputStream()))) {
                    String input;
                    while (!connectionSocket.isClosed()) {
                        input = reader.readLine(); // read from the socket
                        System.out.println("Server received: " + input); // echo the same string to the console

                        if (input.equals(EOT)) {
                            System.out.println("Server: Closing socket.");
                            connectionSocket.close();
                        } else if (input.equals(SHUTDOWN)) {
                            alive = false;
                            System.out.println("Server: Shutting down.");
                            connectionSocket.close();
                        }
                    }
                } catch (IOException e) {
                    System.out.println(e);
                } finally {
                    try {
                        if (connectionSocket != null)
                            connectionSocket.close();
                    } catch (IOException e) {
                        System.out.println("Can't close the socket : " + e);
                    }
                }
            }
        }
}
