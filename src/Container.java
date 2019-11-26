import exceptions.ContainerCarryException;

public class Container extends Object {
    private boolean saw;

    private Object inside;

    public Container(String nom, String nomInside) {
        super(nom);
        saw = false;
        inside = new Object(nomInside);
    }

    @Override
    public Object take() throws ContainerCarryException {

        //if try to take a container object, throw ContainerCarryException
        if (!saw){
            //System.out.println("That looks too hard to carry.");
            throw new ContainerCarryException();
        }

        return inside.take();

    }

    @Override
    public void look() {
        if (!saw){
            super.look();
        }else{
            super.look();
            inside.look();
        }

    }

    public void alreadySaw(){
        saw = true;
    }
}
