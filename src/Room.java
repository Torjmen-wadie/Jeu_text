import java.util.ArrayList;
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
    
    public Room(String name, String description)
    {
    	super(name, description);
        this.exits = super.getMapExit();
        this.contains = new ArrayList<Item>();
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
    
    
    public void addItem(Item item) 
    {
    	this.contains.add(item);
    }

    public List<Item> describeItem(String item){
    	List<Item> items = this.contains.stream()
								.filter(obj -> obj.toString().equalsIgnoreCase(item))
								.collect(Collectors.toList());
		List<Item> insideItems = null;
    	if(items.size() > 0){


			items.forEach(Item::look);

			for (Item item1 : items) {
				item1.look();
				if ( item1 instanceof Container){
					List<Item> tmp = ((Container) item1).getItems();
					if (tmp != null){
						insideItems = new ArrayList<>(tmp);
					}
				}
			}
		}else{
			System.out.println(Message.roomDescItem);
		}

		return insideItems;
	}
    
    public String describePlace() {
    	String str = "";
    	if (!(this.contains.isEmpty())) {
    		str = "In this " + super.getName() + " there are : \n";
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

	public void deleteItem(Portable tmp) {
		Item i = (Item) tmp;
		contains.remove(i);
	}

	public void putItem(Item tmp, Container contain)
	{
		contain.addItem(tmp);
	}
	
	public List<Exit> getDoors(){
		return new ArrayList<>(this.getMapExit().values());
	}
}
    