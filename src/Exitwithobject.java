

public class Exitwithobject extends Exitwithlock{
	private final Item UNLOCK_OBJECT;
	
	public Exitwithobject(Place from, Place to, String name, Item unlocker)
    {
		super(from, to, name);
		this.UNLOCK_OBJECT = unlocker;
    }
	
	
	public void unlock(Item item)
    {
        if (item.getClass().equals(UNLOCK_OBJECT.getClass())){
        	super.unlock();
        }
    }
	
	@Override
    public void unlock(){}

}
