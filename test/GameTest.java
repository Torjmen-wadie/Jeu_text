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

        player.addInventor(new Letter("", ""));
        player.addInventor(new Letter("", ""));
        player.addInventor(new Extinguisher("", 0));
        player.addInventor(new Extinguisher("", 0));
        player.addInventor(new Key("", 0));

        assertNotNull(player.getUsableObjects());
        assertEquals(3, player.getUsableObjects().size());
    }

    @Test
    void takeAllObjects(){
        int itemsInRoom = game.place.GetPortableItemRoom().size();
        int itemsFromPlayer = game.player.getUsableObjects().size();
        System.out.println("itemsFromPlayer " +  itemsFromPlayer);
        System.out.println("itemsInRoom " +  itemsInRoom);
        game.place.GetPortableItemRoom().forEach( x -> ((Item)x).look());
        game.take("TAKE");
        game.place.GetPortableItemRoom().forEach( x -> ((Item)x).look());
        //assertNotEquals(itemsFromPlayer, game.player.getUsableObjects().size());
    }

    @Test
    void help() {
        game.help();
    }
}