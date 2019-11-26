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
public class Extinglisher extends Object {
    private int lvlWater ;
    public Extinglisher(String name,int lvlWater)
    {
        super(name);
        this.lvlWater=lvlWater;
        
    }
    
    @Override
    public Object take()
    {
        return super.take();
    }
    @Override
    public void look()
    {
        System.out.println(name);
    }
}
