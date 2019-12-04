import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class GameTest {
    Game game;
    @BeforeEach
    void setUp() {
        game = new Game();
    }

    @AfterEach
    void tearDown() {
        game = null;
    }

    @Test
    void confirmCommande() {

        // Valid commands
        assertTrue(game.confirmCommande("GO"));
        assertTrue(game.confirmCommande("Go"));
        assertTrue(game.confirmCommande("gO"));
        assertTrue(game.confirmCommande("go"));
        assertTrue(game.confirmCommande("uSe"));
        assertTrue(game.confirmCommande("HELP"));
        assertTrue(game.confirmCommande("QUIT"));
        assertTrue(game.confirmCommande("TAKE"));
        assertTrue(game.confirmCommande("USE"));


        // Invalid commands
        assertFalse(game.confirmCommande(""));
        assertFalse(game.confirmCommande("regarder"));
        assertFalse(game.confirmCommande("sortir"));
        assertFalse(game.confirmCommande("prendre"));

    }

    @Test
    void countUsableObjects(){
        Player player = new Player("Player");

        player.addInventor(new Letter("", "1"));
        player.addInventor(new Letter("", "2"));
        player.addInventor(new Extinguisher("3", 0));
        player.addInventor(new Extinguisher("4", 0));
        player.addInventor(new Key("5", 0));

        assertNotNull(player.getUsableObjects());
        assertEquals(3, player.getUsableObjects().size());
    }

    @Test
    void takeAllObjects(){
        System.out.println(game.getPlace().describePlace());
        int itemsPortablesFromPlayer = game.getPlayer().getObjects().size();
        System.out.println("itemsPortablesFromPlayer " +  itemsPortablesFromPlayer);
        int itemsPortablesInRoom = game.getPlace().GetPortableItemRoom().size();
        System.out.println("itemsPortablesInRoom " +  itemsPortablesInRoom);
        game.take("TAKE");
        System.out.println("Now carrying " + game.getPlayer().getObjects().size());
        assertNotEquals(0, game.getPlayer().getObjects().size());
        assertEquals(itemsPortablesInRoom + itemsPortablesFromPlayer, game.getPlayer().getObjects().size());
        assertNotEquals(itemsPortablesFromPlayer, game.getPlayer().getObjects().size());
    }

    @Test
    void lookAtItem(){
        game.look("look letter1");
    }

    @Test
    void openChest(){
        game.look("look chest1");
        game.open("open chest1");
        game.look("look chest1");
        System.out.println("\n\n");
        game.open("open locked1");
        game.look("look locked1");
    }

    @Test
    void openDoor(){
        game.open("open exit");
    }

    @Test
    void takeUsableItemThatNotExist(){
        assertFalse(game.containsObject("jewerly_chest"));
    }



    @Test
    void takeSpecificItemInPlace(){
        game.take("TAKE letter1");
    }

    @Test
    void help() {
        game.help();
    }

    @Test
    void useKey() {
        game.useChestWithKey(game.getPlayer().getUsableObjects());
    }

    @Test
    void throwObject(){
        int initSize = game.getPlayer().getObjects().size();
        int initSize2 = game.getPlace().GetPortableItemRoom().size();

        game.eject("THROW KEY");
        assertNotEquals(initSize,game.getPlayer().getObjects().size() );
        assertNotEquals(initSize2 , game.getPlace().GetPortableItemRoom().size());
    }

    @Test
    void useKeyWithLockedDoor(){
        game.useDoor(game.getPlayer().getUsableObjects());
    }
}