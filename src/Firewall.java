
public class Firewall extends Exitwithobject
{
	private static final String DEFAULT_NAME = "Fire";
	private static final Item UNLOCK_OBJECT = new Extinguisher("Extinguisher",0);

	public Firewall(Place to, Place from) {
		super(to, from, DEFAULT_NAME, UNLOCK_OBJECT);
	}
	
	
	
	//TODO : Override Unlock : unlock if the level of Extinguisher is < 0
	@Override
	public void unlock(Item item) {
		if(((Extinguisher)item).getLvlWater() > 0){
			super.unlock(item);
		}else{
			System.out.println("The extinguiser is empty");
		}
	}
}
