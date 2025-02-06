
package bg.sofia.uni.fmi.mjt.dungeons.server;

import bg.sofia.uni.fmi.mjt.dungeons.engine.GameEngine;

import java.io.IOException;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import static bg.sofia.uni.fmi.mjt.dungeons.engine.GameEngine.MAX_PLAYERS;

public class GameServer implements GameServerAPI {

    private static final int SERVER_PORT = 8008;
    private static final int MAX_EXECUTOR_THREADS = 20;
    private final ConcurrentHashMap<InetAddress, ClientSession> clients = new ConcurrentHashMap<>();
    private int playersCount = 0;
    private GameEngine engine;

    @Override
    public void start() {
        Thread.currentThread().setName("Server Thread");
        engine = GameEngine.getInstance();

        try (ServerSocket serverSocket = new ServerSocket(SERVER_PORT);
             ExecutorService executor = Executors.newFixedThreadPool(MAX_EXECUTOR_THREADS)) {

            System.out.println("Server started and listening for connect requests");

            while (true) {
                Socket commandSocket = serverSocket.accept();
                InetAddress clientAddress = commandSocket.getInetAddress();
                System.out.println("Accepted command connection from " + clientAddress);
                Socket mapSocket = serverSocket.accept();
                clientAddress = commandSocket.getInetAddress();
                System.out.println("Accepted map update connection from " + clientAddress);

                ClientSession clientSession = new ClientSession(commandSocket, mapSocket, null);
                clients.put(clientAddress, clientSession);
                MapUpdater mapUpdater = new MapUpdater(clientSession, engine);
                ClientHandler clientHandler = new ClientHandler(clientSession, engine);
                playersCount++;
                if (playersCount > MAX_PLAYERS) continue;
                executor.execute(clientHandler);
                executor.execute(mapUpdater);
            }
        } catch (Exception e) {
            throw new RuntimeException("There is a problem with the server socket", e);
        }
    }

    @Override
    public void stop() {

    }

    public static void main(String[] args) {
        GameServer server = new GameServer();
        server.start();
    }
}
