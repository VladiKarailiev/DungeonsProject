package bg.sofia.uni.fmi.mjt.dungeons.server;

import bg.sofia.uni.fmi.mjt.dungeons.engine.GameEngine;

import java.io.IOException;
import java.io.PrintWriter;

public class MapUpdater implements Runnable {

    private ClientSession clientSession;
    private GameEngine engine;

    public MapUpdater(ClientSession clientSession, GameEngine engine) {
        this.clientSession = clientSession;
        this.engine = engine;
    }

    @Override
    public void run() {

        Thread.currentThread().setName("MapUpdater for " + clientSession.mapSocket().getRemoteSocketAddress());

        try (PrintWriter out = new PrintWriter(clientSession.mapSocket().getOutputStream(), true)) {
            while (true) {
                synchronized (clientSession) {
                    clientSession.wait();
                }
                System.out.println("Map sent to client");
                out.println(engine.getStringifiedMap());

            }
        } catch (IOException | InterruptedException e) {

            System.out.println(e.getMessage());

        } finally {

            try {

                clientSession.mapSocket().close();
            } catch (IOException e) {

                e.printStackTrace();
            }
        }

    }

}