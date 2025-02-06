package bg.sofia.uni.fmi.mjt.dungeons.client.commands;

import bg.sofia.uni.fmi.mjt.dungeons.engine.GameEngine;
import bg.sofia.uni.fmi.mjt.dungeons.entity.Direction;
import bg.sofia.uni.fmi.mjt.dungeons.entity.Entity;
import bg.sofia.uni.fmi.mjt.dungeons.entity.Position;

public class MoveCommand implements Command {

    private final GameEngine engine;
    private final Entity entity;
    private final Direction direction;

    public MoveCommand(GameEngine engine, Entity entity, Direction direction) {
        if (engine == null || entity == null || direction == null) {
            throw new IllegalArgumentException("Arguments can't be null");
        }
        this.engine = engine;
        this.entity = entity;
        this.direction = direction;
    }

    @Override
    public void execute() {
        Position from = entity.getPos();
        Position to = switch (direction) {
            case UP -> new Position(from.x() - 1, from.y());
            case DOWN -> new Position(from.x() + 1, from.y());
            case LEFT -> new Position(from.x(), from.y() - 1);
            case RIGHT -> new Position(from.x(), from.y() + 1);
        };
        engine.moveEntity(entity, to);

    }
}
