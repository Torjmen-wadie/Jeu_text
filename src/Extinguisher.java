import exceptions.ContainerCarryException;
import exceptions.NotRightKey;

public class Extinguisher extends Item implements Portable, Usable {

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

    @Override
    public void addToInventory() {

    }


    @Override
    public void use(Object obj) throws NotRightKey {

    }
}