package kiwi.jordancrawford.whattomake;

/**
 * Created by Jordan on 8/08/16.
 */
public class Meal {
    String name, description;
    // TODO: Picture

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

    @Override
    public String toString() {
        return "Meal{" +
                "name='" + name + '\'' +
                ", description='" + description + '\'' +
                '}';
    }
}
