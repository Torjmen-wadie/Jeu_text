import java.util.List;
import java.util.Map;

import exceptions.ExitPlaceException;

public class Floor extends Place {
	
	public Floor(String name, String description, Map<String, Exit> neighbour, List<Object> contains) 
	{
		super(name, description, neighbour, contains);
	}
	
	@Override
    public void describe() {
    	System.out.println(this.getName() + " is " + super.getDescription());
    }
	
	
	@Override
    public Exit select(String exit) throws ExitPlaceException{
    	
    	if (this.getHave().containsKey(exit)) 
    	{
    		return this.getHave().get(exit);
    	}
    	else {
    		throw new ExitPlaceException();
    	}
    }

}
