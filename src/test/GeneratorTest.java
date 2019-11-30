import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


class GeneratorTest {
	
	Pair [][] mapMatrix = 
		{
			{new Pair("",0), new Pair("Door",1), new Pair("Grid",2)},
			{new Pair("Door",1), new Pair("",0), new Pair("",0)},
			{new Pair("",0), new Pair("",0), new Pair("",0)}
		};
	
	List<Place> room; 
	Place start = new Room("Room 1", "Room start");
	
	@BeforeEach
	public void setUp() {
		room = new ArrayList<Place>();
		room.add(this.start);
		room.add(new Room("Room 2", "Room next"));
		room.add(new Room("Room 3", "Room end"));
		
		
		
		
	}
	
	@Test
	public void testgenerate()
	{
		Mapgenerator m = new Mapgenerator(this.mapMatrix, this.room);
		m.create();
		assertEquals(m.getMap().get(0).getName(), "Room 1");
		assertEquals(m.getMap().get(1).getName(), "Room 2");
		assertEquals(m.getMap().get(2).getName(), "Room 3");
	}
	
	@Test
	public void testExitgenerate()
	{
		Mapgenerator m = new Mapgenerator(this.mapMatrix, this.room);
		m.create();
		Set<String> resofkey = new HashSet<String>();
		resofkey.add("Door");
		resofkey.add("Grid");
		assertEquals(m.getMap().get(0).getMapExit().keySet(),resofkey);
	}
	
	
	
	
	
	

}
