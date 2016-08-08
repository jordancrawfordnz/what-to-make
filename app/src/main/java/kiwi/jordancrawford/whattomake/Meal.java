package kiwi.jordancrawford.whattomake;

import java.io.Serializable;
import java.util.Arrays;

/**
 * Created by Jordan on 8/08/16.
 */
public class Meal implements Serializable {
    String name, description, pictureResourceName;
    String[] steps, ingredients;

    public Meal() {

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String[] getSteps() {
        return steps;
    }

    public void setSteps(String[] steps) {
        this.steps = steps;
    }

    public String[] getIngredients() {
        return ingredients;
    }

    public void setIngredients(String[] ingredients) {
        this.ingredients = ingredients;
    }

    public String getPictureResourceName() {
        return pictureResourceName;
    }

    public void setPictureResourceName(String pictureResourceName) {
        this.pictureResourceName = pictureResourceName;
    }

    @Override
    public String toString() {
        return "Meal{" +
                "name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", pictureResourceName='" + pictureResourceName + '\'' +
                ", steps=" + Arrays.toString(steps) +
                ", ingredients=" + Arrays.toString(ingredients) +
                '}';
    }

}
