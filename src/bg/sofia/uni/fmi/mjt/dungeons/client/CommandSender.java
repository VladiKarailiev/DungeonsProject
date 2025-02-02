package bg.sofia.uni.fmi.mjt.dungeons.client;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

import static bg.sofia.uni.fmi.mjt.dungeons.client.Client.SERVER_PORT;

public class CommandSender implements Runnable {
    @Override
    public void run() {
        try (Socket socket = new Socket("localhost", SERVER_PORT);
             PrintWriter writer = new PrintWriter(socket.getOutputStream(), true);
             Scanner scanner = new Scanner(System.in)) {

            Thread.currentThread().setName("Client command thread " + socket.getLocalPort());
            System.out.println("Connected to the server for commands.");
            /* ot tuka nadolu v dr method ------------------------------*/
            while (true) {
                System.out.print("Enter message: ");
                String message = scanner.nextLine();

                if ("quit".equals(message)) {
                    break;
                }
                System.out.println("Sending message <" + message + "> to the server...");
                writer.println(message);
                /*
                char[] buff = new char[BUFFER_SIZE]; // she se smenq
                int charsRead = reader.read(buff); // read the response from the server
                String map = new String(buff, 0, charsRead); // Only use the valid portion
                System.out.print(map);
                 */
            }

        } catch (IOException e) {
            throw new RuntimeException("There is a problem with the network communication", e);
        }
    }
}
