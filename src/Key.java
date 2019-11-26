import exceptions.ContainerCarryException;

public class Key extends Object implements Portable, Usable{
    private int code;

    public Key(String name ,int code) {
        super(name);
        this.code=code;
    }

    public void look()
    {
        // TODO : the key contains a code to show to the user or
        // the key contains a code to be compared with a chest
        System.out.println("It's a normal key ")
    }

    @Override
    public void addToInventory() {

    }

    @Override
    public void use() {

    }
}
