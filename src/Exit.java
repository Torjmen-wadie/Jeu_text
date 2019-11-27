import exceptions.PlaceException;

public class Exit implements Openable {
    /* This class has permit from a exit to go through an another place */
    private boolean open;
    private Place from;
    private Place to;
    
    
    public Exit(Place from, Place to) {
        this.open = false;
        this.to = to;
        this.from = from;
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
    
    public Place nextPlace() throws PlaceException
    {
    	return this.to;	
    }
    
    public Place previousPlace()
    {
    	return this.from;
    }
    
    
    
}