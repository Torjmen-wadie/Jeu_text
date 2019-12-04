import exceptions.ExitPlaceException;
import exceptions.NotRightKey;
import exceptions.PlaceException;

import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

public class Game extends Thread {
    private Player player;
    private Room place;
    private Objective objective;
    private Telephone telephone;
    private List<Place> gamemap;
    private volatile boolean runGame = true;
    Scanner scanner;

    // time to play? 5 minutes
    private int TIME = 300000;
    
    private Pair [][] MAP = 
		{
			{new Pair("",0),new Pair("Door",1),new Pair("",0),new Pair("Wood door",1),new Pair("",0),new Pair("",0),new Pair("",0),new Pair("",0),new Pair("",0),new Pair("",0),new Pair("",0),new Pair("",0),new Pair("",0),new Pair("",0),new Pair("",0)},
			
			{new Pair("Door",1), new Pair("",0), new Pair("Swing door",2), new Pair("",0), new Pair("",0),new Pair("",0), new Pair("",0), new Pair("Metal door",2), new Pair("",0), new Pair("",0),new Pair("",0), new Pair("",0) , new Pair("",0) , new Pair("",0) , new Pair("",0)},
			
			{new Pair("",0),new Pair("Swing door",2),new Pair("",0),new Pair("",0),new Pair("",0),new Pair("",0),new Pair("",0),new Pair("",0),new Pair("Threshold",1),new Pair("",0),new Pair("",0),new Pair("",0),new Pair("",0),new Pair("",0),new Pair("",0)},
			
			{new Pair("",0),new Pair("",0),new Pair("",0),new Pair("",0),new Pair("Trap door",1),new Pair("",0),new Pair("",0),new Pair("Window door",2),new Pair("",0),new Pair("",0),new Pair("",0),new Pair("",0),new Pair("",0),new Pair("",0),new Pair("",0)},
			
			{new Pair("",0),new Pair("",0),new Pair("",0),new Pair("",0),new Pair("",0),new Pair("Hole",1),new Pair("",0),new Pair("",0),new Pair("",0),new Pair("",0),new Pair("",0),new Pair("",0),new Pair("",0),new Pair("",0),new Pair("",0)},
			
			{new Pair("",0),new Pair("",0),new Pair("",0),new Pair("",0),new Pair("Hole",1),new Pair("",0),new Pair("Fire door",3),new Pair("",0),new Pair("",0),new Pair("",0),new Pair("",0),new Pair("",0),new Pair("",0),new Pair("",0),new Pair("",0)},
			
			{new Pair("",0),new Pair("",0),new Pair("",0),new Pair("",0),new Pair("",0),new Pair("Fire door",3),new Pair("",0),new Pair("Double door",1),new Pair("",0),new Pair("",0),new Pair("",0),new Pair("",0),new Pair("",0),new Pair("Arch",1),new Pair("",0)},
			
			{new Pair("",0),new Pair("Metal door",2),new Pair("",0),new Pair("Window door",2),new Pair("",0),new Pair("",0),new Pair("Double door",1),new Pair("",0),new Pair("Wood door",1),new Pair("",0),new Pair("",0),new Pair("",0),new Pair("",0),new Pair("",0),new Pair("",0)},
			
			{new Pair("",0),new Pair("",0),new Pair("Threshold",1),new Pair("",0),new Pair("",0),new Pair("",0),new Pair("",0),new Pair("Wood door",1),new Pair("",0),new Pair("Secret door",2),new Pair("",0),new Pair("",0),new Pair("",0),new Pair("",0),new Pair("",0)},
			
			{new Pair("",0),new Pair("",0),new Pair("",0),new Pair("",0),new Pair("",0),new Pair("",0),new Pair("",0),new Pair("",0),new Pair("Secret door",2),new Pair("",0),new Pair("",0),new Pair("",0),new Pair("",0),new Pair("Reinforced door",2),new Pair("Curtain",1)},
			
			{new Pair("",0),new Pair("",0),new Pair("",0),new Pair("Fire door",3),new Pair("",0),new Pair("",0),new Pair("",0),new Pair("",0),new Pair("",0),new Pair("",0),new Pair("",0),new Pair("Metal door",2),new Pair("",0),new Pair("Fire door",3),new Pair("",0)},
			
			{new Pair("",0),new Pair("",0),new Pair("",0),new Pair("",0),new Pair("",0),new Pair("",0),new Pair("",0),new Pair("",0),new Pair("",0),new Pair("",0),new Pair("Metal door",2),new Pair("",0),new Pair("Openning",1),new Pair("",0),new Pair("",0)},
			
			{new Pair("",0),new Pair("",0),new Pair("",0),new Pair("",0),new Pair("",0),new Pair("",0),new Pair("",0),new Pair("",0),new Pair("",0),new Pair("",0),new Pair("",0),new Pair("",0),new Pair("",0),new Pair("",0),new Pair("",0)},
			
			{new Pair("",0),new Pair("",0),new Pair("",0),new Pair("",0),new Pair("Hole",1),new Pair("",0),new Pair("Arch",1),new Pair("",0),new Pair("",0),new Pair("Reinforced door",2),new Pair("Fire door",3),new Pair("",0),new Pair("",0),new Pair("",0),new Pair("",0)},
			
			{new Pair("",0),new Pair("",0),new Pair("",0),new Pair("",0),new Pair("",0),new Pair("",0),new Pair("",0),new Pair("",0),new Pair("",0),new Pair("Curtain",1),new Pair("",0),new Pair("",0),new Pair("",0),new Pair("",0),new Pair("",0)}
	
		};


    

