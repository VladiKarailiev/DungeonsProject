
package bg.sofia.uni.fmi.mjt.dungeons.server;

import bg.sofia.uni.fmi.mjt.dungeons.engine.GameEngine;

import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import static bg.sofia.uni.fmi.mjt.dungeons.engine.GameEngine.MAX_PLAYERS;

public class GameServer {

    private static final int SERVER_PORT = 8008;
    private static final int MAX_EXECUTOR_THREADS = 20;
    static int playersCount = 0;

    public static void start() {
        Thread.currentThread().setName("Server Thread");
        GameEngine engine = GameEngine.getInstance();
        try (ServerSocket serverSocket = new ServerSocket(SERVER_PORT);
             ExecutorService executor = Executors.newFixedThreadPool(MAX_EXECUTOR_THREADS)) {
            System.out.println("Server started and listening for connect requests");
            while (true) {
                Socket commandSocket = serverSocket.accept();
                InetAddress clientAddress = commandSocket.getInetAddress();
                System.out.println("Accepted connection from " + clientAddress);
                Socket mapSocket = serverSocket.accept();
                ClientSession clientSession = new ClientSession(commandSocket, mapSocket, null);
                MapUpdater mapUpdater = new MapUpdater(clientSession, engine);
                ClientHandler clientHandler = new ClientHandler(clientSession, engine);
                playersCount++;
                if (playersCount > MAX_PLAYERS) {
                    try (PrintWriter out = new PrintWriter(mapSocket.getOutputStream(), true)) {
                        out.println("Server is full! Max 9 players allowed.");
                    }
                    mapSocket.close();
                    commandSocket.close();
                    continue;
                }
                executor.execute(clientHandler);
                executor.execute(mapUpdater);
            }
        } catch (Exception e) {
            throw new RuntimeException("There is a problem with the server socket", e);
        }
    }

    public static void main(String[] args) {
        GameServer.start();
    }
}
