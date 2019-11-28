import static org.junit.jupiter.api.Assertions.*;



import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import exceptions.PlaceException;

class ExitTest {

	Exit exit;
	Exitwithlock lockexit;
	Exitwithkey keyexit;
	Exitwithobject objectexit;
	
	Key k;
	Key falsek;
	Place start;
	Place reach;
	
	Item extinct;
	
	
    @BeforeEach
    void setUp() {
    	// Initialization of objects for start
    	start = new Room("Room", "Dark, where the urge to flee is becoming oppressive",null);
    	
    	// Initialization of destination
    	reach = new Room("Corridor", "A long way to an unknown future", null); 
    	
    	//Initialization of keys (for Exitwithkey)
    	k = new Key("Key",12345);
    	falsek = new Key("Key",54321);


    	// TODO : Change the lvlWater from the Extinguisher, i put 0 but i don't know it it's the good way
    	extinct = new Extinguisher("Extinct", 0);
    	Item Default = new Extinguisher("Toto", 0);
    	
    
    	// Initialization of exit
        exit = new Exit(start, reach, true, "Door");
        lockexit = new Exitwithlock(start, reach, true, "Wooden door");
        keyexit = new Exitwithkey(start, reach, false, "Iron Grid", k);
        objectexit = new Exitwithobject(start, reach, false, "Wall of fire", Default);
        
        
        
    }

    @Test
    void testchangePlace() throws PlaceException 
    {
    	exit.open();
    	assertEquals(exit.nextPlace(), reach);	
    }

    @Test
    void testopenExit() {
    	assertFalse(exit.isopen());
    	exit.close();
    	assertFalse(exit.isopen());
    	exit.open();
    	assertTrue(exit.isopen());
    }

    @Test
    void testunlockAndOpen() 
    {
    	lockexit.unlock();
    	lockexit.open();
    	assertTrue(lockexit.isopen());
    	assertFalse(lockexit.islock());
    }
    
    @Test
    void testdefaultValueExitwithlock() 
    {
    	assertFalse(lockexit.isopen());
    	assertTrue(lockexit.islock());
    }
    
    @Test
    void testpenetratingWhenLock() 
    {
    	lockexit.close();
    	assertTrue(lockexit.islock());
    	lockexit.open();
    	assertFalse(lockexit.isopen());
    	assertTrue(lockexit.islock());
    }
    
    @Test
    void testgoingThroughWhenLock()
    {
    	assertThrows(PlaceException.class, () -> {
    		lockexit.nextPlace();
    	});
    }
    
    @Test
    void testunlockButNotOpen()
    {
    	lockexit.unlock();
    	assertFalse(lockexit.isopen());
    	assertFalse(lockexit.islock());
    }
    
    
    @Test
    void testopenWithGoodKey() 
    {
    	keyexit.unlock(k);
    	assertFalse(keyexit.islock());
    	keyexit.open();
    	assertTrue(keyexit.isopen());
    	;
    }
    
    @Test
    void testopenWithFalseKey() 
    {
    	keyexit.unlock(falsek);
    	keyexit.open();
    	assertFalse(keyexit.isopen());
    }
    
    @Test
    void testtryToUnlockWithoutKey()
    {
    	keyexit.unlock();
    	keyexit.open();
    	assertTrue(keyexit.islock());
    	assertFalse(keyexit.isopen());
    }
    
    @Test
    void testOpenWithItem()
    {
    	objectexit.unlock(k);
    	keyexit.open();
    	assertFalse(keyexit.isopen());
    	
    	objectexit.unlock(extinct);
    	assertFalse(objectexit.islock());
    	objectexit.open();
    	assertTrue(objectexit.isopen());

    }
    
 
}
