package kiwi.jordancrawford.whattomake;

import java.io.Serializable;

/**
 * Created by Jordan on 10/08/16.
 */
public class Ingredient implements Serializable {
    private String name;
    private boolean avaliable;

    public Ingredient() {

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isAvaliable() {
        return avaliable;
    }

    public void setAvaliable(boolean avaliable) {
        this.avaliable = avaliable;
    }

    @Override
    public String toString() {
        return "Ingredient{" +
                "name='" + name + '\'' +
                ", avaliable=" + avaliable +
                '}';
    }
}