    //    Thread t =new Thread();
    public static volatile boolean runinig;
    
    public Game() 
    {
        this.scanner = new Scanner(System.in);
        this.player = new Player("Player");
        this.gamemap = this.setUpMap();
        this.objective = new Objective(this.gamemap.get(0), this.gamemap.get(this.gamemap.size()-1));
        this.place = (Room) this.gamemap.get(2);

        //add the telephone to user's inventory
        telephone = new Telephone("It seems to have not much battery","telephone");
        player.addInventor(telephone);
        player.addInventor(new Key("aadss", 2));
        player.addInventor(new Key("adss", 3));
        player.addInventor(new Key("dss", 4));
        player.addInventor(new Key("ss", 5));
    }

    public void menu(){
        System.out.println(Message.logo);
        System.out.println("\n\n\tPress enter to continue...");
        //wait until enter is pressed
        try {
            System.in.read();
        } catch (IOException e) {
            e.printStackTrace();
        }

        System.out.println(Message.introduction);
        startGame();
        init();
    }
    
    
    
    public List<Place> setUpMap()
    {

        List<Place> unlink_place = new ArrayList<>();
        
        
        //----------------- 1 - Initialization Item-------------------------
        
        // ----------Room--------------
        Couch couch_room = new Couch("Bed",new ArrayList<Item>());
        // ----------Lounge--------------
        Vase vase_lounge = new Vase("Chinese vase", new ArrayList<Item>());
        Letter letter_lounge = new Letter("I lose my keys in a vase !", "HELP ME");
        	//TODO : Put key for Corridor in vase 
        	// TODO : put key for metal door
        // ----------Library--------------
        Book library_book =new Book("you can find a key in the Game Room ...","Java Book");
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
        Room room =new Room("Room",Message.room,room_contain);
        unlink_place.add(room);
        
        // ---------- 1 Lounge Room --------------
        List<Item> lounge_contain = new ArrayList<Item>();
        lounge_contain.add(letter_lounge);
        Room lounge=new Room("Lounge Room", Message.loungeRoom,lounge_contain);
        unlink_place.add(lounge);
        
        // ---------- 2 Library --------------
        List<Item> libr_contain = new ArrayList<Item>();
        libr_contain.add(desk_library);
        Room libr =new Room("Library",Message.library,libr_contain);
        unlink_place.add(libr);     
        
        // ---------- 3 Bathroom --------------
        List<Item> bath_contain = new ArrayList<Item>();
        Room bath=new Room("Bathroom", Message.bathroom,bath_contain);
        unlink_place.add(bath);
        
        // ---------- 4 Drain --------------
        List<Item> drain_contain = new ArrayList<Item>();
        Room drain =new Room("Drain",Message.drain,drain_contain);
        unlink_place.add(drain);
        
        // ---------- 5 Boiler Room --------------
        List<Item> boiler_contain = new ArrayList<Item>();
        boiler_contain.add(extinct_boileroom);
        boiler_contain.add(letter);
        Room boiler= new Room("Boiler Room",Message.boilerRoom ,boiler_contain);
        unlink_place.add(boiler);
        
	    // ---------- 6 Restaurant --------------
        List<Item> resto_contain = new ArrayList<Item>();
        Room resto=new Room("Restaurant",Message.restaurant ,resto_contain);
        unlink_place.add(resto);
        
        // ---------- 7 Corridor --------------
        List<Item> cori_contain = new ArrayList<Item>();
        cori_contain.add(extinct_corridor);
        Room cori=new Room("Corridor",Message.corridor ,cori_contain);
        unlink_place.add(cori);
        
        // ---------- 8 Game Room --------------
        List<Item> game_contain = new ArrayList<Item>();
        Room game=new Room("Game Room",Message.gameRoom,game_contain);
        unlink_place.add(game);
        
        // ---------- 9 Bar --------------
        List<Item> bar_contain = new ArrayList<Item>();
        Room bar =new Room("Bar",Message.bar,bar_contain);
        unlink_place.add(bar);
        
        // ---------- 10 Kitchen --------------
        List<Item> kit_contain = new ArrayList<Item>();
        Room kit=new Room("Kitchen",Message.kitchen ,kit_contain);
        unlink_place.add(kit);
        
        // ---------- 11 Pantry --------------
        List<Item> pan_contain = new ArrayList<Item>();
        Room pan=new Room("Pantry",Message.pantry ,pan_contain);
        unlink_place.add(pan);
        
        // ---------- 12 Cold Room --------------
        List<Item> cold_contain = new ArrayList<Item>();
        Room cold= new Room("Cold Room",Message.coldRoom,cold_contain);
        unlink_place.add(cold);
        
        // ---------- 13 Ballroom --------------
        List<Item> bal_contain = new ArrayList<Item>();
        Room bal=new Room("Ballroom",Message.ballroom,bal_contain);
        unlink_place.add(bal);
        
        // ---------- 14 Reception --------------
        List<Item> rece_contain = new ArrayList<Item>();
        Room rece=new Room("Reception",Message.reception ,rece_contain);
        unlink_place.add(rece);
        
        
        Mapgenerator map = new Mapgenerator(this.MAP, unlink_place);
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
        
        //---------------------- Bar : Move Key into Ballroom-----------------------
        Room bar_select = (Room) world.get(9);
        List<Key> barkey = bar_select.GetPortableItemRoom().stream()
        .filter(i -> i instanceof Key)
        .map(i -> (Key) i)
        .collect(Collectors.toList());
        barkey.forEach(k -> bar_select.deleteItem(k));
        
        Room ballroom_select = (Room) world.get(13);
        barkey.forEach(k -> ballroom_select.addItem(k));
        
        
        return world;
    }

