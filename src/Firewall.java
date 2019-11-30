
public class Firewall extends Exitwithobject
{
	private static final String DEFAULT_NAME = "Fire";
	private static final Item UNLOCK_OBJECT = new Extinguisher("Extinguisher",0);

	public Firewall(Place to, Place from) {
		super(to, from, DEFAULT_NAME, UNLOCK_OBJECT);
	}

}
