public class Telephone extends Object  implements Portable{
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
}
