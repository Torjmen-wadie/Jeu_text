public class Key extends Object implements Portable {
    private int Code;

    public Key(String nom) {
        super(nom);
    }
    
    public int getId() {
    	return this.Code;
    }
}
