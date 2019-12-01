import exceptions.PlaceException;

/**
 * 
 * 
 * Class of Exit
 *
 * A Exit is symbolic relation between two Place.
 * 
 * A Exit permit to the player to go to a another Place.
 * 
 * A Exit can be open and close. 
 * 
 * @author Julien Hayrault
 * 
 * 
 */

public class Exit implements Openable {
	
    private boolean open;
    
    private String name;
    private Place from;
    private Place to;
    
    /**
     * A Exit is a arc.
     * By default, arc is not visited (Exit is close).
     *  
     * @param from
     * 		The Place from we start.
     * @param to
     *      The Place where we arrive.
     * @param name
     *      The name of a Exit (Example : Door).
     *      
     * @see Place
     * @see Openable
     */
//-------------------------------------------------------------------------	
    public Exit(Place from, Place to, String name) 
    {
        this.open = false;
        this.name = name;
        this.from = from;
        this.to = to;  
    }
    /**
     * A Exit is visited (Exit is open)
     */
//-------------------------------------------------------------------------	
    public void open() 
    {
        this.open = true;
    }
    /**
     * Close a Exit
     */
//-------------------------------------------------------------------------	
    public void close() 
    {
        this.open = false;
    }
    /**
     * Getter of open.
     * 
     * @return
     */
//-------------------------------------------------------------------------	
    public boolean isopen() {
        return this.open;
    }
    /**
     * Getter of name.
     * 
     * @return
     */
//-------------------------------------------------------------------------	    
    public String getName() {
    	return this.name;
    }
    /**
     * Permit to go to the destination.
     * @return
     * @throws PlaceException
     */
//-------------------------------------------------------------------------	
    public Place nextPlace() throws PlaceException
    {
    	this.open();
    	return this.to;	
    }
    /**
     * Return from where we come from.
     * @return
     * @throws PlaceException
     */
//-------------------------------------------------------------------------	
    public Place previousPlace() throws PlaceException
    {
    	if (isopen()) 
    	{
    		return this.from;
    	}
    	else
    	{
    		throw new PlaceException();
    	}
    }
}