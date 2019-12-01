import exceptions.ContainerCarryException;

import java.util.*;
public class Container extends Item {
    private boolean saw;

    private List <Item> items ;

    public Container(String nom, List<Item> items) {
        super(nom);
        saw = false;
       this.items = items;
    }

    @Override
    public Item take() throws ContainerCarryException {

        //if try to take a container object, throw ContainerCarryException
        if (!saw){
            //System.out.println("That looks too hard to carry.");
            throw new ContainerCarryException();
        }

        return inside.take();

    }

    //TODO : mirar como es que hago para mostrar los elementos del interior
    @Override
    public void look() {
        if (!saw){
            super.look();
            alreadySaw();
        }else{
            super.look();
            inside.look();
        }

    }

    public void alreadySaw(){
        saw = true;
    }
}
