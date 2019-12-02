import exceptions.ExitPlaceException;
import exceptions.NotRightKey;
import exceptions.PlaceException;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class Game {
    private Player player;
    private Room place;
    public static boolean runGame = true;

    public Game() {
        player = new Player("Player");
        player.addInventor(new Key("KEY", 0));

        // TODO: DELETE THE ITEMS CREATED BELOW
        List<Item> items = new ArrayList<>();
        items.add(new Chest("chest1", new ArrayList<Item>()));
        items.add(new Chest("chest2", new ArrayList<Item>()));
        items.add(new LockedChest("locked1", new ArrayList<Item>(),0));
        items.add(new LockedChest("locked2", new ArrayList<Item>(),1));
        items.add(new Letter("l1","letter1"));
        items.add(new Letter("l2","letter2"));
        items.add(new Letter("l3","letter3"));

        place = new Room("", "", items);
    }

    public void init(){
        while (runGame){
            System.out.println("asddsa");
        }
    }

    public void waitingForCommands(){
        Scanner scanner = new Scanner(System.in);

        String commande = scanner.nextLine();
        String[] parts = commande.split(" ");

        if (confirmCommande(parts[0])){
            execCommande(commande);
        }else{
            System.out.println(Message.gameErrorWait);
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

            case OPEN:open(commande);
                break;
            case THROW:eject(commande);
                break;
        }
    }

    public void eject(String commande) {
        String[] args = commande.split(" ");

        if (args.length > 1){
            Portable tmp = player.deleteUsableObject(args[1]);
            place.addItem((Item) tmp);
        }else {
            System.out.println("What am i supposed to throw?");
        }
    }

    public void open(String commande) {
        String[] args = commande.split(" ");
        int pos = -1;
        List<Openable> tmp = place.GetOpenableItemRoom();
        for (int i = 0; i < tmp.size(); i++) {
            if(tmp.get(i).toString().equals(args[1])){
                pos = i;
            }
        }

        // if there's a object with the name args[1]. open it
        if (pos != -1){
            tmp.get(pos).open();
        }else{
            System.out.println(Message.gameErrorOpen);


        }

    }

    public void take(String commande) {

        String[] args = commande.split(" ");
        if(args.length == 1){
            //Take all the items at place
            if (place.GetPortableItemRoom().size() > 0){
                System.out.println(Message.gameTake);
                while (!place.GetPortableItemRoom().isEmpty()){
                    Portable tmp = place.GetPortableItemRoom().get(0);
                    place.deleteItem(tmp);
                    player.addInventor(tmp);
                }
                System.out.println(Message.gameTakeAll);
            }else{
                System.out.println(Message.gameErrorTake);
            }
        }else{
            //Take a specific item at place
            if(containsObject(args[1])){
                Portable tmp = place.GetPortableItemRoom().stream().filter( x-> x.toString().equals(args[1])).collect(Collectors.toList()).get(0);
                place.deleteItem(tmp);
                player.addInventor(tmp);
            }else{
                System.out.println(Message.gameTakeNul);
            }


        }
    }

    public boolean containsObject(String arg) {
        boolean flag = false;

        for (Portable p : place.GetPortableItemRoom()){
            if(p.toString().equals(arg)) {
                flag = true;
            }
        }

        return flag;
    }

    private void quit(String commande) {

    }

    public void look(String commande) {
        String[] args = commande.split(" ");
        if(args.length == 1){
            //Look at place
            System.out.println(place.describePlace());
        }else{
            //look at object in place
            place.describeItem(args[1]);
        }
    }

    public void help() {
        System.out.println(Message.gameHelp);
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
                    useExtinguiser(usableObjects);
                    break;
                case "TELEPHONE":
                    break;
            }
        }else{
            System.out.println(Message.gameErrorHelp);
        }
    }

    // TODO : CHECK THIS METHOD
    public void useExtinguiser(List<Usable> usableObjects) {
        List<Usable> extinguisers = usableObjects.stream()
                                                    .filter(i -> i instanceof Extinguisher)
                                                    .collect(Collectors.toList());
        List<Firewall> firewalls = place.getMapExit().values().stream()
                                                                .filter(i -> i instanceof Firewall)
                                                                .map(i -> (Firewall) i)
                                                                .collect(Collectors.toList());

        if (firewalls.size() > 0 && extinguisers.size() > 0){
            for (Firewall firewall : firewalls) {
                for (Usable extinguiser : extinguisers) {
                    firewall.unlock((Item) extinguiser);
                    try {
                        extinguiser.use(null);
                    } catch (NotRightKey ignored) {
                        //ignored catch because there's no possible way to raise this exception here
                    }
                }
            }

        }else{
            if (extinguisers.size() > 0){

                for (Usable extinguiser : extinguisers) {
                    try {
                        extinguiser.use(null);
                    } catch (NotRightKey ignored) {
                        //ignored catch because there's no possible way to raise this exception here
                    }
                }

                System.out.println("I'm just wasting the extinguisher...");
            }

            if (firewalls.size() > 0){
                System.out.println("I don't have a extinguisher to use...");
            }
        }

    }

    // TODO : implements the use of doors with a key
    public void useKey(List<Usable> usableObjects) {
        List<Openable> chests = place.GetOpenableItemRoom()
                                        .stream()
                                        .filter( i -> i instanceof Chest)
                                        .collect(Collectors.toList());

        List<Usable> keys = usableObjects.stream()
                                         .filter(usable -> usable instanceof Key)
                                            .collect(Collectors.toList());

        System.out.println("I'm gonna use each key that i have with everything...\n" +
                "maybe something will work with it\n");
        if(keys.size() > 0 && chests.size()>0){
            for (Usable key : keys) {
                for (Openable chest : chests) {

                    try {
                        key.use(chest);
                    } catch (NotRightKey notRightKey) {
                        System.out.println("\t" + notRightKey.getMessage());
                    }

                }
            }
        }else{

            if (keys.size() == 0){
                System.out.println(Message.gameNoKey);
            }


            if (chests.size() == 0){
                System.out.println(Message.gameChestKey);
            }

        }
    }

    private void go(String commande) {
        String[] args = commande.split(" ");
        try {
            Exit tmp = place.select(args[1]);
            place = (Room) tmp.nextPlace();
        } catch (ExitPlaceException | PlaceException e) {
            System.out.println(e.getMessage());
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

    public Player getPlayer() {
        return player;
    }

    public Room getPlace() {
        return place;
    }
}
