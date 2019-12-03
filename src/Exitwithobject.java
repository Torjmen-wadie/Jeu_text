

public class Exitwithobject extends Exitwithlock{
	private final Item UNLOCK_OBJECT;
	
	public Exitwithobject(Place from, Place to, String name, Item unlocker)
    {
		super(from, to, name);
		this.UNLOCK_OBJECT = unlocker;
    }
	
	
	public void unlock(Item item)
    {
        if (item.getClass().getSimpleName().equals(UNLOCK_OBJECT.getClass().getSimpleName())){
        	super.unlock();
        }
    }
	
	@Override
    public void unlock(){}

}
