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
        this.engine = engine;
        this.entity = entity;
        this.direction = direction;
    }

    @Override
    public void execute() {
        Position to = entity.getPos();
        Position from = entity.getPos();
        switch (direction) {
            case UP:
                to = new Position(from.x() - 1, from.y());
                break;
            case DOWN:
                to = new Position(from.x() + 1, from.y());
                break;
            case LEFT:
                to = new Position(from.x(), from.y() - 1);
                break;
            case RIGHT:
                to = new Position(from.x(), from.y() + 1);
                break;
        }
        engine.moveEntity(entity, to);
        from = new Position(to.x(), to.y());

    }
}
