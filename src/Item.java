import exceptions.ContainerCarryException;

public class Item {
    private String nom;

    public Item(String nom) {
        this.nom = nom;
    }

    public Item take() throws ContainerCarryException {
        return this;
    }

    public void look() {
        System.out.println(nom);
    }

}
