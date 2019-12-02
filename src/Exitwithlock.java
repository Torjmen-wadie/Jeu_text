import exceptions.PlaceException;
public class Exitwithlock extends Exit{
    private boolean lock;
    
    public Exitwithlock(Place to , Place from, String name) {
        super(from, to, name);
        this.lock = true;
    }
    
    public void unlock()
    {
        System.out.println("The door have been unlocked");
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
        }else{
            System.out.println("It's locked");
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
    
    @Override
    public Place previousPlace() throws PlaceException
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