package kiwi.jordancrawford.whattomake;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

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

public class MainActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private RecyclerView.Adapter recyclerViewAdapter;
    private RecyclerView.LayoutManager recyclerViewLayoutManager;

    private Meal buildMealFromJSON(JSONObject jsonMeal) throws JSONException {
        Meal meal = new Meal();
        meal.setName(jsonMeal.getString("name"));
        meal.setDescription(jsonMeal.getString("description"));
        return meal;
    }

    private ArrayList<Meal> getSampleData() throws IOException, JSONException {
        // Read from the JSON file.
        InputStream jsonInput = getResources().openRawResource(R.raw.sampledata);
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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        ArrayList<Meal> meals;
        try {
            meals = getSampleData();
        } catch(IOException exception) {
            System.out.println("Could not read the sample data.");
            return;
        } catch(JSONException exception) {
            System.out.println("The sample data file is invalid.");
            return;
        }
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Find the recycler view.
        recyclerView = (RecyclerView) findViewById(R.id.list_recycler_view);

        // Setup a linear layout manager.
        recyclerViewLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(recyclerViewLayoutManager);

        recyclerViewAdapter = new MealListAdapter(meals);
        recyclerView.setAdapter(recyclerViewAdapter);
    }
}
