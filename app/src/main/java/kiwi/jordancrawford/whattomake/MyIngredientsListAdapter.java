package kiwi.jordancrawford.whattomake;

import android.app.Activity;
import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by Jordan on 10/08/16.
 */
public class MyIngredientsListAdapter extends ArrayAdapter<Ingredient> {
    ArrayList<Ingredient> ingredients;
    Context context;
    int layoutResourceId;

    public MyIngredientsListAdapter(Context context, int layoutResourceId, ArrayList<Ingredient> ingredients) {
        super(context, layoutResourceId, ingredients);
        this.layoutResourceId = layoutResourceId;
        this.context = context;
        this.ingredients = ingredients;
    }

    @Override
    public View getView(int position, View view, ViewGroup parent) {
        final Ingredient ingredient = ingredients.get(position);
        // If no view, inflate the view.
        if (view == null) {
            view = LayoutInflater.from(context).inflate(layoutResourceId, parent, false);
        }

        // Setup this view.
        TextView ingredientName = (TextView) view.findViewById(R.id.ingredient_drawer_item_name);
        CheckBox ingredientChosenCheckbox = (CheckBox) view.findViewById(R.id.ingredient_drawer_item_checkbox);
        ingredientName.setText(ingredient.getName());
        ingredientChosenCheckbox.setChecked(ingredient.isAvaliable());

        return view;
    }
}
