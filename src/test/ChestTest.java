import exceptions.ContainerCarryException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ChestTest {

    Chest chest;
    @BeforeEach
    void setUp() {
        chest = new Chest("Chest", "Telephone");
    }

    @Test
    void takeWithOutOpening() {
        assertThrows(ContainerCarryException.class, () -> chest.take());
    }

    @Test
    void takeOpeningChest() {
        try {
            chest.open();
            assertNotNull(chest.take());
        } catch (ContainerCarryException e) {
            e.printStackTrace();
        }
    }

    @Test
    void open() {
        chest.open();
    }

    @Test
    void close() {
        chest.close();
    }
}