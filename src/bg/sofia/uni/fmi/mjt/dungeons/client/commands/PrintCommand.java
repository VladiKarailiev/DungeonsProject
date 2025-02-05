package bg.sofia.uni.fmi.mjt.dungeons.client.commands;

import bg.sofia.uni.fmi.mjt.dungeons.engine.GameEngine;

public class PrintCommand implements Command {
    private final GameEngine engine;

    public PrintCommand(GameEngine engine) {
        this.engine = engine;
    }

    @Override
    public void execute() {
        engine.printMap();
    }
}
