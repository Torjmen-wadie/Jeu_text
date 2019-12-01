import exceptions.ContainerCarryException;

import java.util.List;

public class Chest extends Container implements Openable {
    private boolean opened;

    public Chest(String nom, List<Item> items) {
        super(nom, items);
        this.opened = false;
    }

    @Override
    public Item take() throws ContainerCarryException {
        if (!opened){
            throw new ContainerCarryException("Why am i thinking about carrying something this big and CLOSED???");
        }

        return super.take();
    }


    @Override
    public void open() {
        System.out.println("The chest was opened");
        opened = true;
        //super.alreadySaw();
        super.look();
    }

    @Override
    public void close() {
        opened = false;
    }
}
