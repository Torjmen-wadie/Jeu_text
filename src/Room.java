import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import exceptions.ExitPlaceException;


public class Room extends Place {
    private List<Item> contains;
    private Map<String, Exit> exits;
    
   
    public Room(String name, String description, List<Item> contains) 
    {
        super(name, description);
        this.exits = super.getMapExit();
        this.contains = contains;
        
    }
    
    
    public Exit select(String exit) throws ExitPlaceException
    { 
    	if (this.exits.containsKey(exit)) 
    	{
    		return this.exits.get(exit);
    	}
    	else {
    		throw new ExitPlaceException();
    	}
    }
    
    public void addExit(Exit e) 
    {
    	super.getMapExit().put(e.getName(), e);
    }
    
    public List<Portable> GetPortableItemRoom()
    {
    	return this.contains.stream()
    	.filter(i -> i instanceof Portable)
    	.map(i -> (Portable) i)
    	.collect(Collectors.toList());
    }
    
    public List<Openable> GetOpenableItemRoom()
    {
    	return this.contains.stream()
    	.filter(i -> i instanceof Openable)
    	.map(i -> (Openable) i)
    	.collect(Collectors.toList());
    }
    
    
    public String describePlace() {
    	String str = "";
    	if (!(this.contains.isEmpty())) {
    		str = "In this " + super.getName() + " they are : \n";
        	for(Item c : this.contains) {
        		str += " - " + c.toString() + " \n";
        	}
    	}
    	return str;	 
    }
    
    public String describeExit() {
    	String str = "";
    	if (!(this.exits.isEmpty())) {
    		str  = "This place have \n";
    		for (String k :this.exits.keySet()) {
    			str += " - " + k +  "\n";
    		}
    	}
    	return str;
    }
}
    