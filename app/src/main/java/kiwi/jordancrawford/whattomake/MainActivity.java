package kiwi.jordancrawford.whattomake;

import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.content.Intent;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.CheckBox;
import android.widget.ListView;

import org.json.JSONException;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;

public class MainActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private RecyclerView.Adapter recyclerViewAdapter;
    private RecyclerView.LayoutManager recyclerViewLayoutManager;
    private DrawerLayout drawerLayout;

    private final int DRAWER_GRAVITY = GravityCompat.END;

    private class OnMealClickListener implements OnClickListener<Meal> {
        MainActivity activityInstance;
        public OnMealClickListener(MainActivity activityInstance) {
            this.activityInstance = activityInstance;
        }

        @Override
        public void onItemClick(int itemIndex) {
            Intent intent = new Intent(activityInstance, MealDetailActivity.class);
            intent.putExtra(MealDetailActivity.INTENT_MEAL_INDEX_KEY, itemIndex);
            startActivity(intent);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();

        inflater.inflate(R.menu.main_activity_bar, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
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
        final ArrayList<Meal> meals;
        try {
            // TODO: Don't do this here!
            meals = SampleDataHelper.getSampleData(getResources().openRawResource(R.raw.sampledata));
            Collections.sort(meals);
        } catch(IOException exception) {
            System.out.println("Could not read the sample data.");
            return;
        } catch(JSONException exception) {
            System.out.println("The sample data file is invalid.");
            return;
        }
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        drawerLayout = (DrawerLayout)findViewById(R.id.ingredient_drawer_layout);

        // Setup the ingredient list.
        ListView ingredientListView = (ListView)findViewById(R.id.ingredient_drawer_list);
        ingredientListView.setAdapter(new MyIngredientsListAdapter(this, R.layout.ingredient_drawer_item, SampleDataHelper.allIngredients));

        ingredientListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                CheckBox ingredientChosenCheckbox = (CheckBox) view.findViewById(R.id.ingredient_drawer_item_checkbox);
                Ingredient ingredient = SampleDataHelper.allIngredients.get(i);
                ingredient.setAvaliable(!ingredient.isAvaliable());
                ingredientChosenCheckbox.setChecked(ingredient.isAvaliable());
                Collections.sort(meals);

                // Using this method to notify of data changes shows an animation.
                    // Ideally this would determine which items need to move, but for now just animate all of them.
                recyclerViewAdapter.notifyItemRangeChanged(0, meals.size());
            }
        });

        // Find the recycler view.
        recyclerView = (RecyclerView) findViewById(R.id.meal_list_recycler_view);

        // Setup a linear layout manager.
        recyclerViewLayoutManager = new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(recyclerViewLayoutManager);
        recyclerViewAdapter = new MealListAdapter(this.getApplicationContext(), meals, new OnMealClickListener(this));
        recyclerView.setAdapter(recyclerViewAdapter);
    }
}
