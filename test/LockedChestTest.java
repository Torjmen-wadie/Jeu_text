import exceptions.NotRightKey;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class LockedChestTest {

    LockedChest lockedChest;

    @BeforeEach
    void setUp() {
        lockedChest = new LockedChest("Locked", new ArrayList<Item>(), 20);
    }

    @Test
    void openWithWrongCode() {
        assertThrows(NotRightKey.class, ()->lockedChest.open(30));
    }

    @Test
    void openWithRightCode() {
        try {
            lockedChest.open(20);
        } catch (NotRightKey notRightKey) {
            notRightKey.printStackTrace();
        }
    }
}