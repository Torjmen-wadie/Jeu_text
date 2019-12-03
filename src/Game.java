import exceptions.ExitPlaceException;
import exceptions.NotRightKey;
import exceptions.PlaceException;

import java.util.*;
import java.util.stream.Collectors;

public class Game extends Thread {
    private Player player;
    private Room place;
    private Objective objective;
    private List<Place> gamemap;
    public static boolean runGame = true;
    Scanner scanner;
    
    private final static Pair [][] MAP = 
		{
			{new Pair("",0),new Pair("Door",1),new Pair("",0),new Pair("Wood Door",1),new Pair("",0),new Pair("",0),new Pair("",0),new Pair("",0),new Pair("",0),new Pair("",0),new Pair("",0),new Pair("",0),new Pair("",0),new Pair("",0),new Pair("",0)},
			
			{new Pair("Door",1), new Pair("",0), new Pair("Swing Door",2), new Pair("",0), new Pair("",0),new Pair("",0), new Pair("",0), new Pair("Metal Door",2), new Pair("",0), new Pair("",0),new Pair("",0), new Pair("",0) , new Pair("",0) , new Pair("",0) , new Pair("",0)},
			
			{new Pair("",0),new Pair("Swing Door",2),new Pair("",0),new Pair("",0),new Pair("",0),new Pair("",0),new Pair("",0),new Pair("Threshold",1),new Pair("",0),new Pair("",0),new Pair("",0),new Pair("",0),new Pair("",0),new Pair("",0),new Pair("",0)},
			
			{new Pair("",0),new Pair("",0),new Pair("",0),new Pair("",0),new Pair("Trap Door",1),new Pair("",0),new Pair("",0),new Pair("Window Door",2),new Pair("",0),new Pair("",0),new Pair("",0),new Pair("",0),new Pair("",0),new Pair("",0),new Pair("",0)},
			
			{new Pair("",0),new Pair("",0),new Pair("",0),new Pair("",0),new Pair("",0),new Pair("Hole",1),new Pair("",0),new Pair("",0),new Pair("",0),new Pair("",0),new Pair("",0),new Pair("",0),new Pair("",0),new Pair("",0),new Pair("",0)},
			
			{new Pair("",0),new Pair("",0),new Pair("",0),new Pair("",0),new Pair("Hole",1),new Pair("",0),new Pair("Fire Door",3),new Pair("",0),new Pair("",0),new Pair("",0),new Pair("",0),new Pair("",0),new Pair("",0),new Pair("",0),new Pair("",0)},
			
			{new Pair("",0),new Pair("",0),new Pair("",0),new Pair("",0),new Pair("",0),new Pair("Fire Door",3),new Pair("",0),new Pair("Double Door",0),new Pair("",0),new Pair("",0),new Pair("",0),new Pair("",0),new Pair("",0),new Pair("",0),new Pair("",0)},
			
			{new Pair("",0),new Pair("Metal Door",2),new Pair("",0),new Pair("Window door",2),new Pair("",0),new Pair("",0),new Pair("Double door",1),new Pair("",0),new Pair("Wood door",1),new Pair("",0),new Pair("",0),new Pair("",0),new Pair("",0),new Pair("",0),new Pair("",0)},
			
			{new Pair("",0),new Pair("",0),new Pair("Threshold",1),new Pair("",0),new Pair("",0),new Pair("",0),new Pair("",0),new Pair("Wood door",1),new Pair("",0),new Pair("Secret door",2),new Pair("",0),new Pair("",0),new Pair("",0),new Pair("",0),new Pair("",0)},
			
			{new Pair("",0),new Pair("",0),new Pair("",0),new Pair("",0),new Pair("",0),new Pair("",0),new Pair("",0),new Pair("Secret door",2),new Pair("",0),new Pair("",0),new Pair("",0),new Pair("",0),new Pair("",0),new Pair("Reinforced door",2),new Pair("Curtain",1)},
			
			{new Pair("",0),new Pair("",0),new Pair("",0),new Pair("Fire door",3),new Pair("",0),new Pair("",0),new Pair("",0),new Pair("",0),new Pair("",0),new Pair("",0),new Pair("",0),new Pair("Metal door",2),new Pair("",0),new Pair("",0),new Pair("",0)},
			
			{new Pair("",0),new Pair("",0),new Pair("",0),new Pair("",0),new Pair("",0),new Pair("",0),new Pair("",0),new Pair("",0),new Pair("",0),new Pair("",0),new Pair("Metal door",2),new Pair("",0),new Pair("Openning",1),new Pair("",0),new Pair("",0)},
			
			{new Pair("",0),new Pair("",0),new Pair("",0),new Pair("",0),new Pair("",0),new Pair("",0),new Pair("",0),new Pair("",0),new Pair("",0),new Pair("",0),new Pair("",0),new Pair("",0),new Pair("",0),new Pair("",0),new Pair("",0)},
			
			{new Pair("",0),new Pair("",0),new Pair("",0),new Pair("",0),new Pair("Hole",1),new Pair("",0),new Pair("Fire door",3),new Pair("",0),new Pair("",0),new Pair("Reinforced door",2),new Pair("",0),new Pair("",0),new Pair("",0),new Pair("",0),new Pair("",0)},
			
			{new Pair("",0),new Pair("",0),new Pair("",0),new Pair("",0),new Pair("",0),new Pair("",0),new Pair("",0),new Pair("",0),new Pair("",0),new Pair("",0),new Pair("",0),new Pair("",0),new Pair("",0),new Pair("",0),new Pair("",0)}
	
		};


    

