package kiwi.jordancrawford.whattomake;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import java.util.ArrayList;

/**
 * Created by Jordan on 11/08/16.
 */
public class MealDetailPagerAdapter extends FragmentStatePagerAdapter {
    private ArrayList<Meal> meals;
    public MealDetailPagerAdapter(FragmentManager fm, ArrayList<Meal> meals) {
        super(fm);
        this.meals = meals;
    }

    @Override
    public Fragment getItem(int position) {
        // Setup a fragment for this item.
        return (Fragment)MealDetailFragment.newInstance(meals.get(position));
    }

    @Override
    public int getCount() {
        return meals.size();
    }
 }
