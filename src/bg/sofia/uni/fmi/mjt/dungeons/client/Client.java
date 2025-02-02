package bg.sofia.uni.fmi.mjt.dungeons.client;

import bg.sofia.uni.fmi.mjt.dungeons.entity.Direction;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class Client implements GameClientAPI {


    private static final int SERVER_PORT = 8008;

    @Override
    public void connect() {
        try (Socket socket = new Socket("localhost", SERVER_PORT);
             PrintWriter writer = new PrintWriter(socket.getOutputStream(), true); // autoflush on
             BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
             Scanner scanner = new Scanner(System.in)) {

            Thread.currentThread().setName("Echo client thread " + socket.getLocalPort());

            System.out.println("Connected to the server.");

            /* ot tuka nadolu v dr method ------------------------------*/
            while (true) {
                System.out.print("Enter message: ");
                String message = scanner.nextLine(); // read a line from the console

                if ("quit".equals(message)) {
                    quit();
                }
                System.out.println("Sending message <" + message + "> to the server...");
                writer.println(message);
                String reply = reader.readLine(); // read the response from the server
                System.out.println("The server replied <" + reply + ">");
                /* do tuka--------------------------------------------------*/
            }

        } catch (IOException e) {
            throw new RuntimeException("There is a problem with the network communication", e);
        }
    }

    @Override
    public void quit() {

    }

    @Override
    public void move(Direction dir) {

    }

    @Override
    public void displayMap() { // da se dobavi klas mapvisualizer!

    }

    public static void main(String[] args) {
        Client test = new Client();
        test.connect();

    }
}
