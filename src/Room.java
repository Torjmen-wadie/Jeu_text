import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import exceptions.ExitPlaceException;


public class Room extends Place {
    private List<Item> contains;
    private  Map<String, Exit> have;
   
    public Room(String name, String description, List<Item> contains) 
    {
        super(name, description);
        this.have = new HashMap< String,Exit>();
        this.contains = contains;
    }
    
    
    public Exit select(String exit) throws ExitPlaceException
    {
    	
    	if (this.have.containsKey(exit)) 
    	{
    		return this.have.get(exit);
    	}
    	else {
    		throw new ExitPlaceException();
    	}
    }
    
    public void addExit(String name, Exit e) 
    {
    	this.have.put(name, e);
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
    	if (!(this.have.isEmpty())) {
    		str  = "This place have";
    		for (String k :this.have.keySet()) {
    			str += " - " + k +  "\n";
    		}
    	}
    	return str;
    }
}
    