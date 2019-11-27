import static org.junit.jupiter.api.Assertions.*;



import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import exceptions.PlaceException;

class ExitTest {

	Exit exit;
	Exitwithlock lockexit;
	Exitwithkey keyexit;
	
	
	Key k;
	Key falsek;
	Place start;
	Place reach;
	
    @BeforeEach
    void setUp() {
    	// Initialization of objects for start
    	start = new Room("Room", "Dark, where the urge to flee is becoming oppressive",null);
    	
    	// Initialization of destination
    	reach = new Room("Corridor", "A long way to an unknown future", null); 
    	
    	//Initialization of keys (for Exitwithkey)
    	k = new Key("Key",12345);
    	falsek = new Key("Key",54321); 
    
    	// Initialization of exit
        exit = new Exit(start, reach);
        lockexit = new Exitwithlock(start, reach);
        keyexit = new Exitwithkey(start, reach, k);
        
        
    }

    @Test
    void changePlace() throws PlaceException 
    {
    	exit.open();
    	assertEquals(exit.nextPlace(), reach);	
    }

    @Test
    void openExit() {
    	assertFalse(exit.isopen());
    	exit.close();
    	assertFalse(exit.isopen());
    	exit.open();
    	assertTrue(exit.isopen());
    }

    @Test
    void unlockAndOpen() 
    {
    	lockexit.unlock();
    	lockexit.open();
    	assertTrue(lockexit.isopen());
    	assertFalse(lockexit.islock());
    }
    
    @Test
    void defaultValueExitwithlock() 
    {
    	assertFalse(lockexit.isopen());
    	assertTrue(lockexit.islock());
    }
    
    @Test
    void penetratingWhenLock() 
    {
    	lockexit.close();
    	assertTrue(lockexit.islock());
    	lockexit.open();
    	assertFalse(lockexit.isopen());
    	assertTrue(lockexit.islock());
    }
    
    @Test
    void goingThroughWhenLock()
    {
    	assertThrows(PlaceException.class, () -> {
    		lockexit.nextPlace();
    	});
    }
    
    @Test
    void unlockButNotOpen()
    {
    	lockexit.unlock();
    	assertFalse(lockexit.isopen());
    	assertFalse(lockexit.islock());
    }
    
    
    @Test
    void openWithGoodKey() 
    {
    	keyexit.unlock(k);
    	assertFalse(keyexit.islock());
    	keyexit.open();
    	assertTrue(keyexit.isopen());
    	;
    }
    
    @Test
    void openWithFalseKey() 
    {
    	keyexit.unlock(falsek);
    	keyexit.open();
    	assertFalse(keyexit.isopen());
    }
    
    @Test
    void tryToUnlockWithoutKey()
    {
    	keyexit.unlock();
    	keyexit.open();
    	assertTrue(keyexit.islock());
    	assertFalse(keyexit.isopen());
    }
}
