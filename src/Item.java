import exceptions.ContainerCarryException;

import java.util.Objects;

public class Item {
    private String name;

    public Item(String name) {
        this.name = name;
    }
  //-----------------------------------------------------------------------------------------------
    public Item take() throws ContainerCarryException {
        return this;
    }
  //-----------------------------------------------------------------------------------------------
   
    public void look() {
        System.out.println(name);
    }
  //-----------------------------------------------------------------------------------------------
    //2 items are equals when their name is the same
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null) return false;
        return name.equals(o.toString());
    }
  //-----------------------------------------------------------------------------------------------
    @Override
    public int hashCode() {
        return Objects.hash(name);
    }
  //-----------------------------------------------------------------------------------------------
    @Override
    public String toString() {
        return name;
    }
}