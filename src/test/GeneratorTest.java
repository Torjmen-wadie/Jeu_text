import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;

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
		assertEquals(m.getMap().get(0), start);
		System.out.println(m.getMap().get(0).getName() + "\n" + m.getMap().get(0).describeExit());
		System.out.println(m.getMap().get(1).getName() + "\n" + m.getMap().get(1).describeExit());
		System.out.println(m.getMap().get(2).getName() + "\n" + m.getMap().get(2).describeExit());
	}
	
	
	

}
