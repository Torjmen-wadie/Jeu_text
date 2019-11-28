import exceptions.ContainerCarryException;

public class Letter extends Item implements Portable {
    private String message ;
    public Letter (String message,String name)
    {
        super(name);
        this.message=message;
    }

    @Override
    public void look()
    {
        System.out.println(message);
    }

    @Override
    public void addToInventory() {

    }

}
