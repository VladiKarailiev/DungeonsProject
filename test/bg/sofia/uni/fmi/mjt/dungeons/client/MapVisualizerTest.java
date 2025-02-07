package bg.sofia.uni.fmi.mjt.dungeons.client;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.net.Socket;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.mockStatic;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.when;
class MapVisualizerTest {

    @BeforeEach
    public void setUp() throws IOException {
        Object connectionReadySignal = new Object();
        Socket socket = mock(Socket.class);
    }

    @Test
    public void testMapVisualizerThrowsExceptionForNullArgument() {
        assertThrows(IllegalArgumentException.class, () -> new MapVisualizer(null),
            "MapVisualizer should throw when the argument is null.");
    }


}
