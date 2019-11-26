

public class Exitwithkey extends Exitwithlock{
    private final int KEY; 
    
    public Exitwithkey(Place to, Place from, Key k)
    {
        super(to, from);
        this.KEY = k.getId();
    }
    
    @Override
    public void unlock(){}
    
    public void unlock(Key k)
    {
        if (checkKey(k)) {
            super.unlock();
        } 
    }
    
    public boolean checkKey(Key k)
    {
    	return this.KEY == k.getId();
    }
    
    
}