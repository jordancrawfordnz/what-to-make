package kiwi.jordancrawford.whattomake;

import android.content.res.Resources;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.StringWriter;
import java.io.Writer;
import java.util.ArrayList;

/**
 * Created by Jordan on 8/08/16.
 */
public class SampleDataHelper {
        // TODO: Put this somewhere nicer.
    public static ArrayList<String> allIngredients = new ArrayList<>();

    public static Meal buildMealFromJSON(JSONObject jsonMeal) throws JSONException {
        Meal meal = new Meal();
        meal.setName(jsonMeal.getString("name"));
        meal.setDescription(jsonMeal.getString("description"));
        meal.setPictureResourceName(jsonMeal.getString("picture"));

        // Populate the steps.
        JSONArray jsonSteps = jsonMeal.getJSONArray("steps");
        String[] steps = new String[jsonSteps.length()];
        for (int currentStepIndex = 0; currentStepIndex < jsonSteps.length(); currentStepIndex++) {
            steps[currentStepIndex] = jsonSteps.getString(currentStepIndex);
        }

        // Populate the ingredients.
        JSONArray jsonIngredients = jsonMeal.getJSONArray("ingredients");
        String[] ingredients = new String[jsonIngredients.length()];
        for (int currentIngredientIndex = 0; currentIngredientIndex < jsonIngredients.length(); currentIngredientIndex++) {
            String currentIngredient = jsonIngredients.getString(currentIngredientIndex);
            ingredients[currentIngredientIndex] = currentIngredient;
            if (!allIngredients.contains(currentIngredient)) {
                allIngredients.add(currentIngredient);
            }
        }

        meal.setIngredients(ingredients);
        meal.setSteps(steps);
        return meal;
    }

    public static ArrayList<Meal> getSampleData(InputStream jsonInput) throws IOException, JSONException {
        // Read from the JSON file.
        Reader reader = new BufferedReader(new InputStreamReader(jsonInput));
        Writer writer = new StringWriter();
        char[] buffer = new char[1024];

        int inputSize;
        try {
            // While there is something to read, read to the buffer.
            while((inputSize = reader.read(buffer)) != -1) {
                writer.write(buffer, 0, inputSize);
            }
        } finally {
            jsonInput.close();
        }
        String inputString = writer.toString();

        // Parse the JSON and build Meals from JSON.
        ArrayList<Meal> meals = new ArrayList<>();
        JSONArray jsonMeals = new JSONArray(inputString);
        for (int mealIndex = 0; mealIndex < jsonMeals.length(); mealIndex++) {
            JSONObject jsonMeal = jsonMeals.getJSONObject(mealIndex);
            meals.add(buildMealFromJSON((jsonMeal)));
        }

        System.out.println("all ingredients");
        System.out.println(allIngredients);
        return meals;
    }

}
