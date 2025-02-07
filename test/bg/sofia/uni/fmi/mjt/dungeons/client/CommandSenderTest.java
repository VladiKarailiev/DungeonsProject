package bg.sofia.uni.fmi.mjt.dungeons.client;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.net.Socket;


import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class CommandSenderTest {
    private Object connectionReadySignal;
    private Socket socket;

    @BeforeEach
    public void setUp() throws IOException {
        connectionReadySignal = new Object();
        socket = mock(Socket.class);
    }

    @Test
    public void testCommandSenderThrowsExceptionForNullArgument() {
        assertThrows(IllegalArgumentException.class, () -> new CommandSender(null),
            "CommandSender should throw when the argument is null.");
    }

}
