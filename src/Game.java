import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Game {
    //private List<Commande> commande = new ArrayList<Commande> ();
    public void waitingForCommands(){
        Scanner scanner = new Scanner(System.in);

        String commande = scanner.nextLine();
        String[] parts = commande.split(" ");

        if (confirmCommande(parts[0])){
            execCommande(commande);
        }
    }
    // TODO: Creat this method
    private void execCommande(String commande) {
        String[] parts = commande.split(" ");
        switch (Commande.valueOf(parts[0])){
            case GO:
                break;
            case USE:
                break;

            case HELP:
                break;

            case LOOK:
                break;

            case QUIT:
                break;

            case TAKE:
                break;
        }
    }

    public boolean confirmCommande(String commande){

        boolean flag = false;
        for (Commande c : Commande.values()){

            if (c.toString().equalsIgnoreCase(commande.trim())){
                flag = true;
            }

        }
        return flag;
    }
}
