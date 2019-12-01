import exceptions.ContainerCarryException;

import java.util.*;

public class Container extends Item {

    private List <Item> items ;

    public Container(String nom, List<Item> items) {
        super(nom);
       this.items = items;
    }

    @Override
    public Item take() throws ContainerCarryException {
        //if try to take a container object, throw ContainerCarryException
        throw new ContainerCarryException();
    }

    //TODO : mirar como es que hago para mostrar los elementos del interior
    @Override
    public void look() {
        if (items != null && items.size() > 0){
            System.out.println("This "+ this.toString() + " contains");
            for (Item item : items) {
                System.out.println("\t- "+item);
            }
        }else{
            System.out.println("There aren't objects inside");
        }
    }

    public List<Item> getItems(){
        List<Item> tmp = this.items;
        this.items = null;
        return tmp;
    }

}
