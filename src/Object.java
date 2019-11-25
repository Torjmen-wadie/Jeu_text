import exceptions.ContainerCarryException;

public class Object {
    private String nom;

    public Object(String nom) {
        this.nom = nom;
    }

    public Object take() throws ContainerCarryException {
        return this;
    }

    public void look() {
        System.out.println(nom);
    }

}
