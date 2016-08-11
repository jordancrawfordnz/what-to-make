package kiwi.jordancrawford.whattomake;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.CheckBox;
import android.widget.ListView;

import java.util.Collections;


/**
 * Displays the list of all ingredients.
 */
public class MyIngredientsFragment extends Fragment {
    private MyIngredientsFragment.OnFragmentInteractionListener interactionListener;

    public MyIngredientsFragment() {
        // Required empty public constructor
    }

    public static MyIngredientsFragment newInstance() {
        MyIngredientsFragment fragment = new MyIngredientsFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View inflatedView = inflater.inflate(R.layout.fragment_my_ingredients, container, false);

        // Setup the ingredient list.
        final ListView ingredientListView = (ListView)inflatedView.findViewById(R.id.ingredient_drawer_list);
        ingredientListView.setAdapter(new MyIngredientsListAdapter(getContext(), R.layout.ingredient_drawer_item, SampleDataHelper.allIngredients));

        ingredientListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                CheckBox ingredientChosenCheckbox = (CheckBox) view.findViewById(R.id.ingredient_drawer_item_checkbox);
                Ingredient ingredient = SampleDataHelper.allIngredients.get(i);
                ingredient.setAvaliable(!ingredient.isAvaliable());
                ingredientChosenCheckbox.setChecked(ingredient.isAvaliable());

                // Notify the parent that the ingredients have changed.
                if (interactionListener != null) {
                    interactionListener.myIngredientsChanged();
                }
            }
        });

        // Inflate the layout for this fragment
        return inflatedView;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            interactionListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        interactionListener = null;
    }

    public interface OnFragmentInteractionListener {
        void myIngredientsChanged();
    }
}
