package bg.sofia.uni.fmi.mjt.dungeons.client;

import bg.sofia.uni.fmi.mjt.dungeons.entity.Direction;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Client {


    static final int SERVER_PORT = 8008;
    private static final int BUFFER_SIZE = 1024;
    private static final int MAX_EXECUTOR_THREADS = 2;

    public static void main(String[] args) {

        Thread.currentThread().setName("Client Thread");

        Client test = new Client();

        try (ExecutorService executor = Executors.newFixedThreadPool(MAX_EXECUTOR_THREADS)) {

            CommandSender commandSender = new CommandSender();
            MapVisualizer mapVisualizer = new MapVisualizer();
            executor.execute(commandSender);
            executor.execute(mapVisualizer);
        }
    }

    private void startMapVisualizer() {
    }
}