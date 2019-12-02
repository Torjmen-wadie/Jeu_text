import com.sun.deploy.security.SelectableSecurityManager;
import com.sun.xml.internal.bind.v2.runtime.Coordinator;
import exceptions.ExitPlaceException;
import exceptions.NotRightKey;
import exceptions.PlaceException;

import java.util.*;
import java.util.stream.Collectors;

public class Game extends Thread {
    private Player player;
    private Room place;

    private Key keyL ;
    private Key keyLou ;
    private Key keyBa;
    private Key keyKit;
    private Key keyPou;
    private Key keyGa ;

    private Key keyBal;
    private Book book;
    private Letter letter;
    private Extinguisher extinguisher;
    private Desk desk ;
    private Couch couch;
    private Extinguisher extin1;
    private Extinguisher extin2;

    //    Thread t =new Thread();
    public static volatile boolean runinig;
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
    public void intilisation()
    {

        List<Place> hotel = new ArrayList<>();
        keyL=new Key("keyLounge",23456);
        keyLou =new Key("keyRoom",12345);
        keyBa =new Key("KeyBathroom",78451);
        keyPou=new Key("keyPountry",54712);
        keyKit=new Key("keyKitchen",27841);
        keyGa=new Key("keyGame",54661);
        book=new Book("you must find the Key in the Lounge ...","Java");
        letter=new Letter("you must use the extiglisher in the door with fire ...","from your teacher");
        extin1 = new Extinguisher("ExtinguisherCor",30);
        extin2=new Extinguisher("ExtinguisherDrain",30);

        List<Item> deskObj=new ArrayList<>();
        deskObj.add(book);
        desk=new Desk("Desk",deskObj);
        List<Item>objePlace =new ArrayList<>();
        objePlace.add(keyL);
        objePlace.add(keyLou);
        objePlace.add(keyBa);
        objePlace.add(keyPou);
        objePlace.add(keyKit);
        objePlace.add(keyGa);
        objePlace.add(book);
        objePlace.add(letter);
        objePlace.add(extin1);
        objePlace.add(extin2);
        Room room =new Room("Room","This is your room which contain a couch",objePlace);
        Room bath=new Room("Bathroom","This is the bathroom which contain a key...",objePlace);
        Room Drain =new Room("Drain","In the Drain you must find the Extinghuisher...",objePlace);
        Room baler= new Room("BalerRoom","the Baler Room contain an Extinguisher",objePlace);
        Room resto=new Room("Restorant","there is nothing here you must go out",null);
        Room cori=new Room("Coridor","here you have a key and an extinguisher ...",objePlace);
        Room lounge=new Room("Lounge Room","you have two key here ... ",objePlace);
        Room libr =new Room("Liibrary","you must find the key in the desk...",objePlace);
        Room game=new Room("Game Room","you must find the key in this place...",objePlace);
        Room bal=new Room("Balhroom","there is a key here",objePlace);
        Room kitc=new Room("Kitchen","there is key in some where here",objePlace);
        Room pou=new Room("Pontry","there is key",objePlace);
        Room cold= new Room("Cold Room","there is nothing",null);
        Room bar =new Room("Bar","go out ...",null);
        Room rece=new Room("Reception","you must go out right now",null);
        hotel.add(room);
        hotel.add(bath);
        hotel.add(Drain);
        hotel.add(resto);
        hotel.add(cori);
        hotel.add(baler);
        hotel.add(lounge);
        hotel.add(libr);
        hotel.add(game);
        hotel.add(bal);
        hotel.add(kitc);
        hotel.add(pou);
        hotel.add(cold);
        hotel.add(bar);
        hotel.add(rece);

    }

    //surcharger la méthode run() de classe thread
    public  void  startGame()
   {
       this.runinig=true;
       while (runinig)
       System.out.println("the Game is started");
   }
        public void stopGame(){
            this.runinig =false;
            System.out.println("the Game is end");
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
