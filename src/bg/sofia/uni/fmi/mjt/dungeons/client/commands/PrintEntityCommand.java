package bg.sofia.uni.fmi.mjt.dungeons.client.commands;

import bg.sofia.uni.fmi.mjt.dungeons.engine.GameEngine;
import bg.sofia.uni.fmi.mjt.dungeons.entity.Entity;

public class PrintEntityCommand implements Command {
    private final Entity entity;
    private final GameEngine engine;

    public PrintEntityCommand(Entity entity, GameEngine engine) {
        this.entity = entity;
        this.engine = engine;
    }

    @Override
    public void execute() {
        engine.printEntity(entity);
    }
}
