import exceptions.ContainerCarryException;

public class Chest extends Container implements Openable {
    private boolean opened;

    public Chest(String nom, String nomInside) {
        super(nom, nomInside);
        opened = false;
    }

    @Override
    public Object take() throws ContainerCarryException {
        if (!opened){
            throw new ContainerCarryException("Why am i thinking about carrying something this big and CLOSED???");
        }

        return super.take();
    }


    @Override
    public void open() {
        opened = true;
        super.alreadySaw();
        super.look();
    }

    @Override
    public void close() {
        opened = false;
    }
}
