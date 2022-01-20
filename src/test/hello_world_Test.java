import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class HelloWorldTest {

    @DisplayName("Test HelloWorld.get()")
    @Test
    void testGet() {
        assertEquals("Hello World!", HelloWorld.get());
    }

}
