/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package poo;
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
       if(objects.size() <MAX_OBJ)
       {
       objects.add(obj);
       } 
       
   }
   public void use(Portable obj)
   {
       obj.use();
   }

    
    public void look()  {
      System.out.println(objects);
    }

 
    
    
}