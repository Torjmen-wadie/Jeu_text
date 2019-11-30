import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import exceptions.ExitPlaceException;
import exceptions.PlaceException;

public class PlaceTest {

	
	Room start;
	Room nextroom;
	Room end;
	
	Key key;
	Chest chest;
	
	List<Place> unlink_map; // A unlink map is a set of Place, without any exit
	List<Place> map; // A map is a linked set of Place
	
	
	/* 
	 * Initialization of exit
	 * Chained world where every place are linked by only one exit (for a test)
	 * 
	 * Graph :
	 * Start === Nextroom --- End
	*/
	
	Pair [][] mapMatrix = 
		{
			{new Pair("",0), new Pair("Door",1), new Pair("",0)},
			{new Pair("Door",1), new Pair("",0), new Pair("Wooden Door",1)},
			{new Pair("",0), new Pair("",0), new Pair("",0)}
		};
	
    @BeforeEach
    public void setUp() {
    	
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
    	List<Item> containsexit = new ArrayList<Item>();
    	//----------------------------------------
    	
    	
    	// 2 : Initialization of place
    	start = new Room("Start", "Begin of chain",containstart);
    	nextroom = new Room("Next Room", "Middle of chain",containsnextroom);
    	end = new Room("End", "On way end", containsexit);
    	
    	this.unlink_map = new ArrayList<Place>();
    	this.unlink_map.addAll(Arrays.asList(start,nextroom,end));
    	//----------------------------------------
    
    	Mapgenerator m = new Mapgenerator(this.mapMatrix, this.unlink_map);
		m.create();
		this.map = m.getMap();
		
		this.map.get(2).describeExit();
    }
    
    @Test
    public void testDescription() {
    	String txt = start.describePlace();
    	assertFalse(txt.isEmpty());
    	assertNotEquals("", txt);
    }
    
    @Test
    public void testNotEmptyRoom() {
    	assertFalse(start.GetPortableItemRoom().isEmpty());
    	assertEquals(start.GetPortableItemRoom().get(0),key);
    	
    	assertFalse(start.GetOpenableItemRoom().isEmpty());
    	assertEquals(start.GetOpenableItemRoom().get(0),chest);
    }
    
    @Test
    public void testChangePlace() throws ExitPlaceException, PlaceException
    {
    	assertEquals(start.select("Door").nextPlace(),nextroom); 
    }
    
    @Test
    public void testSelectExit() throws ExitPlaceException, PlaceException 
    {
    	Exit selected = this.map.get(0).select("Door");
    	assertEquals(selected.nextPlace(),nextroom);
    	assertEquals(selected.previousPlace(),start);
    	assertEquals(selected.getName(), "Door");
    	assertThrows(ExitPlaceException.class, () -> {
    		this.map.get(0).select("Grid");
    	});
    }

    @Test
    public void testTwoWay() throws PlaceException, ExitPlaceException 
    {
    	
    	Place changePlace = this.map.get(0).select("Door").nextPlace();
    	assertEquals(changePlace,nextroom);
    	Place goback = changePlace.select("Door").nextPlace();
    	assertEquals(goback, start);
    	
    	
    }
     
    
    @Test
    public void testOneWay() throws PlaceException, ExitPlaceException
    {
    	Place changePlace = this.map.get(1).select("Wooden Door").nextPlace();
    	assertEquals(changePlace,end);
    	assertThrows(ExitPlaceException.class, () -> {
    		changePlace.select("Wooden Door");
    	});
    }
}
