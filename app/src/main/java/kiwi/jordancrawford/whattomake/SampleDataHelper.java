package kiwi.jordancrawford.whattomake;

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
    public static Meal buildMealFromJSON(JSONObject jsonMeal) throws JSONException {
        Meal meal = new Meal();
        meal.setName(jsonMeal.getString("name"));
        meal.setDescription(jsonMeal.getString("description"));
        meal.setPictureResourceName(jsonMeal.getString("picture"));
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

        return meals;
    }

}
