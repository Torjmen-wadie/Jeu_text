import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Mapgenerator {
	/* 
	 * Generation of map game
	 * Based on the adjacency matrix for oriented graph principle's  
	 * Example of matrix :
	 *---------------------------------------------------------- 
	 * 			Room 1    Room 2    Room 3
	 * Room 1	  0         1         2
	 * 
	 * Room 2     1         0         0
	 * 
	 * Room 3     0         0         0
	 * 
	 * 
	 * Simply describe a graph:
	 * 
	 * Room 1  =======  Room 2
	 *  |
	 *  |  
	 *  |
	 * Room 3
	 * ------------------------------------------------------------ 
	 *  A single line is one-way, two line is two-way 
	 *  
	 * 
	 * Label for a arete :
	 * - 1 : A simple exit
	 * - 2 : A exit with lock
	 * - 3 : A exit unlock with an object
	 * - TODO : add another labels
	 * 
	 * Nodes are Place, initialized before
	 * Map contain initialized Place with their exit
	 * 
	 */ 
	
	private Pair [][] adjmatrix;
	private List<Place> nodes;
	private List<Place> map;
	private ArrayList<Integer> unique;
	
	public Mapgenerator(Pair [][] matrix, List<Place> node) {
		this.nodes = node;
		this.adjmatrix = matrix;
		this.unique = new ArrayList<Integer>();
		/*
		 * Generate a list of unique 5 numbers key
		 */
		for (int id = 10000; id<=99999; id++) 
		{
			this.unique.add(id);
		}
		this.map  = node;
		
	}
	
	public void create() 
	{
		/* 
		 * Process of the two-dimensional matrix : O(n^2).
		 * Maybe if consider that a matrix is symetrical, we can reduce time of execution
		 */
		int fixed_size = this.nodes.size();
		for (int i = 0; i < fixed_size; i++)
		{
			for (int j = 0; j < fixed_size; j++) 
			{
				this.map.set(i, initExit(nodes.get(i),nodes.get(j), this.adjmatrix[i][j]));
			}
		}
	}
	
	private int randomizeUnique() 
	{
		int rdmvalue = 0;
		if (!(this.unique.isEmpty())) {
			Collections.shuffle(this.unique);
			rdmvalue = this.unique.get(0); //Get the unique randomize value for key (or code, or another things)
			this.unique.remove(0);
			
		}
		return rdmvalue;
	}
	
	private Place initExit(Place from, Place to , Pair link) 
	{
		switch (link.getValue()) 
		{
		case 1: // Add to a room a simple exit
			from.addExit(new Exit(from, to, link.getKey()));		
			break;
		case 2: // Add to a room a exit with lock, and put a key in a room
			Key passkey = new Key("Key",this.randomizeUnique());
			from.addExit(new Exitwithkey(from, to, link.getKey(),passkey));
			Room r = (Room) from;
			r.addItem(passkey);
			break;
		case 3: //Add to a room a firewall, unlock with a Extinguisher
			from.addExit(new Firewall(from, to));
			break;
		}
		return from;
	}
	
	public List<Place> getMap() 
	{	
		return this.map;
	}
	
	

}
