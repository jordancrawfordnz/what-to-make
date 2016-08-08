package kiwi.jordancrawford.whattomake;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.content.Intent;

import org.json.JSONException;

import java.io.IOException;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private RecyclerView.Adapter recyclerViewAdapter;
    private RecyclerView.LayoutManager recyclerViewLayoutManager;

    private class OnMealClickListener implements OnClickListener<Meal> {
        MainActivity activityInstance;
        public OnMealClickListener(MainActivity activityInstance) {
            this.activityInstance = activityInstance;
        }

        @Override
        public void onItemClick(Meal item) {
            Intent intent = new Intent(activityInstance, MealDetailActivity.class);
            intent.putExtra(MealDetailActivity.INTENT_MEAL_KEY, item);
            startActivity(intent);
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        ArrayList<Meal> meals;
        try {
            meals = SampleDataHelper.getSampleData(getResources().openRawResource(R.raw.sampledata));
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
        recyclerView = (RecyclerView) findViewById(R.id.meal_list_recycler_view);

        // Setup a linear layout manager.
        recyclerViewLayoutManager = new GridLayoutManager(this, 2);
        recyclerView.setLayoutManager(recyclerViewLayoutManager);

        recyclerViewAdapter = new MealListAdapter(this.getApplicationContext(), meals, new OnMealClickListener(this));
        recyclerView.setAdapter(recyclerViewAdapter);
    }
}
