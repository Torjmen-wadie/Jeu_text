public class Exitwithkey extends Exitwithlock{
    private final int KEY; 
    
    public Exitwithkey(Place from, Place to, String name , Key k)
    {
        super(from, to, name);
        this.KEY = k.getCode();
    }
    

    public void unlock(Key k)
    {
        if (check(k)) {
            super.unlock();
        }else{
            System.out.println("It's not the right Key");
        }
    }
    
    @Override
    public void unlock(){}
    
    public boolean check(Key k)
    {
    	return this.KEY == k.getCode();
    }
    
    public int getKEY()
    {
    	return KEY;
    }
    
}