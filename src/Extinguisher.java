import exceptions.ContainerCarryException;

public class Extinguisher extends Object implements Portable {
    private int lvlWater;

    public Extinguisher(String name,int lvlWater)
    {
        super(name);
        this.lvlWater=lvlWater;

    }

    @Override
    public void look()
    {
        System.out.println(lvlWater);
    }
}