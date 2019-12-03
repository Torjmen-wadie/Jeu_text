import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
/**
 * 
 * 
 * 
 * <b> Generation of map game </b>
 * 
 * Based on the adjacency matrix for oriented graph principle's  
 * 
 * Example of matrix :
 *-------------------------------------
 * 			Room 1    Room 2    Room 3
 * Room 1     0         1         2
 * 
 * Room 2     1         0         0
 * 
 * Room 3     0         0         0
 *
 * 
 * Simply describe a graph:
 *-------------------------------------
 * 		Room 1  =======  Room 2
 *  	  |
 *		  |  
 *		  |
 *	    Room 3
 * 
 * <i>A single line is one-way, two line is two-way Exit</i>
 * ------------------------------------
 * 
 * Nodes are Place, initialized before.
 * This class have method that return a Map contain initialized Place with their Exit
 * 
 * @author Julien Hayrault
 */


public class Mapgenerator {

	private Pair [][] adjmatrix;
	private List<Place> nodes;
	private List<Place> map;
	private ArrayList<Integer> unique;
	private int currentunique; 
	
	/**
	 * <b>Constructor</b>
	 * 
	 * @param matrix
	 *     Two-dimensional matrix that represent the map of game
	 *     These arrays contain Pair, a Pair contain a String and a value.
	 *     String is a name of Exit, value is type of Exit.
	 * @param node
	 * 	   A list that contain the label of node, represented by a Place
	 * 
	 * @see Pair
	 * @see Place
	 * @see Exit
	 */
//-------------------------------------------------------------------------	
	public Mapgenerator(Pair [][] matrix, List<Place> node) 
	{
		this.nodes = node;
		this.adjmatrix = matrix;
		this.unique = new ArrayList<Integer>();
		for (int id = 100; id<=999; id++) 
		{
			this.unique.add(id); //Generate a list of unique 3 numbers key
		}
		this.map  = node;
		this.newCurrentUnique();
	}
	/**
	 * Converted matrix in relationship between Exit and Place. 
	 */
//-------------------------------------------------------------------------	
	public void create() 
	{
		/* 
		 * Process of the two-dimensional matrix : O(n^2).
		 * Maybe if consider that a matrix is symmetrical, we can reduce time of execution
		 */
		int fixed_size = this.nodes.size();
		
		for (int i = 0; i < fixed_size; i++)
		{
			for (int j = 0; j < fixed_size; j++) 
			{
				if (!(this.adjmatrix[i][j].getValue() == 0))
				{	
					if (this.adjmatrix[i][j].equals(this.adjmatrix[j][i])) 
					{
						this.map.set(i, initExit(this.nodes.get(i),this.nodes.get(j), this.adjmatrix[i][j]));
						this.map.set(j, initExit(this.nodes.get(j),this.nodes.get(i), this.adjmatrix[i][j]));
						if (this.adjmatrix[i][j].getValue() == 2)
						{
							this.newCurrentUnique();
						}
						this.adjmatrix[i][j] = new Pair("", 0);
						this.adjmatrix[j][i] = new Pair("", 0);
					}
					else
					{
						this.map.set(i, initExit(this.nodes.get(i),this.nodes.get(j), this.adjmatrix[i][j]));
					}
				}
			}
		}
	}
	/**
	 * Out a unique random id between 10000 and 99999.
	 * 
	 * @return
	 * 		Unique random id.
	 */
//-------------------------------------------------------------------------	
	
	

	private int randomizeUnique() 
	{
		 //Get the unique randomize value for key (or code, or another things)
		int rdmvalue = 0;
		if (!(this.unique.isEmpty())) {
			Collections.shuffle(this.unique);
			rdmvalue = this.unique.get(0);
			this.unique.remove(0);
		}
		return rdmvalue;
	}
	
	private void newCurrentUnique() 
	{
		this.currentunique = randomizeUnique();
	}
	
	
	/**
	 * Initialize a Exit and add this Exit to a Place.
	 * 
	 * Label for a edge :
	 * - 1 : A simple Exit
	 * - 2 : A Exit with lock
	 * - 3 : A fire (need Extinguisher to cross)
	 * - TODO : add another labels
	 * 
	 * @param from
	 * 		The original Place from where we start.
	 * @param to
	 * 		The Place where we are going.
	 * @param link
	 * 		The name and the type of Exit who will be created.
	 * @return
	 * 		The Place, initialized with Exit
	 */
//-------------------------------------------------------------------------	
	private Place initExit(Place from, Place to , Pair link) 
	{
		switch (link.getValue()) 
		{
		case 1: // Add to a room a simple exit
			from.addExit(new Exit(from, to, link.getKey()));		
			break;
		case 2: // Add to a room a exit with lock, and put a key in a room
			Key passkey = new Key("Key",this.currentunique);
			Room r = (Room) from;
			r.addItem(passkey);
			from.addExit(new Exitwithkey(from, to, link.getKey(),passkey));
			break;
		case 3: //Add to a room a fire wall, unlock with a Extinguisher
			from.addExit(new Firewall(from, to));
			break;
		}
		return from;
	}
/**
 * Getter of game map
 * @return
 * 		The list of Place, linked between us
 */
//-------------------------------------------------------------------------	
	public List<Place> getMap() 
	{	
		return this.map;
	}
}
