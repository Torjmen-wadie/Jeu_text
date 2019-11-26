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
public class Object {
    String name;

    public Object(String name) {
        this.name = name;
    }

    public Object take(){
        return this;
    }

    public void look() {
        System.out.println(name);
    }

}