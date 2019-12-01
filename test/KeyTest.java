import exceptions.NotRightKey;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class KeyTest {

    @Test
    void useKeyWithRightCode(){
        Key key = new Key("",0);
        LockedChest lockedChest = new LockedChest("","", 0);

        //we have the right key when there aren't exceptions
        assertDoesNotThrow(() -> lockedChest.open(key.getCode()));
    }

    @Test
    void useKeyWithWrongCode(){
        Key key = new Key("",1);
        LockedChest lockedChest = new LockedChest("","", 0);

        //we have the wrong key when there are exceptions
        assertThrows(NotRightKey.class, () -> lockedChest.open(key.getCode()));
    }


}