package kiwi.jordancrawford.whattomake;

import android.content.Intent;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.ImageView;
import android.widget.TextView;

public class MealDetailActivity extends AppCompatActivity {
    public static final String INTENT_MEAL_KEY = "kiwi.jordancrawford.whattomake.intent_meal";
    private RecyclerView stepsRecyclerView;
    private RecyclerView.Adapter stepsRecyclerViewAdapter;
    private RecyclerView.LayoutManager stepsRecyclerViewLayoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_meal_detail);

        Intent intent = getIntent();
        Meal meal = (Meal) intent.getSerializableExtra(INTENT_MEAL_KEY);

        TextView mealDescriptionView = (TextView)findViewById(R.id.meal_detail_description);
        mealDescriptionView.setText((meal.getDescription()));

        ImageView mealPictureView = (ImageView)findViewById(R.id.meal_detail_picture);
        Integer drawableId = getResources().getIdentifier(meal.getPictureResourceName(), "drawable", getPackageName());

        // Only show images if one exists.
        if (drawableId != null) {
            mealPictureView.setImageResource(drawableId);
        }

        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle(meal.getName());

        // Find the recycler view.
        stepsRecyclerView = (RecyclerView) findViewById(R.id.step_list_recycler_view);

        // Setup a linear layout manager.
        stepsRecyclerViewLayoutManager = new LinearLayoutManager(this);
        stepsRecyclerView.setLayoutManager(stepsRecyclerViewLayoutManager);

        stepsRecyclerViewAdapter = new RecipeStepsListAdapter(meal.getSteps());
        stepsRecyclerView.setAdapter(stepsRecyclerViewAdapter);
    }
}
