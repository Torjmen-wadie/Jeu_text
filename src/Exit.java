import exceptions.PlaceException;

public class Exit implements Openable {
    /* This class has permit from a exit to go through an another place */
	
    private boolean open;
    private boolean swap;
    
    private String name;
    private Place from;
    private Place to;
    
    
    
    public Exit(Place from, Place to, boolean swap, String name) {
        this.open = false;
        this.swap = swap;
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
    
    private void swap() 
    {
    	Place tmp = this.to;
    	this.to = this.from;
    	this.from = tmp;
    }
    
    public Place nextPlace() throws PlaceException
    {
    	Place nextplace = this.to;
    	if (this.swap) 
    	{
    		this.swap();
    		nextplace.addExit(this);
    	}
    	return nextplace;	
    }
    
    
    
    
}