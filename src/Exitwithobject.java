

public class Exitwithobject extends Exitwithlock{
	private final Item UNLOCK_OBJECT;
	
	public Exitwithobject(Place to, Place from, boolean swap, String name, Item unlocker)
    {
		super(to, from, swap, name);
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