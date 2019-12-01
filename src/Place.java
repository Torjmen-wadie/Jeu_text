import java.util.HashMap;
import java.util.Map;

import exceptions.ExitPlaceException;
/**
 * 
 * 
 * Abstract class of place.
 * 
 * A place is modeling of playground.
 * 
 * A place contain also some Exit, which permit to go through a another place.
 * 
 * @author Julien Hayrault
 */
public abstract class Place {
	private final String name;
	private final String description;
	private  Map<String, Exit> MapExit;
	/**
	 * Constructor
	 * @param n
	 *     name of place (Example : Room, Forest)
	 * @param d
	 *     description
	 *     
	 * @see Exit
	 */
    public Place(String n, String d) {
    	
		this.MapExit =new HashMap< String,Exit>();
		this.name = n;
		this.description = d;
    }
  /**
   * Add a Exit to a Place 
   * 
   * 
   * @param e
   * 	Exit to be added in a place
   * 
   */
    public abstract void addExit(Exit e);
/**
 * @param exit
 *     A name of exit to be select (Command LOOK)
 * @return
 * 	   A exit selected
 *      
 * @throws ExitPlaceException
 * 	   A non-identified exit
 *
 */
    public abstract Exit select(String exit) throws ExitPlaceException;
/**
 * Descriptor of a Place    
 * @return
 * 		String that describe the Place
 */
    public abstract String describePlace();
/**
 * Descriptor of exit in a Place
 * @return
 * 		String that describe the list of Exit in Place
 */
    public abstract String describeExit();
/**
 * Getter of description  
 * @return
 * 		Description of Place
 */
	public String getDescription() 
	{
		return this.description;
	}
/**
 * Getter of name
 * @return
 * 		Name of place
 */
	public String getName()
	{
		return this.name;
	}
/**
 * Getter of Exit, Map related String representation with Exit object
 * @return
 * 		Map related a String and a Exit
 */
	public Map<String, Exit> getMapExit()
	{
		return this.MapExit;
	}
    
}