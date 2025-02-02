package bg.sofia.uni.fmi.mjt.dungeons.server;

import bg.sofia.uni.fmi.mjt.dungeons.engine.GameEngine;
import bg.sofia.uni.fmi.mjt.dungeons.entity.Entity;
import bg.sofia.uni.fmi.mjt.dungeons.entity.Position;
import bg.sofia.uni.fmi.mjt.dungeons.entity.actor.Hero;
import bg.sofia.uni.fmi.mjt.dungeons.entity.actor.Stats;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class ClientHandler implements Runnable {

    private Socket socket;
    private GameEngine engine;
    private Position currPosition;

    public ClientHandler(Socket socket, GameEngine engine) {
        this.socket = socket;
        this.engine = engine;
        currPosition = engine.getNextFreeSpawn();
        /// tva she trqq se izmisli kak da idva ot client-a
        engine.addEntity(currPosition, new Hero("Pich", new Stats(1, 2, 3, 4), null, null));
    }

    @Override
    public void run() {

        Thread.currentThread().setName("Client Handler for " + socket.getRemoteSocketAddress());

        try (PrintWriter out = new PrintWriter(socket.getOutputStream(), true); // autoflush on
             BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()))) {

            String inputLine;
            while ((inputLine = in.readLine()) != null) { // read the message from the client
                System.out.println("Message received from client: " + inputLine);
                handleCommand(inputLine);
                out.println(engine.getStringifiedMap()); // send response back to the client
            }

        } catch (IOException e) {
            System.out.println(e.getMessage());
        } finally {
            try {
                socket.close();
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