import exceptions.ContainerCarryException;

/**
 *
 * @author Wadie
 */
public class Book extends Item implements Portable, Openable{
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

    @Override
    public void open() {

    }

    @Override
    public void close() {

    }

}