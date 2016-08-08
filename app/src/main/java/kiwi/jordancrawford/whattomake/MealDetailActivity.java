package kiwi.jordancrawford.whattomake;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class MealDetailActivity extends AppCompatActivity {

    public static final String INTENT_MEAL_KEY = "kiwi.jordancrawford.whattomake.intent_meal";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_meal_detail);

        Intent intent = getIntent();
        Meal meal = (Meal) intent.getSerializableExtra(INTENT_MEAL_KEY);

        TextView mealNameView = (TextView)findViewById(R.id.meal_detail_name);
        mealNameView.setText(meal.getName());

        TextView mealDescriptionView = (TextView)findViewById(R.id.meal_detail_description);
        mealDescriptionView.setText((meal.getDescription()));

    }
}
