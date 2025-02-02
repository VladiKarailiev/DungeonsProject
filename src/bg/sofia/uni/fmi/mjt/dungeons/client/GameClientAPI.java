package bg.sofia.uni.fmi.mjt.dungeons.client;

import bg.sofia.uni.fmi.mjt.dungeons.entity.Direction;

public interface GameClientAPI {

    void connect();

    void quit();

    void move(Direction dir);

    void displayMap();

}
