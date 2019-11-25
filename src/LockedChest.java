import exceptions.NotRightKey;

public class LockedChest extends Chest {
    int code;

    public LockedChest(String nom, String nomInside, int code) {
        super(nom, nomInside);
        this.code = code;
    }


    @Override
    public void open() {
        //this fonction must have empty body
    }

    public void open(int code) throws NotRightKey {
        if (code != this.code){
            throw new NotRightKey();
        }

        super.open();

    }
}
