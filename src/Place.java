import java.util.HashMap;
import java.util.Map;

import exceptions.ExitPlaceException;

public abstract class Place {
	private final String name;
	private final String description;
	private  Map<String, Exit> MapExit;
	 
    public Place(String n, String d) {
    	
		this.MapExit =new HashMap< String,Exit>();
		this.name = n;
		this.description = d;
    }

    
    
    public abstract void addExit(Exit e);
    public abstract Exit select(String exit) throws ExitPlaceException;
    
    public abstract String describePlace();
    public abstract String describeExit();
    
    
	public String getDescription() 
	{
		return this.description;
	}

	public String getName()
	{
		return this.name;
	}
	
	public Map<String, Exit> getMapExit()
	{
		return this.MapExit;
	}
    
}