import exceptions.ContainerCarryException;

public class Object {
    private String name;

    public Object(String name) {
        this.name = name;
    }

    public Object take() throws ContainerCarryException {
        return this;
    }

    public void look() {
        System.out.println(name);
    }

}
