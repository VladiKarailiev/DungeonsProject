package bg.sofia.uni.fmi.mjt.dungeons.client;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

import static bg.sofia.uni.fmi.mjt.dungeons.client.Client.SERVER_HOST;
import static bg.sofia.uni.fmi.mjt.dungeons.client.Client.SERVER_PORT;

public class CommandSender implements Runnable {
    @Override
    public void run() {
        try (Socket socket = new Socket(SERVER_HOST, SERVER_PORT);
             PrintWriter writer = new PrintWriter(socket.getOutputStream(), true);
             Scanner scanner = new Scanner(System.in)) {

            Thread.currentThread().setName("Client command thread " + socket.getLocalPort());
            System.out.println("Connected to the server for commands.");
            while (true) {
                System.out.print("Enter message: ");
                String message = scanner.nextLine();

                if ("quit".equals(message)) {
                    break;
                }
                System.out.println("Sending message <" + message + "> to the server...");
                writer.write(message + System.lineSeparator());
                System.out.println("Message sent successfully: " + message);
                writer.flush();
            }

        } catch (IOException e) {
            throw new RuntimeException("There is a problem with the network communication", e);
        }
    }
}
