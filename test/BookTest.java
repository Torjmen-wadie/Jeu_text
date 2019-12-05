import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class BookTest {
Book fisrtBook;
    @BeforeEach
    void setUp() {
     fisrtBook=new Book("you must find the key","javaDoc");
    }

    @Test
    void look() {
        fisrtBook.look();
    }
}