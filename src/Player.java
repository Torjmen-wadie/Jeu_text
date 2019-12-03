/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


import java.util.*;

public class Player {
    private String nameP ;
    private List<Portable> objects ;
    private int hopless;
    private final int MAX_OBJ =5 ;
    
    public Player (String nameP)
    {
        this.nameP=nameP ;
        objects =new ArrayList<>();
      
    }
    public String statut ()
    {
        return this.nameP;
    }
   
    public int getHopless()
    {
        return this.hopless;
    }
     @Override
    public String toString() {
        return "Player{" +
                "name='" + nameP + ' ' + "hopless='" + hopless +
                '}';
    }

    public List<Portable> getObjects(){
        return this.objects;
    }

    public void addInventor(Portable obj)
   {
       if((objects.size() < MAX_OBJ) && (!objects.contains(obj)) && (obj!=null))
       {
       objects.add(obj);
       }
       else if (objects.contains(obj))
       {
           System.out.println(Message.playerErrorAdd);
       }
       else if (obj==null)
       {
           System.out.println(Message.playerAddNull);
       }
       else
           {
           System.out.println(Message.playerAddSat);
           }

       
   }


   public List<Usable> getUsableObjects(){
        List<Usable> usables = new ArrayList<>();

        objects.forEach(portable -> {
            if (portable instanceof  Usable){
                usables.add( (Usable) portable);
            }
        });

        return usables;
   }

   public void useObject(String obj, Place place){
        /*objects.stream().
                filter(portable -> portable.getClass().getSimpleName().equalsIgnoreCase(obj)).
                forEach( portable -> ((Usable) portable).use() );*/
   }
/*get(i).getClass().toString()equalsIgnoreCase("Key")  ||
                   objects.get(i).getClass().toString().equalsIgnoreCase("Extinguisher") ||
                   objects.get(i).getClass().toString().equalsIgnoreCase("Telephone"))*/
   public boolean hasUsableObject()
   {
       boolean flag = false;
       for(int i=0;i<objects.size()-1;i++)
       {
           if (objects.get(i) instanceof Usable){
                flag = true;
           }

       }
       return flag;
       
   }
   public List<Usable> listUsableObject()
   {
        List<Usable>listObject = new ArrayList<Usable>();
       for (int i=0 ;i<objects.size()-1;i++)
           if (objects.get(i) instanceof  Usable)
           {
               listObject.add((Usable) objects.get(i));
           }
       return listObject ;
   }
   public void displayListeUsableObject()
   {
       List <Usable> displayList=listUsableObject();
       for(int i=0 ; i<=displayList.size()-1 ; i++)
       System.out.println(displayList.get(i));
   }

   public List<Portable> getObject()
   {
       return objects;
   }

   public void use(Usable obj)
   {
       //obj.use();
   }
   public Portable deleteUsableObject(String obj)
   {
       Portable tmp = null;
       for(int i=0 ; i< objects.size();i++) {
           if (objects.get(i).toString().equalsIgnoreCase(obj)) {
               tmp = objects.get(i);
               objects.remove(tmp);
           }
       }
       return tmp;

   }

    
    public void look()  {
      System.out.println(objects);
    }

 
    
    
}
