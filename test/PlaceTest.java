import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

import java.util.List;
import java.util.stream.Collectors;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import exceptions.ExitPlaceException;
import exceptions.PlaceException;

public class PlaceTest {

	
	Room start;
	Room nextroom;
	Room nextroom2;
	Room end;
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
		containstart.add(new LockedChest("Chest locked","", 12345));
    	
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
    	exitstart = new Exit(start, nextroom, true, "Door");
    	Exit exitnextroom = new Exit(nextroom,nextroom2, false, "Wooden Door");
    	Exit exitnextroom2 = new Exit(nextroom2,end, false, "Grid");
    	
    	
    	// 4 : Add exit to place
    	start.addExit(exitstart);
    	nextroom.addExit(exitnextroom);
    	nextroom2.addExit(exitnextroom2);
    }
    
    @Test
    public void testDescription() {
    	String txt = start.describePlace();
    	assertFalse(txt.isEmpty());
    	assertNotEquals("", txt);
		System.out.println(txt);
    }

	@Test
	void lookObjectsInPlace(){
		System.out.println(start.describePlace());
		start.describeItem("Desk");
	}

    @Test
    public void testNotEmptyRoom() {
    	assertFalse(start.GetPortableItemRoom().isEmpty());
    	assertEquals(start.GetPortableItemRoom().get(0),key);
    	
    	assertFalse(start.GetOpenableItemRoom().isEmpty());
    	assertEquals(start.GetOpenableItemRoom().get(0),chest);
    }

    @Test
    public void testSelectExit() throws ExitPlaceException {
    	assertEquals(start.select("Door"),exitstart);
    	assertThrows(ExitPlaceException.class, () -> {
    		start.select("Grid");
    	});
    }
    
    
    @Test
    public void testChangePlace() throws ExitPlaceException, PlaceException
    {
    	assertEquals(nextroom,start.select("Door").nextPlace()); 
    }
    
    @Test
    public void testTwoWay() throws PlaceException, ExitPlaceException 
    {
    	
    	Place changePlace = start.select("Door").nextPlace();
    	assertEquals(changePlace,nextroom);
    	Place goback = changePlace.select("Door").nextPlace();
    	assertEquals(goback, start);
    	
    	
    }

    @Test
	void deleteItem(){
    	List<Portable> items = start.GetPortableItemRoom();
    	int taille = items.size();
		System.out.println("Portables items = " + taille);
    	Portable tmp = start.GetPortableItemRoom().get(0);
		System.out.println(start.describePlace());;
		start.deleteItem(tmp);
		System.out.println(start.describePlace());;

		assertNotEquals(taille, start.GetPortableItemRoom().size());
		assertNotNull(tmp);
		((Item)tmp).look();
	}
    
    @Test
    public void testOneWay() throws PlaceException, ExitPlaceException
    {
    	Place changePlace = nextroom2.select("Grid").nextPlace();
    	assertEquals(changePlace,end);
    	assertThrows(ExitPlaceException.class, () -> {
    		changePlace.select("Grid");
    	});
    }
    
    @Test
	public void getChestsFromPlace(){
		List<Openable > locked = start.GetOpenableItemRoom().stream()
								.filter( i -> i instanceof Chest)
								.collect(Collectors.toList());

		assertNotNull(locked);
		locked.forEach(System.out::println);
		assertEquals(2, locked.size());
	}
    
}
