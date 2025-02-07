package bg.sofia.uni.fmi.mjt.dungeons.client.commands;


import bg.sofia.uni.fmi.mjt.dungeons.engine.GameEngine;
import bg.sofia.uni.fmi.mjt.dungeons.entity.Direction;
import bg.sofia.uni.fmi.mjt.dungeons.entity.Entity;
import bg.sofia.uni.fmi.mjt.dungeons.entity.Position;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;


class CommandTests {
    private GameEngine engine;
    private Entity entity;

    @BeforeEach
    public void setUp() {
        engine = mock(GameEngine.class);
        entity = mock(Entity.class);
    }

    @Test
    public void testMoveCommandExecutesCorrectly() {
        Position startPosition = new Position(2, 2);
        when(entity.getPos()).thenReturn(startPosition);
        Direction direction = Direction.UP;
        Position expectedPosition = new Position(1, 2);

        MoveCommand moveCommand = new MoveCommand(engine, entity, direction);
        moveCommand.execute();

        verify(engine).moveEntity(entity, expectedPosition);
    }

    @Test
    public void testPrintCommandExecutesCorrectly() {
        PrintCommand printCommand = new PrintCommand(engine);
        printCommand.execute();

        verify(engine).printMap();
    }

    @Test
    public void testPrintEntityCommandExecutesCorrectly() {
        PrintEntityCommand printEntityCommand = new PrintEntityCommand(entity, engine);
        printEntityCommand.execute();

        verify(engine).printEntity(entity);
    }

    @Test
    public void testMoveCommandThrowsExceptionForNullArgument() {
        assertThrows(IllegalArgumentException.class, () -> new MoveCommand(null, entity, Direction.UP),
            "Move Command should throw when the argument is null.");
    }

    @Test
    public void testPrintCommandThrowsExceptionForNullEngine() {
        assertThrows(IllegalArgumentException.class, () -> new PrintCommand(null),
            "Print Command should throw when the argument is null.");
    }

    @Test
    public void testPrintEntityCommandThrowsExceptionForNullArgument() {
        assertThrows(IllegalArgumentException.class, () -> new PrintEntityCommand(null, engine),
            "PrintEntity Command should throw when the argument is null.");
    }
}
