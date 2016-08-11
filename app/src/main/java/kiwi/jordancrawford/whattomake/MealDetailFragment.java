package kiwi.jordancrawford.whattomake;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
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
    private LinearLayout stepsLinearView;
    private LinearLayout ingredientsLinearView;

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

        // Setup a list of the meals ingredients.
        ingredientsLinearView = (LinearLayout) fragmentView.findViewById(R.id.ingredient_list_view);
        for (Ingredient currentIngredient : meal.getIngredients()) {
            // Inflate the view.
            View inflatedView = inflater.inflate(R.layout.recipe_ingredient, ingredientsLinearView, false);
            ingredientsLinearView.addView(inflatedView);

            // Setup data in the view.
            TextView ingredientName = (TextView) inflatedView.findViewById(R.id.recipe_ingredient_name);
            ImageView ingredientAvaliable = (ImageView) inflatedView.findViewById(R.id.recipe_ingredient_avaliable);
            ingredientName.setText(currentIngredient.getName());
            if (currentIngredient.isAvaliable())
                ingredientAvaliable.setImageResource(R.drawable.ic_check_circle_black_24dp);
            else
                ingredientAvaliable.setImageResource(R.drawable.ic_close_circle_black_24dp);
        }

        // Setup a list of recipe steps.
        stepsLinearView = (LinearLayout) fragmentView.findViewById(R.id.step_list_recycler_view);
        for (int currentRecipeStepIndex = 0; currentRecipeStepIndex < meal.getSteps().length; currentRecipeStepIndex++) {
            String currentRecipeStep = meal.getSteps()[currentRecipeStepIndex];
            // Inflate the view.
            View inflatedView = inflater.inflate(R.layout.recipe_step, stepsLinearView, false);
            stepsLinearView.addView(inflatedView);

            // Setup data in the view.
            TextView stepNumber = (TextView) inflatedView.findViewById(R.id.recipe_step_number);
            TextView stepInstruction = (TextView) inflatedView.findViewById(R.id.recipe_step_instruction);
            stepNumber.setText(String.valueOf(currentRecipeStepIndex+1));
            stepInstruction.setText(currentRecipeStep);
        }

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
