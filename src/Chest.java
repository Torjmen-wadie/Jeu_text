import exceptions.ContainerCarryException;

import java.util.List;

public class Chest extends Container implements Openable {
    private boolean opened;

    public Chest(String nom, List<Item> items) {
        super(nom, items);
        this.opened = false;
    }

    @Override
    public void look() {
        if (opened){
            super.look();
        }else{
            System.out.println(Message.descChestLook);
        }
    }

    @Override
    public void open() {
        System.out.println(Message.descChestOpen);
        opened = true;
        super.look();
    }

    @Override
    public void close() {
        opened = false;
    }
}
