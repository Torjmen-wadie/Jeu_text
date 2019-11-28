import exceptions.NotRightKey;

public class Telephone extends Item  implements Portable, Usable{
    private String statut ;
    public Telephone(String statut,String name)
    {
        super(name);
        this.statut=statut;
    }

    @Override
    public void look(){
        System.out.println(statut);
    }

    @Override
    public String toString() {
        return "Telephone{" +
                "statut='" + statut + '\'' +
                '}';
    }

    @Override
    public void addToInventory() {

    }

    @Override
    public void use(Object obj) throws NotRightKey {
        //maybe use a system to tell the user what to do each time he uses the phone?
    }
}
