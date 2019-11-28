import exceptions.ContainerCarryException;
import exceptions.NotRightKey;

import java.util.ArrayList;

public class Key extends Object implements Portable, Usable{

    private int code;

    public Key(String name ,int code) {
        super(name);
        this.code=code;
    }

    public void look()
    {
        // TODO : the key contains a code to show to the user or
        // the key contains a code to be compared with a chest
        System.out.println(code);
    }
    public int getCode()
    {
        return this.code;
    }

    @Override
    public void addToInventory() {

    }


    @Override
    public void use(Object obj) throws NotRightKey {
        if (obj != null){
            if(obj.getClass().getSimpleName().equalsIgnoreCase("LockedChest")){
                // try to unlock with key
                ((LockedChest) obj).open(code);
            }
        }
    }
}
