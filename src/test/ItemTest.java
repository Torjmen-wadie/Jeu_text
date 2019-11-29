import exceptions.ContainerCarryException;
import exceptions.NotRightKey;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class ItemTest {
    Item object;
    @BeforeEach
    void setUp() {
        object = new Item("Test");
    }


    @Test
    void take() {
        try {
            assertNotNull(object.take());
            object.look();
            assertEquals(object, object.take());
        } catch (ContainerCarryException e) {
            e.printStackTrace();
        }

    }

    @Test
    void look() {
        object.look();
    }
}