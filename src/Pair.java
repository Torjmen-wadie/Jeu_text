
public class Pair {
	private String name;
	private int value;
	
	public Pair(String n, int v) {
		this.name = n;
		this.value = v;
	}
	
	public String getKey()
	{
		return this.name;
	}
	
	public int getValue()
	{
		return this.value;
	}

}
