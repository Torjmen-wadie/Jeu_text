import exceptions.ContainerCarryException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ContainerTest {

    Container container;
    List<Item> insideItems;
    @BeforeEach
    void setUp() {
        insideItems = new ArrayList<>();
        insideItems.add(new Letter("asdasdads", "love letter"));
        insideItems.add(new Letter("assdasd", "Magasin letter"));
        container = new Container("Container", insideItems);
    }

    @Test
    void take() {
        assertThrows(ContainerCarryException.class, () -> container.take());
    }

    @Test
    void getInsideObjects(){
        List<Item> items = container.getItems();
        assertNotNull(items);
        assertNotEquals(0, items.size());
    }

    @Test
    void showInsideObjects() {
        container.look();
    }

}