    //    Thread t =new Thread();
    public static volatile boolean runinig;
    
    public Game() 
    {
        this.scanner = new Scanner(System.in);
        this.player = new Player("Player");
        this.gamemap = this.setUpMap();
        this.objective = new Objective(this.gamemap.get(0), this.gamemap.get(this.gamemap.size()-1));
        this.place = (Room) this.gamemap.get(0);
    }
    
    
    
    public List<Place> setUpMap()
    {

        List<Place> unlink_place = new ArrayList<>();
        
        
        //----------------- 1 - Initialization Item-------------------------
        
        // ----------Room--------------
        Couch couch_room = new Couch("Bed",new ArrayList<Item>());
        // ----------Lounge--------------
        Vase vase_lounge = new Vase("Chinese vase", new ArrayList<Item>());
        	//TODO : Put key for Corridor in vase 
        	// TODO : put key for metal door
        // ----------Library--------------
        Book library_book =new Book("you can find a key in the Game Room ...","Java");
        List<Item> desk_contain = new ArrayList<Item>();
        desk_contain.add(library_book);
        Desk desk_library = new Desk("Bookcase",desk_contain);
    	// ----------Game Room--------------
        	// AUTO-GENERATE : Key
        // ----------Bath Room--------------
        	// AUTO-GENERATE : Key
        // ----------Boiler Room--------------
        Extinguisher extinct_boileroom  = new Extinguisher("Extinguisher", 30);
        Letter letter=new Letter("you must use the extiglisher in the door with fire ...","from your teacher");
        // ----------Corridor--------------
        Extinguisher extinct_corridor = new Extinguisher("Extinguisher", 30);
        // ----------Kitchen--------------
        Chest chest_kitchen = new Chest("Chest",new ArrayList<Item>());
        	//TODO : Put key Pantry here
        // ----------Pantry--------------
        	//TODO : Put key Bar here
        
        //----------------- 2 - Initialization Place-------------------------
       
        // ---------- 0 Room --------------
        List<Item> room_contain = new ArrayList<Item>();
        room_contain.add(couch_room);
        Room room =new Room("Room","This is your room which contain a couch",room_contain);
        unlink_place.add(room);
        
        // ---------- 1 Lounge Room --------------
        List<Item> lounge_contain = new ArrayList<Item>();
        	
        Room lounge=new Room("Lounge Room","you have two key here ... ",lounge_contain);
        unlink_place.add(lounge);
        
        // ---------- 2 Library --------------
        List<Item> libr_contain = new ArrayList<Item>();
        libr_contain.add(desk_library);
        Room libr =new Room("Library","you must find the key in the desk...",libr_contain);
        unlink_place.add(libr);     
        
        // ---------- 3 Bathroom --------------
        List<Item> bath_contain = new ArrayList<Item>();
        Room bath=new Room("Bathroom","This is the bathroom which contain a key...",bath_contain);
        unlink_place.add(bath);
        
        // ---------- 4 Drain --------------
        List<Item> drain_contain = new ArrayList<Item>();
        Room drain =new Room("Drain","In the Drain you must find the Extinghuisher...",drain_contain);
        unlink_place.add(drain);
        
        // ---------- 5 Boiler Room --------------
        List<Item> boiler_contain = new ArrayList<Item>();
        boiler_contain.add(extinct_boileroom);
        boiler_contain.add(letter);
        Room boiler= new Room("Boiler Room","the Baler Room contain an Extinguisher",boiler_contain);
        unlink_place.add(boiler);
        
	    // ---------- 6 Restaurant --------------
        List<Item> resto_contain = new ArrayList<Item>();
        Room resto=new Room("Restaurant","there is nothing here you must go out",resto_contain);
        unlink_place.add(resto);
        
        // ---------- 7 Corridor --------------
        List<Item> cori_contain = new ArrayList<Item>();
        cori_contain.add(extinct_corridor);
        Room cori=new Room("Corridor","here you have a key and an extinguisher ...",cori_contain);
        unlink_place.add(cori);
        
        // ---------- 8 Game Room --------------
        List<Item> game_contain = new ArrayList<Item>();
        Room game=new Room("Game Room","you must find the key in this place...",game_contain);
        unlink_place.add(game);
        
        // ---------- 9 Bar --------------
        List<Item> bar_contain = new ArrayList<Item>();
        Room bar =new Room("Bar","go out ...",bar_contain);
        unlink_place.add(bar);
        
        // ---------- 10 Kitchen --------------
        List<Item> kit_contain = new ArrayList<Item>();
        Room kit=new Room("Kitchen","there is key in some where here",kit_contain);
        unlink_place.add(kit);
        
        // ---------- 11 Pantry --------------
        List<Item> pan_contain = new ArrayList<Item>();
        Room pan=new Room("Pantry","there is key",pan_contain);
        unlink_place.add(pan);
        
        // ---------- 12 Cold Room --------------
        List<Item> cold_contain = new ArrayList<Item>();
        Room cold= new Room("Cold Room","there is nothing",cold_contain);
        unlink_place.add(cold);
        
        // ---------- 13 Ballroom --------------
        List<Item> bal_contain = new ArrayList<Item>();
        Room bal=new Room("Ballroom","there is a key here",bal_contain);
        unlink_place.add(bal);
        
        // ---------- 14 Reception --------------
        List<Item> rece_contain = new ArrayList<Item>();
        Room rece=new Room("Reception","you must go out right now",rece_contain);
        unlink_place.add(rece);
        
        
        Mapgenerator map = new Mapgenerator(MAP, unlink_place);
        map.create();
        List<Place> world = map.getMap();
        
        
        //------------------Lounge : Move Key into vase-----------------------
        
        Room lounge_select = (Room) world.get(1);
        List<Key> loungekey = lounge_select.GetPortableItemRoom().stream()
        .filter(i -> i instanceof Key)
        .map(i -> (Key) i)
    	.collect(Collectors.toList());
        loungekey.forEach(k -> lounge_select.deleteItem(k));
        loungekey.forEach(k -> vase_lounge.addItem(k));
        lounge_select.addItem(vase_lounge);
       
        
      //------------------Game Room : Move Key into Pantry-----------------------
        
        Room game_select = (Room) world.get(8);
        List<Key> gamekey = game_select.GetPortableItemRoom().stream()
        .filter(i -> i instanceof Key)
        .map(i -> (Key) i)
        .collect(Collectors.toList());
        
        gamekey.forEach(k -> game_select.deleteItem(k));
        
        Room pantry_select = (Room) world.get(11);
        gamekey.forEach(k -> pantry_select.addItem(k));
        
        //------------------------Kitchen : Move Key into Chest---------------------
        Room kitchen_select = (Room) world.get(10);
        List<Key> kitchenkey = kitchen_select.GetPortableItemRoom().stream()
        .filter(i -> i instanceof Key)
        .map(i -> (Key) i)
        .collect(Collectors.toList());
        
        kitchenkey.forEach(k -> kitchen_select.deleteItem(k));
        kitchenkey.forEach(k -> chest_kitchen.addItem(k));
        kitchen_select.addItem(chest_kitchen);
        
        return world;
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
            waitingForCommands();
        }
    }

    public void waitingForCommands(){

        String commande = scanner.nextLine();
        String[] parts = commande.split(" ");

        if (confirmCommande(parts[0])){
            execCommande(commande);
        }else{
            System.out.println(Message.gameErrorWait);
        }
    }

    private void execCommande(String commande) {
        commande = commande.trim();
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
                runGame = false;
                break;

            case TAKE:take(commande);
                break;

            case OPEN:open(commande);
                break;
            case THROW:eject(commande);
                break;
            case SHOW:show(commande);
                break;
        }
    }

    private void show(String commande) {
        if (player.getObjects().size() > 0){
            System.out.println("I have : ");
            player.getObjects().forEach(System.out::println);
        }else{
            System.out.println("I don't have any object");
        }
    }

    public void eject(String commande) {
        String[] args = commande.split(" ",2);

        if (args.length > 1){
            Portable tmp = player.deleteUsableObject(args[1]);
            if (tmp != null)
                place.addItem((Item) tmp);

        }else {
            System.out.println("What am i supposed to throw?");
        }
    }

    public void open(String commande) {
        String[] args = commande.split(" ");

        if (args.length > 1 ){
            int pos = -1;
            List<Openable> tmp = place.GetOpenableItemRoom();
            // added the items from user to open
            tmp.addAll(player.getOpenableItems());
            //added the doors to open
            tmp.addAll(place.getDoors());

            for (int i = 0; i < tmp.size(); i++) {
                if(tmp.get(i).toString().equalsIgnoreCase(args[1])){
                    pos = i;
                }
            }

            // if there's a object with the name args[1]. open it
            if (pos != -1){
                tmp.get(pos).open();
                if( tmp.get(pos).isopen() ) {
                    // TODO : TMP POUR LE MOMENT
                    System.out.println("OPENED");
                }else{
                    // TODO : TMP POUR LE MOMENT
                    System.out.println("It's still closed");
                }
            }else{
                System.out.println(Message.gameErrorWait);


            }

        }else {
            System.out.println(Message.gameErrorWait);
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

    //bug, look 2 time
    public void look(String commande) {
        String[] args = commande.split(" ", 2);
        if(args.length == 1){
            //Look at place
            System.out.println(place.describePlace());
            System.out.println(place.describeExit());
        }else{
            //look at object in place
            //if look is at Container, we'll get all the objects inside
            List<Item> insideItems = place.describeItem(args[1]);

            if (insideItems != null){
                //put all the objects inside place
                for (Item insideItem : insideItems) {
                    place.addItem(insideItem);
                }
            }
        }
    }

    public void help() {
        System.out.println(Message.gameHelp);
        Arrays.asList(Commande.values()).forEach(System.out::println);
    }

    private void use(String commande) {
        String args[] = commande.split(" ");

        if (args.length > 1){


            List<Usable> usableObjects = player.getUsableObjects();

            if (usableObjects.size() > 0){
                switch (args[1].toUpperCase()){
                    case "KEY" :
                        useChestWithKey(usableObjects);
                        useDoor(usableObjects);
                        break;
                    case "EXTINGUISHER":
                        useExtinguiser(usableObjects);
                        break;
                    case "TELEPHONE":
                        break;

                    default:
                        System.out.println("what am i supposed to use?");
                        break;
                }
            }else{
                System.out.println(Message.gameErrorHelp);
            }
        }else{
            System.out.println(Message.gameUseError);
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
    public void useChestWithKey(List<Usable> usableObjects) {


        List<Openable> chests = place.GetOpenableItemRoom()
                                        .stream()
                                        .filter( i -> i instanceof Chest)
                                        .collect(Collectors.toList());

        List<Usable> keys = usableObjects.stream()
                                         .filter(usable -> usable instanceof Key)
                                            .collect(Collectors.toList());

        System.out.println("I'm gonna use each key that i have with everything...\n" +
                "maybe something will work with it\n");

        Key tmp = null;
        if(keys.size() > 0 && chests.size()>0){
            for (Usable key : keys) {
                for (Openable chest : chests) {

                    try {
                        System.out.println("I'm trying to open this " + chest.toString());
                        key.use(chest);

                        // if is a lockedChest, keep the key to delete after use
                        if (chest.getClass().getSimpleName().equalsIgnoreCase("LockedChest")){
                            if (((LockedChest)chest).isUnlocked()){
                                deleteRightKey((Key) key);
                            }
                        }

                        //if chest is open, don't try the other keys
                        if(((Chest) chest).isOpened()){
                            break;
                        }

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

    // Delete the rigth key from player's items
    private void deleteRightKey(Key tmp) {
        if (tmp != null){
            player.deleteUsableObject(tmp.toString());
        }
    }

    public void useDoor(List<Usable> usableObjects) {
        List<Key> keys = usableObjects.stream()
                .filter(usable -> usable instanceof Key)
                .map(k -> (Key) k)
                .collect(Collectors.toList());

        List<Exitwithkey> exits = place.getDoors().stream()
                                            .filter(e  -> e instanceof Exitwithkey)
                                            .map( e -> (Exitwithkey) e )
                                            .collect(Collectors.toList());

        System.out.println("I'm gonna try to open this thing with all my keys...");
        Key tmp = null;
        for (Exitwithkey exit : exits) {
            for (int i = 0; i < keys.size(); i++) {
                if (exit.islock()){
                    exit.unlock(keys.get(i));
                    if (!exit.islock()){
                        tmp = keys.get(i);
                        keys.remove(tmp);
                        deleteRightKey(tmp);
                        break;
                    }
                }
            }
        }

        System.out.println("I finally managed to open");
        for (Exitwithkey exit : exits) {
            System.out.println(exit + " \t" + !exit.islock());
        }


    }

    private void go(String commande) {
        String[] args = commande.split(" ", 2);
        try {
            if (args.length > 1){
                Exit tmp = place.select(args[1]);
                place = (Room) tmp.nextPlace();
            }else {
                System.out.println("Where am i going?");
            }
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
    
    public static void main(String[] args) {
    	Game game = new Game();
    	game.init();
    
    	
    }

}
