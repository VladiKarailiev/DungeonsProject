package bg.sofia.uni.fmi.mjt.dungeons.server;

import bg.sofia.uni.fmi.mjt.dungeons.entity.actor.Hero;

import java.net.Socket;

public class ClientSession {

    final Socket commandSocket;
    final Socket mapSocket;
    Hero hero;

    public ClientSession(Socket commandSocket, Socket mapSocket, Hero hero) {
        if (commandSocket == null || mapSocket == null) {
            throw new IllegalArgumentException("Sockets can't be null");
        }

        this.commandSocket = commandSocket;
        this.mapSocket = mapSocket;
        this.hero = hero;
    }

    public Socket getCommandSocket() {
        return commandSocket;
    }

    public Socket getMapSocket() {
        return mapSocket;
    }

    public Hero getHero() {
        return hero;
    }

    void changeHero(Hero hero) {
        this.hero = hero;
    }
}
