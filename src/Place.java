import java.util.List;
import java.util.Map;

import exceptions.ExitPlaceException;

public abstract class Place {
	private String name;
	private String description;
	private List<Object> contains;
    private  Map<String, Exit> have; 
    
    
    public Place(String n, String d, Map<String, Exit> have, List<Object> contains) {
		this.have = have;
		this.name = n;
		this.description = d;
		this.contains = contains;
	}
    
	public abstract void describe();
    public abstract Exit select(String exit) throws ExitPlaceException;

	public String getDescription() {
		return description;
	}

	public String getName() {
		return name;
	}

	public Map<String, Exit> getHave() {
		return have;
	}
	
	public List<Object> getContains() {
		return this.contains;
	}
	
    
}