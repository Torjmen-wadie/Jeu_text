package exceptions;

public class ContainerCarryException extends Exception {

    public ContainerCarryException() {
        super("That looks too hard to carry. \nI think I'd better keep looking");
    }

    public ContainerCarryException(String s) {
        System.out.println(s);
    }
}
