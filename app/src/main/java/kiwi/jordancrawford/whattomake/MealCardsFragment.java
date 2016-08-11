package kiwi.jordancrawford.whattomake;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import org.json.JSONException;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;


/**
 * Displays a card view of the meals.
 */
public class MealCardsFragment extends Fragment {
    private RecyclerView recyclerView;
    private RecyclerView.Adapter recyclerViewAdapter;
    private RecyclerView.LayoutManager recyclerViewLayoutManager;

    // Setup a listener that opens a meal detail view when it is touched.
    private class OnMealClickListener implements OnClickListener<Meal> {
        public OnMealClickListener() {
        }

        @Override
        public void onItemClick(int itemIndex) {
            Intent intent = new Intent(getContext(), MealDetailActivity.class);
            intent.putExtra(MealDetailActivity.INTENT_MEAL_INDEX_KEY, itemIndex);
            startActivity(intent);
        }
    }

    public MealCardsFragment() {
        // Required empty public constructor
    }

    public static MealCardsFragment newInstance() {
        MealCardsFragment fragment = new MealCardsFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // TODO: Don't do this here!
        final ArrayList<Meal> meals;
        try {
            meals = SampleDataHelper.getSampleData(getResources().openRawResource(R.raw.sampledata));
            Collections.sort(meals);
        } catch(IOException exception) {
            System.out.println("Could not read the sample data.");
            return;
        } catch(JSONException exception) {
            System.out.println("The sample data file is invalid.");
            return;
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        final ArrayList<Meal> meals = SampleDataHelper.allMeals;

        // Inflate the layout for this fragment
        View inflatedView = inflater.inflate(R.layout.fragment_meal_cards, container, false);

        // Find the recycler view.
        recyclerView = (RecyclerView) inflatedView.findViewById(R.id.meal_list_recycler_view);

        // Setup a linear layout manager.
        recyclerViewLayoutManager = new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(recyclerViewLayoutManager);
        recyclerViewAdapter = new MealListAdapter(getContext(), meals, new OnMealClickListener());
        recyclerView.setAdapter(recyclerViewAdapter);

        return inflatedView;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
    }

    @Override
    public void onDetach() {
        super.onDetach();
    }

    public void onMyIngredientsChanged() {
        // Using this method to notify of data changes shows an animation.
            // Ideally this would determine which items need to move, but for now just animate all of them.
        recyclerViewAdapter.notifyItemRangeChanged(0, SampleDataHelper.allMeals.size());
    }
}
