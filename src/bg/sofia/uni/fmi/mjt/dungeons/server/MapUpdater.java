package bg.sofia.uni.fmi.mjt.dungeons.server;

import bg.sofia.uni.fmi.mjt.dungeons.engine.GameEngine;

import java.io.IOException;
import java.io.PrintWriter;

public class MapUpdater implements Runnable {

    private final ClientSession clientSession;
    private final GameEngine engine;

    public MapUpdater(ClientSession clientSession, GameEngine engine) {
        this.clientSession = clientSession;
        this.engine = engine;
    }

    @Override
    public void run() {

        Thread.currentThread().setName("MapUpdater for " + clientSession.getMapSocket().getRemoteSocketAddress());

        try (PrintWriter out = new PrintWriter(clientSession.getMapSocket().getOutputStream(), true)) {
            while (true) {
                synchronized (engine) {
                    engine.wait();
                }
                System.out.println("Map sent to client");
                out.println(engine.getStringifiedMap());
                out.println(clientSession.getHero().toString());

            }
        } catch (IOException | InterruptedException e) {

            System.out.println(e.getMessage());

        } finally {

            try {

                clientSession.getMapSocket().close();
            } catch (IOException e) {

                e.printStackTrace();
            }
        }

    }

}