import java.util.List;

import exceptions.ExitPlaceException;

public abstract class Place {
	private String name;
	private String description;
	 
    public Place(String n, String d) {
		
		this.name = n;
		this.description = d;
    }
   

    public abstract String describePlace();
    public abstract String describeExit();
    public abstract Exit select(String exit) throws ExitPlaceException;

	public String getDescription() {
		return this.description;
	}

	public String getName() {
		return this.name;
	}


	public abstract List<Portable> GetPortableItemRoom();
	public abstract List<Openable>  GetOpenableItemRoom();


	public abstract void addExit(String string, Exit exitstart);
	
    
}