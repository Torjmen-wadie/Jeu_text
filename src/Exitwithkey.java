public class Exitwithkey extends Exitwithlock{
    private final int KEY; 
    
    public Exitwithkey(Place to, Place from, Key k)
    {
        super(to, from);
        this.KEY = k.getId();
    }
    
    
    
    public void unlock(Key k)
    {
        if (check(k)) {
            super.unlock();
        } 
    }
    
    @Override
    public void unlock(){}
    
    public boolean check(Key k)
    {
    	return this.KEY == k.getId();
    }
    
    
}