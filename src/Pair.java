
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
	
	@Override
	public boolean equals(Object o) {
		Pair p = (Pair) o;
		if (!(o instanceof Pair))
		{
			return false;
		}
		return this.name == p.getKey() && this.value == p.getValue();
	}

}
