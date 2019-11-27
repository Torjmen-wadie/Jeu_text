import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Game {
    private Player player;
    private Place place;

    public Game() {
        player = new Player("Player");
    }

    public void waitingForCommands(){
        Scanner scanner = new Scanner(System.in);

        String commande = scanner.nextLine();
        String[] parts = commande.split(" ");

        if (confirmCommande(parts[0])){
            execCommande(commande);
        }else{
            System.out.println("I don't know what i'm doing... \n Maybe I'll try with another action");
        }
    }
    // TODO: Creat this method
    private void execCommande(String commande) {
        String[] parts = commande.split(" ");
        switch (Commande.valueOf(parts[0].toUpperCase())){
            case GO: go(commande);
                break;
            case USE: use(commande);
                break;

            case HELP:help();
                break;

            case LOOK:look(commande);
                break;

            case QUIT:quit(commande);
                break;

            case TAKE:take(commande);
                break;
        }
    }

    private void take(String commande) {

    }

    private void quit(String commande) {

    }

    private void look(String commande) {
        if(commande.split(" ").length == 1){
            //Look at place

        }else{
            //look at object in place

        }
    }

    public void help() {
        System.out.println("Commandes disponibles :");
        Arrays.asList(Commande.values()).forEach(System.out::println);
    }

    private void use(String commande) {
        String args[] = commande.split(" ");
        if (player.hasUsableObject()){

            List<Usable> usableObjects = player.getUsableObjects();

            switch (args[1].toUpperCase()){
                case "KEY" :
                case "EXTINGUISHER":
                case "TELEPHONE":
                    player.useObject(args[1], place);
                    break;
            }
        }else{
            System.out.println("I don't know how to use this...");
        }
    }

    private void go(String commande) {

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
