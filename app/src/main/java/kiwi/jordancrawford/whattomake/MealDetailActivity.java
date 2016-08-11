package kiwi.jordancrawford.whattomake;

import android.content.Intent;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class MealDetailActivity extends AppCompatActivity implements MealDetailFragment.OnFragmentInteractionListener {
    public static final String INTENT_MEAL_INDEX_KEY = "kiwi.jordancrawford.whattomake.intent_meal_index";

    ViewPager pager;
    PagerAdapter pagerAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_meal_detail);

        Intent intent = getIntent();
        int mealIndex = intent.getIntExtra(INTENT_MEAL_INDEX_KEY, 0);

        // Get the meal.
        Meal meal = SampleDataHelper.allMeals.get(mealIndex);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle(meal.getName());
        actionBar.setDisplayHomeAsUpEnabled(true);

            // TODO: Update the title when the page changes.
        pager = (ViewPager) findViewById(R.id.meal_detail_pager);

        // Setup the PagerAdapter.
        pagerAdapter = new MealDetailPagerAdapter(getSupportFragmentManager(), SampleDataHelper.allMeals);
        pager.setAdapter(pagerAdapter);
        pager.setCurrentItem(mealIndex);
    }
}
