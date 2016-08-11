package kiwi.jordancrawford.whattomake;

import android.support.v4.app.FragmentManager;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import java.util.ArrayList;
import java.util.Collections;

public class MainActivity extends AppCompatActivity implements MyIngredientsFragment.OnFragmentInteractionListener {
    private DrawerLayout drawerLayout;

    private final int DRAWER_GRAVITY = GravityCompat.END;

    private MyIngredientsFragment myIngredientsFragment;
    private MealCardsFragment mealCardsFragment;


    @Override
    public void myIngredientsChanged() {
        ArrayList<Meal> meals = SampleData.getAllMeals();
        Collections.sort(meals);

        mealCardsFragment.onMyIngredientsChanged();
    }

    // Inflate the action bar menu's options.
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();

        inflater.inflate(R.menu.main_activity_bar, menu);
        return super.onCreateOptionsMenu(menu);
    }

    // Handle actions in the action bar.
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            // If "My Ingredients" is pressed, toggle the drawer.
            case R.id.action_open_my_ingredients: {
                if (drawerLayout.isDrawerOpen(DRAWER_GRAVITY)) {
                    drawerLayout.closeDrawer(DRAWER_GRAVITY);
                } else {
                    drawerLayout.openDrawer(DRAWER_GRAVITY);
                }
                return true;
            }
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        drawerLayout = (DrawerLayout)findViewById(R.id.ingredient_drawer_layout);

        // Setup the meal detail fragment.
        FragmentManager fragmentManager = getSupportFragmentManager();
        myIngredientsFragment = (MyIngredientsFragment) fragmentManager.findFragmentById(R.id.my_ingredients_frame);
        mealCardsFragment = (MealCardsFragment) fragmentManager.findFragmentById(R.id.meal_cards_frame);
    }
}
