package bg.sofia.uni.fmi.mjt.dungeons.server;

import bg.sofia.uni.fmi.mjt.dungeons.engine.GameEngine;
import bg.sofia.uni.fmi.mjt.dungeons.entity.Position;
import bg.sofia.uni.fmi.mjt.dungeons.entity.actor.Hero;
import bg.sofia.uni.fmi.mjt.dungeons.entity.actor.Stats;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Scanner;

public class ClientHandler implements Runnable {

    private final ClientSession clientSession;
    private GameEngine engine;
    private Position currPosition;

    public ClientHandler(ClientSession clientSession, GameEngine engine) {
        this.clientSession = clientSession;
        this.engine = engine;
        currPosition = engine.getNextFreeSpawn();
        /// tva she trqq se izmisli kak da idva ot client-a
        engine.addEntity(currPosition, new Hero("Pich", new Stats(1, 2, 3, 4), null, null));
    }

    @Override
    public void run() {
        Thread.currentThread().setName("Client Handler for " + clientSession.commandSocket().getRemoteSocketAddress());

        try (Scanner scanner = new Scanner(clientSession.commandSocket().getInputStream())) {
            System.out.println("Waiting for client message...");
            while (scanner.hasNext()) {
                String inputLine = scanner.next();
                System.out.println("Message received from client: " + inputLine);
                handleCommand(inputLine);
                synchronized (clientSession) {
                    clientSession.notifyAll();
                }
            }
            System.out.println("Client disconnected or message stream ended.");
        } catch (IOException e) {
            System.out.println(e.getMessage());
        } finally {
            try {
                clientSession.commandSocket().close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }

    private void handleCommand(String inputLine) { // tuka command pattern i builder pattern
        String[] parts = inputLine.split(" ");
        if (parts[0].equals("move")) {
            engine.moveEntity(currPosition, new Position(Integer.parseInt(parts[1]), Integer.parseInt(parts[2])));
            currPosition = new Position(Integer.parseInt(parts[1]), Integer.parseInt(parts[2]));
        }
        if (parts[0].equals("print")) engine.printMap();
    }

}

/*
    bug ne poluchava komandi
 */