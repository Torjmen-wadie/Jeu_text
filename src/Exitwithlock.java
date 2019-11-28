import exceptions.PlaceException;
public class Exitwithlock extends Exit{
    private boolean lock;
    
    public Exitwithlock(Place to , Place from, boolean swap, String name) {
        super(from, to, swap, name);
        this.lock = true;
    }
    
    public void unlock()
    {  
      this.lock = false;
    }
    
    private void lock()
    {
    	this.lock = true;
    }
    
    public boolean islock()
    {
        return this.lock;
    }
    
    @Override
    public void open() 
    {
        if (!(this.islock()))
        {
            super.open();
        }
    }
    
    @Override
    public void close()
    {
        if (!(this.islock()))
        {
            super.close();
            this.lock();
        }
    }
    
    @Override
    public Place nextPlace() throws PlaceException
    {
    	if(!(this.islock())) 
    	{
    		return super.nextPlace();
    	}
    	else
    	{
    		throw new PlaceException();
    	}
    } 
}