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
public class Letter extends Object {
        private String message ;
        public Letter (String message,String name)
        {
            super(name);
          
            this.message=message;
        }
        
       
       @Override
        public Object take()
        {
            return super.take();
        }
        @Override
        public void look()
        {
            System.out.println(message);
        }
    
}
