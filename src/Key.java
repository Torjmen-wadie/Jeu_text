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
public class key extends Object{
    private int code ;

    public key(String name ,int code) {
        super(name);
        this.code=code;
    }

    @Override 
    public Object take()
    {
        return super.take();
    }
    public void look()
    {
        System.out.println(code);
    }
    
}
