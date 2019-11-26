/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import exceptions.ContainerCarryException;

/**
 *
 * @author Wadie
 */
public class Book extends Object implements Portable, Openable{
    private String text ; 
    
    public Book(String text,String name)
    {
        super(name);
        this.text=text;
    }

    @Override
    public void look()
    {
        System.out.println(text);
    }

    @Override
    public void addToInventory() {

    }

    @Override
    public void open() {

    }

    @Override
    public void close() {

    }

}