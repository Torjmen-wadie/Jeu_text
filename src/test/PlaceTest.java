import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

import java.util.List;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import exceptions.ExitPlaceException;
import exceptions.PlaceException;

public class PlaceTest {

	
	Place start;
	Place nextroom;
	Place nextroom2;
	Place end;
	Key key;
	Chest chest;
	Exit exitstart;
	
    @BeforeEach
    public void setUp() {
    	/* 
    	 * Initialization of exit
    	 * Chained world where every place are linked by only one exit
    	*/
    	
    	
    	
    	// 1 : Initialization of objects
    	key = new Key("Key", 12345);
    	chest = new Chest("Desk", "desk");
    	List<Item> containstart = new ArrayList<Item>(); 
    	containstart.add(chest);
    	containstart.add(key);
    	
    	List<Item> containsnextroom = new ArrayList<Item>(); 
    	containsnextroom.add(new Vase("Vase", "vase"));
    	containsnextroom.add(new Key("Key", 54321));
    	
    	//Empty list
    	List<Item> containsnextroom2 = new ArrayList<Item>();
    	List<Item> containsexit = new ArrayList<Item>();
    	//----------------------------------------
    	
    	// 2 : Initialization of place
    	start = new Room("Start Room", "a description",containstart);
    	nextroom = new Room("Next Room", "a description",containsnextroom);
    	nextroom2 = new Room("Next Room", "a description",containsnextroom2);
    	end = new Room("Room 1", "a description 1", containsexit);
    	//----------------------------------------
    	
    	
    	// 3 : Initialization of exit
    	exitstart = new Exit(start,nextroom);
    	Exit exitnextroom = new Exit(nextroom,nextroom2);
    	Exit exitnextroom2 = new Exit(nextroom2,end);
    	
    	
    	// 4 : Add exit to place
    	start.addExit("Door",exitstart);
    	nextroom.addExit("Wood door", exitnextroom);
    	nextroom2.addExit("Double door", exitnextroom2);
    }
    
    @Test
    public void testDescription() {
    	String txt = start.describePlace();
    	System.out.println(txt);
    	assertFalse(txt.isEmpty());
    	assertNotEquals("", txt);
    }
    
    @Test
    public void NotEmptyRoom() {
    	assertFalse(start.GetPortableItemRoom().isEmpty());
    	assertEquals(start.GetPortableItemRoom().get(0),key);
    	
    	assertFalse(start.GetOpenableItemRoom().isEmpty());
    	assertEquals(start.GetOpenableItemRoom().get(0),chest);
    }
    
    @Test
    public void SelectExit() throws ExitPlaceException {
    	assertEquals(start.select("Door"),exitstart);
    	assertThrows(ExitPlaceException.class, () -> {
    		start.select("Grid");
    	});
    }
    
    
    @Test
    public void ChangePlace() throws ExitPlaceException, PlaceException
    {
    	assertEquals(nextroom,start.select("Door").nextPlace()); 
    }
}
