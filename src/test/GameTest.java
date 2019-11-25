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
}