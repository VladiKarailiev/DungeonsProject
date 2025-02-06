package bg.sofia.uni.fmi.mjt.dungeons.client;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

import static bg.sofia.uni.fmi.mjt.dungeons.client.Client.SERVER_HOST;
import static bg.sofia.uni.fmi.mjt.dungeons.client.Client.SERVER_PORT;

public class CommandSender implements Runnable {
    private final Object connectionReadySignal;

    public CommandSender(Object connectionReadySignal) {
        if (connectionReadySignal == null) {
            throw new IllegalArgumentException("Arguments can't be null");
        }
        this.connectionReadySignal = connectionReadySignal;
    }

    @Override
    public void run() {
        try (Socket socket = new Socket(SERVER_HOST, SERVER_PORT);
             PrintWriter writer = new PrintWriter(socket.getOutputStream(), true);
             Scanner scanner = new Scanner(System.in)) {
            synchronized (connectionReadySignal) {
                connectionReadySignal.notify();
            }
            Thread.currentThread().setName("Client command thread " + socket.getLocalPort());
            while (true) {
                System.out.print("Enter command: ");
                String message = scanner.nextLine();

                if ("quit".equals(message)) {
                    break;
                }

                writer.write(message + System.lineSeparator());
                writer.flush();
            }

        } catch (IOException e) {
            throw new RuntimeException("There is a problem with the network communication", e);
        }
    }
}
