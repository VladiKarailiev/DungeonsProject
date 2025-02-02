package bg.sofia.uni.fmi.mjt.dungeons.server;

import java.net.Socket;

public record ClientSession(Socket commandSocket, Socket mapSocket) {
}
