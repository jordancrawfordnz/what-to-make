package kiwi.jordancrawford.whattomake;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link MealDetailFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link MealDetailFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class MealDetailFragment extends Fragment {
    private static final String MEAL = "meal";

    private Meal meal;
    private RecyclerView stepsRecyclerView;
    private RecyclerView.Adapter stepsRecyclerViewAdapter;
    private RecyclerView.LayoutManager stepsRecyclerViewLayoutManager;
    private RecyclerView ingredientsRecyclerView;
    private RecyclerView.Adapter ingredientsRecyclerViewAdapter;
    private RecyclerView.LayoutManager ingredientsRecyclerViewLayoutManager;

    private OnFragmentInteractionListener mListener;

    public MealDetailFragment() {
        // Required empty public constructor
    }

    /**
     * Create a Meal detail fragment with the meal as a parameter.
     *
     * @param meal The meal to display.
     * @return A new instance of fragment MealDetailFragment.
     */
    public static MealDetailFragment newInstance(Meal meal) {
        MealDetailFragment fragment = new MealDetailFragment();
        Bundle args = new Bundle();
        args.putSerializable(MEAL, meal);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            meal = (Meal)getArguments().getSerializable(MEAL);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        View fragmentView = inflater.inflate(R.layout.fragment_meal_detail, container, false);

        TextView mealDescriptionView = (TextView)fragmentView.findViewById(R.id.meal_detail_description);
        mealDescriptionView.setText((meal.getDescription()));

        ImageView mealPictureView = (ImageView)fragmentView.findViewById(R.id.meal_detail_picture);
        Integer drawableId = getResources().getIdentifier(meal.getPictureResourceName(), "drawable", getContext().getPackageName());

        // Only show images if one exists.
        if (drawableId != null) {
            mealPictureView.setImageResource(drawableId);
        }

        // Setup a list of recipe steps.
        stepsRecyclerView = (RecyclerView) fragmentView.findViewById(R.id.step_list_recycler_view);
        stepsRecyclerViewLayoutManager = new LinearLayoutManager(getContext());
        stepsRecyclerView.setLayoutManager(stepsRecyclerViewLayoutManager);

        stepsRecyclerViewAdapter = new RecipeStepsListAdapter(meal.getSteps());
        stepsRecyclerView.setAdapter(stepsRecyclerViewAdapter);

        // Setup a list of the meals ingredients.
        ingredientsRecyclerView = (RecyclerView) fragmentView.findViewById(R.id.ingredient_list_recycler_view);
        ingredientsRecyclerViewLayoutManager = new LinearLayoutManager(getContext());
        ingredientsRecyclerView.setLayoutManager(ingredientsRecyclerViewLayoutManager);

        ingredientsRecyclerViewAdapter = new MealIngredientListAdapter(meal.getIngredients());
        ingredientsRecyclerView.setAdapter(ingredientsRecyclerViewAdapter);

        return fragmentView;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p/>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
    }
}
