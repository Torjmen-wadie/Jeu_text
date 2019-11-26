public class Key extends Object implements Portable {
    private final int Code;

    public Key(String nom,  int secret) {
        super(nom);
        this.Code = secret;
        
    }
    
    public int getId() {
    	return this.Code;
    }
}
