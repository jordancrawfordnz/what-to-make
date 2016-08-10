package kiwi.jordancrawford.whattomake;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.content.Intent;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class MealDetailActivity extends AppCompatActivity implements MealDetailFragment.OnFragmentInteractionListener {
    public static final String INTENT_MEAL_KEY = "kiwi.jordancrawford.whattomake.intent_meal";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_meal_detail);

        Intent intent = getIntent();
        Meal meal = (Meal) intent.getSerializableExtra(INTENT_MEAL_KEY);

        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle(meal.getName());
        actionBar.setDisplayHomeAsUpEnabled(true);

        // Setup a fragment to display the meal detail.
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        Fragment fragment = (Fragment)MealDetailFragment.newInstance(meal);
        fragmentTransaction.add(R.id.meal_detail_fragment_container, fragment);
        fragmentTransaction.commit();
    }
}
