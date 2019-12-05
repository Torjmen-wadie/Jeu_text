import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import exceptions.PlaceException;

/* 
 * Testing 
 * Testing with Junit Jupiter Test Library (See import org.junit.jupiter.api)
 * Runs 11/11 	Errors : 0 		Failures : 0 
 * This class Testing some behavior of Exit.
*/

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
    public void setUp() {
    	// Initialization of objects for start
    	start = new Room("Room", "Dark, where the urge to flee is becoming oppressive",null);
    	
    	// Initialization of destination
    	reach = new Room("Corridor", "A long way to an unknown future", null); 
    	
    	//Initialization of keys (for Exitwithkey)
    	k = new Key("Key",12345);
    	falsek = new Key("Key",54321);


    	extinct = new Extinguisher("Extinct", 0);
    	Item Default = new Extinguisher("Toto", 0);
    	
    
    	// Initialization of exit
        exit = new Exit(start, reach, "Door",true);
        lockexit = new Exitwithlock(start, reach, "Wooden door",true);
        keyexit = new Exitwithkey(start, reach, "Iron Grid", true,k);
        objectexit = new Exitwithobject(start, reach, "Wall of fire", true,Default);
        
        
        
    }

    @Test
    public void testchangePlace() throws PlaceException 
    {
    	exit.open();
    	assertEquals(exit.nextPlace(), reach);	
    }

    @Test
    public void testopenExit() {
    	assertFalse(exit.isopen());
    	exit.close();
    	assertFalse(exit.isopen());
    	exit.open();
    	assertTrue(exit.isopen());
    }

    @Test
    public void testunlockAndOpen() 
    {
    	lockexit.unlock();
    	lockexit.open();
    	assertTrue(lockexit.isopen());
    	assertFalse(lockexit.islock());
    }
    
    @Test
    public void testdefaultValueExitwithlock() 
    {
    	assertFalse(lockexit.isopen());
    	assertTrue(lockexit.islock());
    }
    
    @Test
    public void testpenetratingWhenLock() 
    {
    	lockexit.close();
    	assertTrue(lockexit.islock());
    	lockexit.open();
    	assertFalse(lockexit.isopen());
    	assertTrue(lockexit.islock());
    }
    
    @Test
    public void testgoingThroughWhenLock()
    {
    	assertThrows(PlaceException.class, () -> {
    		lockexit.nextPlace();
    	});
    }
    
    @Test
    public void testunlockButNotOpen()
    {
    	lockexit.unlock();
    	assertFalse(lockexit.isopen());
    	assertFalse(lockexit.islock());
    }
    
    
    @Test
    public void testopenWithGoodKey() 
    {
    	keyexit.unlock(k);
    	assertFalse(keyexit.islock());
    	keyexit.open();
    	assertTrue(keyexit.isopen());
    	;
    }
    
    @Test
    public void testopenWithFalseKey() 
    {
    	keyexit.unlock(falsek);
    	keyexit.open();
    	assertFalse(keyexit.isopen());
    }
    
    @Test
    public void testtryToUnlockWithoutKey()
    {
    	keyexit.unlock();
    	keyexit.open();
    	assertTrue(keyexit.islock());
    	assertFalse(keyexit.isopen());
    }
    
    @Test
    public void testOpenWithItem()
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
