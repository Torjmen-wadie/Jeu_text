import exceptions.NotRightKey;

import java.util.List;

public class LockedChest extends Chest {
    int code;

    public LockedChest(String nom, List<Item> items, int code) {
        super(nom, items);
        this.code = code;
    }

    @Override
    public void open() {
        //this fonction must have empty body
        System.out.println("The chest is closed by Key");;
    }

    public void open(int code) throws NotRightKey {
        if (code != this.code){
            throw new NotRightKey();
        }

        super.open();

    }
}
