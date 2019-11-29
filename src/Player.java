/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


import java.util.*;
/**
 *
 * @author Wadie
 */
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

   public void addInventor(Portable obj)
   {
       if(objects.size() < MAX_OBJ)
       {
       objects.add(obj);
       }else{
           System.out.println("I can't take no more object");
       }
       
   }

   public List<Usable> getUsableObjects(){
        List<Usable> usables = new ArrayList<>();

        objects.forEach(portable -> {
            if (portable.getClass().getSimpleName().equalsIgnoreCase("Key")  ||
                    portable.getClass().getSimpleName().equalsIgnoreCase("Extinguisher") ||
                    portable.getClass().getSimpleName().equalsIgnoreCase("Telephone")){
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

   public boolean hasUsableObject()
   {
       boolean flag = false;
       for(int i=0;i<objects.size();i++)
       {
           if (objects.get(i).getClass().toString().equalsIgnoreCase("Key")  ||
                   objects.get(i).getClass().toString().equalsIgnoreCase("Extinguisher") ||
                   objects.get(i).getClass().toString().equalsIgnoreCase("Telephone")){
                return flag = true;
           }

       }
       return flag;
       
   }

   public void use(Usable obj)
   {
       //obj.use();
   }

    
    public void look()  {
      System.out.println(objects);
    }

 
    
    
}
