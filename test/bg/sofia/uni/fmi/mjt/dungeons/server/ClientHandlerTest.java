package bg.sofia.uni.fmi.mjt.dungeons.server;

import bg.sofia.uni.fmi.mjt.dungeons.client.commands.Command;
import bg.sofia.uni.fmi.mjt.dungeons.client.commands.MoveCommand;
import bg.sofia.uni.fmi.mjt.dungeons.client.commands.PrintCommand;
import bg.sofia.uni.fmi.mjt.dungeons.engine.GameEngine;
import bg.sofia.uni.fmi.mjt.dungeons.entity.Direction;
import bg.sofia.uni.fmi.mjt.dungeons.entity.Position;
import bg.sofia.uni.fmi.mjt.dungeons.entity.actor.Hero;
import bg.sofia.uni.fmi.mjt.dungeons.entity.actor.Stats;
import bg.sofia.uni.fmi.mjt.dungeons.exceptions.IllegalCommandException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

class ClientHandlerTest {

    private ClientHandler clientHandler;
    private ClientSession clientSession;
    private GameEngine engine;
    private Hero hero;
    private Stats stats;

    @BeforeEach
    public void setUp() throws Exception {
        clientSession = mock(ClientSession.class);
        engine = mock(GameEngine.class);
        Position pos = new Position(1, 1);
        stats = new Stats(100, 100, 10, 10);

        //System.out.println("Hero Position: " + pos);
        hero = new Hero(pos, "Pich", stats, null, null);

        Position pos2 = new Position(2, 2);
        when(engine.getNextFreeSpawn()).thenReturn(pos2);
        clientHandler = new ClientHandler(clientSession, engine);

        when(clientSession.getCommandSocket()).thenReturn(mock(java.net.Socket.class));
    }


    @Test
    public void testHandleInvalidCommand() {
        String input = "z";
        assertThrows(IllegalCommandException.class, () -> {
            clientHandler.commandBuilder(input);
        }, "Illegal commands should throw exception");
    }

    @Test
    public void testHandlePrintCommand() throws IllegalCommandException {
        String input = "p";
        Command cmd = clientHandler.commandBuilder(input);
        cmd.execute();
        verify(engine, times(1)).printMap();
    }
    @Test
    public void testHandleMoveCommand() throws IllegalCommandException {
        String input = "a";
        Command cmd = clientHandler.commandBuilder(input);
        cmd.execute();
        verify(engine, times(1)).moveEntity(any(),any());
    }

}
