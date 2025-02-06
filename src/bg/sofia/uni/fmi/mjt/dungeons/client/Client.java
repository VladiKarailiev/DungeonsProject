package bg.sofia.uni.fmi.mjt.dungeons.client;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Client {


    static final int SERVER_PORT = 8008;
    static final String SERVER_HOST = "localhost";
    private static final int MAX_EXECUTOR_THREADS = 5;

    public static void main(String[] args) {

        Thread.currentThread().setName("Client Thread");

        try (ExecutorService executor = Executors.newFixedThreadPool(MAX_EXECUTOR_THREADS)) {

            CommandSender commandSender = new CommandSender();
            MapVisualizer mapVisualizer = new MapVisualizer();
            executor.execute(commandSender);
            executor.execute(mapVisualizer);

        }
    }

}