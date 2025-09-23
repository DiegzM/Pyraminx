package pyraminx.model;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class PyraminxModelTest {

    // Test the initialization of the Model
    @Test
    void testInitialization() {
        int layers = 3;
        PyraminxModel model = new PyraminxModel(layers);
        int[] state = model.getState();

        // Check if model stores 36 stickers (9 stickers per face * 4 sides)
        assertEquals(36, state.length, "State should have 36 elements at layers = 3");

        // Check if stickers are initialized with state indices (0,1,2,3)
        assertEquals(0, state[0], "First face should be 0");
        assertEquals(1, state[9], "Second face should be 1");
        assertEquals(2, state[18], "Third face should be 2");
        assertEquals(3, state[27], "Fourth face should be 3");

    }
}
