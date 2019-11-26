/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package poo;

/**
 *
 * @author Wadie
 */
public class Telephone extends Object  {
 private String statut ;
 public Telephone(String statut,String name)
 {
     super(name);
     this.statut=statut;
     
 }
 @Override 
 public Object take()
 {
     return super.take();
 }
 @Override
 public void look(){
 System.out.println(statut);
 } 
 
 @Override
 public String toString() {
        return "Telephone{" +
                "name='" + name + ' ' + "Status='" + statut +
                '}';
    }
 
 
}
