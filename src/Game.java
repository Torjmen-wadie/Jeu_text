import exceptions.NotRightKey;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class Game {
    private Player player;
    private Place place;

    public Game() {
        player = new Player("Player");

        // TODO: DELETE THE ITEMS CREATED BELOW
        List<Item> items = new ArrayList<>();
        items.add(new Chest("", ""));
        items.add(new Chest("", ""));
        items.add(new LockedChest("", "",0));
        items.add(new LockedChest("", "",1));
        items.add(new Letter("",""));

        place = new Room("", "", items);
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
        List<Usable> usableObjects = player.getUsableObjects();

        if (usableObjects.size() > 0){
            switch (args[1].toUpperCase()){
                case "KEY" : useKey(usableObjects);
                            break;
                case "EXTINGUISHER":
                case "TELEPHONE":
                    player.useObject(args[1], place);
                    break;
            }
        }else{
            System.out.println("I don't know how to use this...");
        }
    }

    private void useKey(List<Usable> usableObjects) {
        List<Openable> lockedChests = ((Room)place).GetOpenableItemRoom()
                                        .stream()
                                        .filter( i -> i instanceof Chest)
                                        .collect(Collectors.toList());

        List<Usable> keys = usableObjects.stream()
                                         .filter(usable -> usable instanceof Key)
                                            .collect(Collectors.toList());

        if(keys.size() > 0 && lockedChests.size()>0){
            for (Usable key : keys) {
                for (Openable lockedChest : lockedChests) {

                    try {
                        key.use(lockedChest);
                    } catch (NotRightKey notRightKey) {
                        System.out.println(notRightKey.getMessage());
                    }

                }
            }
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
