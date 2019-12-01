import exceptions.NotRightKey;

public class Key extends Item implements Portable, Usable{

    private int code;

    public Key(String name ,int code) {
        super(name);
        this.code=code;
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
                ((LockedChest) obj).unlock(code);
            }else if(obj.getClass().getSimpleName().equalsIgnoreCase("Chest")){
                ((Chest) obj).open();
            }
        }
    }
}
