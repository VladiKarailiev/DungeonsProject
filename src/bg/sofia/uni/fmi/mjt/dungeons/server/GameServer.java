
package bg.sofia.uni.fmi.mjt.dungeons.server;

import bg.sofia.uni.fmi.mjt.dungeons.engine.GameEngine;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class GameServer implements GameServerAPI {

    private static final int SERVER_PORT = 8008;
    private static final int MAX_EXECUTOR_THREADS = 10;

    private GameEngine engine;

    @Override
    public void start() {
        Thread.currentThread().setName("Echo Server Thread");

        engine = GameEngine.getInstance();

        try (ServerSocket serverSocket = new ServerSocket(SERVER_PORT);
             ExecutorService executor = Executors.newFixedThreadPool(MAX_EXECUTOR_THREADS);) {

            System.out.println("Server started and listening for connect requests");
            Socket clientSocket;

            while (true) {
                clientSocket = serverSocket.accept();

                System.out.println("Accepted connection request from client " + clientSocket.getInetAddress());

                ClientHandler clientHandler = new ClientHandler(clientSocket, engine);

                executor.execute(clientHandler);
            }
        } catch (IOException e) {
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

/*
    TO DO: LOADVA MAP OT FILE!
 */
