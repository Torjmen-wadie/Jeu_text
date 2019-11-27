import exceptions.PlaceException;

public class Exitwithlock extends Exit{
    private boolean lock;
    
    public Exitwithlock(Place to , Place from) {
        super(from, to);
        this.lock = true;
    }
    
    public void unlock()
    {  
      this.lock = false;
    }
    
    public void lock()
    {
        if (!(this.isopen()))
        {
            this.lock = true;
        }
    }
    
    public boolean islock()
    {
        return this.lock;
    }
    
    @Override
    public void open() 
    {
        if (!(this.lock))
        {
            super.open();
        }
    }
    
    @Override
    public void close()
    {
        if (!(this.lock))
        {
            super.close();
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