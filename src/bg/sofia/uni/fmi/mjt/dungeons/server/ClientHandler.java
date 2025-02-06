package bg.sofia.uni.fmi.mjt.dungeons.server;

import bg.sofia.uni.fmi.mjt.dungeons.client.commands.Command;
import bg.sofia.uni.fmi.mjt.dungeons.client.commands.MoveCommand;
import bg.sofia.uni.fmi.mjt.dungeons.client.commands.PrintCommand;
import bg.sofia.uni.fmi.mjt.dungeons.client.commands.PrintEntityCommand;
import bg.sofia.uni.fmi.mjt.dungeons.engine.GameEngine;
import bg.sofia.uni.fmi.mjt.dungeons.entity.Direction;
import bg.sofia.uni.fmi.mjt.dungeons.entity.actor.Hero;
import bg.sofia.uni.fmi.mjt.dungeons.entity.actor.Stats;

import java.io.IOException;
import java.net.Socket;
import java.util.Scanner;

import static bg.sofia.uni.fmi.mjt.dungeons.engine.GameEngine.MAX_PLAYERS;

public class ClientHandler implements Runnable {

    private final ClientSession clientSession;
    private final GameEngine engine;
    private final Hero hero;
    public ClientHandler(ClientSession clientSession, GameEngine engine) throws Exception {
        this.clientSession = clientSession;
        this.engine = engine;
        hero = new Hero(engine.getNextFreeSpawn(), "Pich", new Stats(100, 200, 30, 40), null, null);
        engine.addEntity(hero);
        clientSession.changeHero(hero);
    }

    @Override
    public void run() {
        Thread.currentThread()
            .setName("Client Handler for " + clientSession.getCommandSocket().getRemoteSocketAddress());
        Socket socket = clientSession.getCommandSocket();
        try (Scanner scanner = new Scanner(clientSession.getCommandSocket().getInputStream())) {
            System.out.println("Waiting for client message...");
            while (scanner.hasNextLine()) {
                String inputLine = scanner.nextLine();
                System.out.println("Message received from client: " + inputLine);
                handleCommand(inputLine);
                synchronized (engine) {
                    engine.notifyAll();
                }
            }
            System.out.println("Client disconnected or message stream ended.");
        } catch (IOException e) {
            System.out.println(e.getMessage());
        } finally {
            try {
                clientSession.getCommandSocket().close();
                clientSession.getMapSocket().close();
                engine.removeEntity(hero);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }

    private void handleCommand(String inputLine) {
        Command cmd = commandBuilder(inputLine);
        if (cmd != null)
            cmd.execute();
    }

    private Command commandBuilder(String inputLine) {
        switch (inputLine) {
            case "a" -> {
                return new MoveCommand(engine, hero, Direction.LEFT);
            }
            case "d" -> {
                return new MoveCommand(engine, hero, Direction.RIGHT);
            }
            case "w" -> {
                return new MoveCommand(engine, hero, Direction.UP);
            }
            case "s" -> {
                return new MoveCommand(engine, hero, Direction.DOWN);
            }
            case "p" -> {
                return new PrintCommand(engine);
            }

        }
        return null;
    }

}

/*
    bug ne poluchava komandi
 */