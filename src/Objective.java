import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Objective 
{
	
	private List<Task> fulfill;
	private List<Task> todo;
	private Task main;
	
	public class Task
	{
		private String description;
		private Place condition;
		
		public Task(String s, Place c) {
			this.description = s;
			this.condition = c;
		}	
		
		public Place getCondition()
		{
			return this.condition;
		}
	}

	
	public Objective(Place starting, Place ending)
	{
		this.fulfill = new ArrayList<Task>();
		this.todo = new ArrayList<Task>();
		this.main = new Task("Get out of the hotel !", ending);
		this.todo.add(this.main);
	}
	
	private void addfulfill(Task t)
	{
		for (Map.Entry<String, Exit> entry : t.condition.getMapExit().entrySet())
		{
			if(entry.getValue().isopen()) 
			{
				todo.remove(t);
				fulfill.add(t);
			}
		}
	}
	
	private void addtoDo(Task t) 
	{
		todo.add(0,t);
	}
	
	public void addAnotherPlaceObjective(String describe, Place p) 
	{
		addtoDo(new Task(describe, p));
		
	}
	
	public boolean isObjective(Place p) 
	{
		for (Task t : this.todo) 
		{
			return t.condition.equals(p);
		}
		return false;	
	}
	
	
	public void isAccomplished(Place p)
	{
		if (isObjective(p)) 
		{
			for (Task t : this.todo) 
			{
				if (t.condition.equals(p)) {
					addfulfill(t);
					break;
				}
			}
		}
	}
	
	
	public void trigger(Item item, List <Place> gamemap)
	{
		switch (item.toString()) 
    	{
    	case "Java Book":
    		this.addAnotherPlaceObjective("Go to Game room to find key", gamemap.get(8)); //this.gamemap.get(8)
    		break;
    		
    	case "HELP ME":
    		this.addAnotherPlaceObjective("Look in vase and use key", gamemap.get(1)); //this.gamemap.get(1)
    		break;
    	}
		
	}
	
	public boolean isWin()
	{
		return this.todo.isEmpty() || !(this.todo.contains(this.main));
	}
	
	public boolean isLoose(int despair)
	{
		return despair >= 15;
	}
	
	@Override
	public String toString() 
	{
		String str = "\n------------Objective-------------\n\n";
		if (isWin()) 
		{
			str += "\tYOU HAVE WON !!! \n";
		}
		for (Task t : this.todo) 
		{
			str += " - " + t.description + "\n";
		}
		str += "\n------------Achievement-------------\n\n";
		for (Task t : this.fulfill) {
			str += " [X] " + t.description + "\n";
		}
		
		return str;
	}
	
	public static void main(String[] args) {
		Place start = new Room("Start", "Hello");
		Place end = new Room("End", "World");
		Objective o = new Objective(start,end);
		Place error = new Room("Error", "Not a objective");
		Place next = new Room("Next", "__");
		Exit test = new Exit(next, end,"Door");
		test.open();
		next.addExit(test);
		o.addAnotherPlaceObjective("Go into this place", next);
		System.out.println(o.toString());
		System.out.print("\n\n\n");
		o.isAccomplished(error);
		System.out.println(o.toString());
		System.out.print("\n\n\n");
		o.isAccomplished(next);
		System.out.println(o.toString());
		end.addExit(test);
		o.isAccomplished(end);
		System.out.println(o.toString());
		
	}
	
}

