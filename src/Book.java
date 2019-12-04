import exceptions.ContainerCarryException;

public class Book extends Item implements Portable{
    private String text ;
    
    public Book(String text,String name)
    {
        super(name);
        this.text=text;
    }

    @Override
    public void look()
    {
        System.out.println(text);
    }

    @Override
    public void addToInventory() {

    }

}