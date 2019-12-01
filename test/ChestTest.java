import exceptions.ContainerCarryException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ChestTest {

    Chest chest;
    @BeforeEach
    void setUp() {
        chest = new Chest("Chest", new ArrayList<Item>());
    }

    @Test
    void takeWithOutOpening() {
        assertThrows(ContainerCarryException.class, () -> chest.take());
    }

    @Test
    void takeChest() {
        try {
            chest.open();
            assertNotNull(chest.take());
        } catch (ContainerCarryException e) {
            System.out.println(e.getMessage());
        }
    }

    @Test
    void open() {
        List<Item> insideItems;
        insideItems = new ArrayList<>();
        insideItems.add(new Letter("asdasdads", "love letter"));
        insideItems.add(new Letter("assdasd", "Magasin letter"));
        Chest chest = new Chest("Chest", insideItems);
        chest.open();
    }

    @Test
    void lookAtChest(){
        List<Item> insideItems;
        insideItems = new ArrayList<>();
        insideItems.add(new Letter("asdasdads", "love letter"));
        insideItems.add(new Letter("assdasd", "Magasin letter"));
        Chest chest = new Chest("Chest", insideItems);
        chest.open();
    }
    @Test
    void close() {
        chest.close();
        chest.look();
    }
}