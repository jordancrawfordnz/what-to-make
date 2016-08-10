package kiwi.jordancrawford.whattomake;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;

/**
 * Created by Jordan on 8/08/16.
 */
public class Meal implements Serializable, Comparable<Meal> {
    private String name, description, pictureResourceName;
    private String[] steps;
    private ArrayList<Ingredient> ingredients;

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

    public ArrayList<Ingredient> getIngredients() {
        return ingredients;
    }

    public void setIngredients(ArrayList<Ingredient> ingredients) {
        this.ingredients = ingredients;
    }

    public String getPictureResourceName() {
        return pictureResourceName;
    }

    public void setPictureResourceName(String pictureResourceName) {
        this.pictureResourceName = pictureResourceName;
    }

    public int getNumberOfAvaliableIngredients() {
        int avaliableIngredients = ingredients.size();
        for (Ingredient ingredient : ingredients) {
            if (!ingredient.isAvaliable()) {
                avaliableIngredients--;
            }
        }
        return avaliableIngredients;
    }

    public int getNumberOfMissingIngredients() {
        return ingredients.size() - getNumberOfAvaliableIngredients();
    }

    @Override
    public String toString() {
        return "Meal{" +
                "name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", pictureResourceName='" + pictureResourceName + '\'' +
                ", steps=" + Arrays.toString(steps) +
                ", ingredients=" + ingredients.toString() +
                '}';
    }

    @Override
    public int compareTo(Meal meal) {
        int thisMealMissingIngredients = getNumberOfMissingIngredients();
        int otherMealMissingIngredients = meal.getNumberOfMissingIngredients();
        if (thisMealMissingIngredients == otherMealMissingIngredients)
            return meal.getIngredients().size() - getIngredients().size();
        else
            return thisMealMissingIngredients - otherMealMissingIngredients;
    }
}
