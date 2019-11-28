import exceptions.ContainerCarryException;

public class Item {
    private String name;

    public Item(String name) {
        this.name = name;
    }

    public Item take() throws ContainerCarryException {
        return this;
    }

    public void look() {
        System.out.println(name);
    }

}