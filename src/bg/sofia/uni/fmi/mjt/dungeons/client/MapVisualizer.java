package bg.sofia.uni.fmi.mjt.dungeons.client;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;

import static bg.sofia.uni.fmi.mjt.dungeons.client.Client.SERVER_HOST;
import static bg.sofia.uni.fmi.mjt.dungeons.client.Client.SERVER_PORT;

public class MapVisualizer implements Runnable {
    private static final int BUFFER_SIZE = 1024;

    private final Object connectionReadySignal;

    public MapVisualizer(Object connectionReadySignal) {
        if (connectionReadySignal == null) {
            throw new IllegalArgumentException("Arguments can't be null");
        }
        this.connectionReadySignal = connectionReadySignal;
    }

    @Override
    public void run() {
        synchronized (connectionReadySignal) {
            try {
                connectionReadySignal.wait();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
        try (Socket socket = new Socket(SERVER_HOST, SERVER_PORT);
             BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()))) {
            Thread.currentThread().setName("Client map visualizer thread " + socket.getLocalPort());
            while (true) {
                char[] buff = new char[BUFFER_SIZE];
                int charsRead = reader.read(buff, 0, BUFFER_SIZE);
                if (charsRead == -1) continue;
                String map = new String(buff, 0, charsRead);
                System.out.print(map);
            }

        } catch (IOException e) {
            throw new RuntimeException("There is a problem with the network communication", e);
        }
    }
}
