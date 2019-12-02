import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PlayerTest {
Player player ;
    @BeforeEach
    void setUp() {
        player=new Player("toto");
    }

    @Test
    void addOneInventor() {
        Portable obj = new Key("key",1234);
        player.addInventor(obj);
        assertEquals(1,player.getObject().size());
    }
    @Test
    void  addFiveInventor()
    {
        Portable obj1=new Key("key1",2345);
        Portable obj2= new Book("you must find the key","java");
        Portable obj3 = new Letter("you must use the key in the desk","Letter");
        Portable obj4 = new Extinguisher("the first",20);
        Portable obj5 =new Telephone("789","Iphone");
        player.addInventor(obj1);
        player.addInventor(obj2);
        player.addInventor(obj3);
        player.addInventor(obj4);
        player.addInventor(obj5);
        assertEquals(5,player.getObject().size());
    }
    @Test
    void addSixInventor()
    {
        Portable obj1=new Key("key1",2345);
        Portable obj2= new Book("you must find the key","java");
        Portable obj3 = new Letter("you must use the key in the desk","Letter");
        Portable obj4 = new Extinguisher("the first",20);
        Portable obj5 =new Telephone("789","Iphone");
        Portable obj6 =new Telephone("1234","Sumssuung");
        player.addInventor(obj1);
        player.addInventor(obj2);
        player.addInventor(obj3);
        player.addInventor(obj4);
        player.addInventor(obj5);
        player.addInventor(obj6);
        assertEquals(5,player.getObject().size());
    }
    @Test
    void addSameInventor()
    {
        Portable obj1=new Key("key1",2345);

        player.addInventor(obj1);
        player.addInventor(obj1);
        player.addInventor(obj1);
        player.addInventor(obj1);
        player.addInventor(obj1);
        assertEquals(1,player.getObject().size());
    }

    @Test
   void addNullInventor()
    {
        player.addInventor(null);
        assertEquals(0,player.getObject().size());
    }

    @Test
    void getOneUsableObjects()
    {
        Portable obj1 =new Key("key",1234);
        Portable obj2 =new Letter("you must go","JavaDoc");
   player.addInventor(obj1);
   player.addInventor(obj2);

   assertEquals(1,player.getUsableObjects().size());
    }
    @Test
    void getThreeUsableObject()
    {
        Portable obj1 =new Key("key",1234);
        Portable obj2 =new Letter("you must go","JavaDoc");
        Portable obj3 =new Extinguisher("Extinglisher1",12);
        Portable obj4 =new Telephone("call me","alex");
  player.addInventor(obj1);
  player.addInventor(obj2);
  player.addInventor(obj3);
  player.addInventor(obj4);
  assertEquals(3,player.getUsableObjects().size());
    }
    @Test
    void getUsableObject()
    {
        Portable obj1 =new Key("key",1234);
        Portable obj2 =new Letter("you must go","JavaDoc");
        Portable obj3 =new Extinguisher("Extinglisher1",12);
        Portable obj4 =new Telephone("call me","alex");
        Portable obj5 =new Book("turn off","how to be happy");
        player.addInventor(obj1);
        player.addInventor(obj2);
        player.addInventor(obj3);
        player.addInventor(obj4);
        player.addInventor(obj5);
        assertEquals(3,player.getUsableObjects().size());
    }
    @Test
    void hasUsableObject()
    {
        Portable obj1 =new Key("key",1234);
        Portable obj2 =new Letter("you must go","JavaDoc");
        player.addInventor(obj1);
        player.addInventor(obj2);
        assertTrue(player.hasUsableObject());
    }
    @Test
    void displayListeUsableObject(){
        Portable obj1 =new Key("key",1234);
        Portable obj2 =new Letter("you must go","JavaDoc");
        player.addInventor(obj1);
        player.addInventor(obj2);
        player.displayListeUsableObject();
    }
    @Test
    void statut(){
        player.statut();
    }
    @Test
     void testtoString()
    {
  player.toString();

    }
    @Test
    void deleteUsableObject()
    {
        String obj="key";
        Portable obj1 =new Key("key",1234);
        Portable obj2 =new Letter("you must go","JavaDoc");
        Portable obj3 =new Extinguisher("Extinglisher1",12);
        Portable obj4 =new Telephone("call me","alex");
        player.addInventor(obj1);
        player.addInventor(obj2);
        player.addInventor(obj3);
        player.addInventor(obj4);

        player.deleteUsableObject(obj);
        assertEquals(3,player.getUsableObjects().size());

    }



}