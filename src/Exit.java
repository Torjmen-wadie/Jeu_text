import exceptions.PlaceException;

public class Exit implements Openable {
    /* This class has permit from a exit to go through an another place */
	
    private boolean open;
    
    private String name;
    private Place from;
    private Place to;
    
    
    
    public Exit(Place from, Place to, String name) {
        this.open = false;
        this.name = name;
        this.from = from;
        this.to = to;  
    }
    
    public void open() {
        this.open = true;
    }
    
    public void close() {
        this.open = false;
    }
    
    public boolean isopen() {
        return this.open;
    }
    
    public String getName() {
    	return this.name;
    }
    
    public Place nextPlace() throws PlaceException
    {
    	this.open();
    	return this.to;	
    }
    
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