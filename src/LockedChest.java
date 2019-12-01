import exceptions.NotRightKey;

import java.util.List;

public class LockedChest extends Chest {
    private int code;
    private boolean unlocked;

    public LockedChest(String nom, List<Item> items, int code) {
        super(nom, items);
        this.code = code;
        unlocked = false;
    }

    @Override
    public void open() {
        if (unlocked){
            super.open();
        }else{
            System.out.println(Message.chestErrorOpen);;
        }
    }

    public void unlock(int code) throws NotRightKey {
        if (code != this.code){
            throw new NotRightKey();
        }

        unlocked = true;
        System.out.println(Message.chestUnlock);
        open();
    }
}