    //surcharger la méthode run() de classe thread
    public  void  startGame()
    {
        this.runinig=true;
        //the game starts, countdown starts...
        Thread stop = new Thread(() -> {
            try {
                //starts countdown
                sleep(TIME);
                runGame = false;
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        stop.start();
    }

    public void stopGame(){
        this.runinig =false;
    }


    public void init(){

        while (runGame && !(this.objective.isWin()))
        {
            System.out.println("\n");
            waitingForCommands();
        }


        if (this.objective.isWin())
        {
            System.out.println(Message.won);
        }
        else
        {
            System.out.println(Message.loser);
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

            // if it's a phone, avoid throw
            if (args[1].equalsIgnoreCase("telephone"))
            {
                System.out.println("I should keep my phone with me...");
            }else
            {
                Portable tmp = player.deleteUsableObject(args[1]);
                if (tmp != null){
                    place.addItem((Item) tmp);
                }else{
                    System.out.println("what am i supposed to throw??");
                }
            }

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

        String[] args = commande.split(" ",2);
        if(args.length == 1){
            //Take all the items at place
            if (place.GetPortableItemRoom().size() > 0){
                System.out.println(Message.gameTake);
                for (int i = 0; i < place.GetPortableItemRoom().size(); i++) {
                    Portable tmp = place.GetPortableItemRoom().get(0);
                    if( player.addInventor(tmp) ) {
                        place.deleteItem(tmp);
                    }
                }

                System.out.println(Message.gameTakeAll);
            }else{
                System.out.println(Message.gameErrorTake);
            }
        }else{
            //Take a specific item at place
            if(containsObject(args[1]))
            {
                Portable tmp = place.GetPortableItemRoom().stream().filter( x-> x.toString().equalsIgnoreCase(args[1])).collect(Collectors.toList()).get(0);
                //if we can add the item in the inventory, delete from the place
                if (player.addInventor(tmp))
                {
                    place.deleteItem(tmp);
                }

            }
            
            else
            {
                System.out.println(Message.gameTakeNul);
            }


        }
    }

    public boolean containsObject(String arg) {
        boolean flag = false;

        for (Portable p : place.GetPortableItemRoom()){
            if(p.toString().equalsIgnoreCase(arg)) {
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
            //try to look at objects in player's inventory
            List<Item> itemInPlayerInventory = player.getObjects().stream()
                                                .filter(i -> i.toString().equalsIgnoreCase(args[1]))
                                                .map( i -> (Item) i)
                                                .collect(Collectors.toList());
            if (itemInPlayerInventory.size() > 0){
                for (Item item : itemInPlayerInventory) {
                    item.look();
                    confirmCompletedObjectif(itemInPlayerInventory.get(0));
                }
            }else{
                //look at object in place
                //if look is at Container, we'll get all the objects inside

                //insideItems is the items that are in a container,
                // if args[1] doesn't match with a container, insideItems = null
                // is only use to put the items from a container to a place
                putInsideItemsInPlace(place.describeItem(args[1]));


                /*
                 * get instance of item with name args[1] to confirm is objectif is completed
                 * only get the first position because all the items are called differently
                 * */
                List<Item> tmp = place.getContains().stream()
                        .filter(i -> i.toString().equalsIgnoreCase(args[1]))
                        .collect(Collectors.toList());
                //confirm that there's at least one item in the room called like args[1]
                if (tmp.size() > 0){
                    confirmCompletedObjectif(tmp.get(0));
                }
            }

        }
    }

    private void confirmCompletedObjectif(Item item) {
        // ajout la confirmation ici...
        // change this message with the confirmation you need
    	if(this.objective.trigger(item, this.gamemap))
    	{
    		System.out.println(Message.telephoneVibrates);
    	}
        
    }

    //insideItems is the items that are in a container,
    // null if there aren't items inside
    private void putInsideItemsInPlace(List<Item> describeItem) {
        List<Item> insideItems = describeItem;
        if (insideItems != null){
            //put all the objects inside place
            for (Item insideItem : insideItems) {
                place.addItem(insideItem);

            }
        }
    }

    public void help() {
        System.out.println(Message.gameHelp);
        Arrays.asList(Commande.values()).forEach(System.out::println);
    }

    private void use(String commande) {
        String args[] = commande.split(" ");

        if (args.length > 1)
        {
            List<Usable> usableObjects = player.getUsableObjects();
            if (usableObjects.size() > 0)
            {
                switch (args[1].toUpperCase())
                {
                    case "KEY" :
                        useChestWithKey(usableObjects);
                        useDoor(usableObjects);
                        break;
                    case "EXTINGUISHER":
                        useExtinguiser(usableObjects);
                        break;
                    case "TELEPHONE":
                        //there's no possible way to throw the exception NotRightKey in here...
                        // so ignored
                        try {
                            telephone.use(this.objective);
                        } catch (NotRightKey ignored) {}

                        break;
                    default:
                        System.out.println("what am i supposed to use?");
                        break;
                }
            }
            else
            {
                System.out.println(Message.gameErrorHelp);
            }
        }
        else
        {
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
        for (Exitwithkey exit : exits) 
        {
            for (int i = 0; i < keys.size(); i++) 
            {
                if (exit.islock())
                {
                    exit.unlock(keys.get(i));
                    if (!exit.islock())
                    {
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

    private void go(String commande) 
    {
        String[] args = commande.split(" ", 2);
        try 
        {
            if (args.length > 1)
            {
                Exit tmp = place.select(args[1]);
                place = (Room) tmp.nextPlace();
                place.checkDirection();
                System.out.println(place.getDescription());
                place.describePlace();
                if (this.objective.isAccomplished(place))
                {
                	System.out.println(Message.telephoneVibrates);
                }
                
            }
            else 
            {
                System.out.println("Where am i going?");
            }
        } 
        catch (ExitPlaceException | PlaceException e) 
        {
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
