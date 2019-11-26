import exceptions.ContainerCarryException;

public class Letter extends Object implements Portable {
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

}