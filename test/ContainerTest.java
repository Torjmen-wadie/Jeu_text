import exceptions.ContainerCarryException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ContainerTest {

    Container container;
    @BeforeEach
    void setUp() {
        container = new Container("Container",  "Key");
    }

    @Test
    void takeWithOutLookingInside() {
        assertThrows(ContainerCarryException.class, () -> container.take());
    }

    @Test
    void takeLookingInside() {
        try {
            container.alreadySaw();
            assertNotNull(container.take());
            container.take().look();
        } catch (ContainerCarryException e) {
            System.out.println(e.getMessage());
        }
    }

    @Test
    void hasntLookInside() {
        container.look();
    }

    @Test
    void hasLookInside(){
        container.alreadySaw();
        container.look();
    }
}