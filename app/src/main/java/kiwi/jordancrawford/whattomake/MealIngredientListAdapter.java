package kiwi.jordancrawford.whattomake;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by Jordan on 9/08/16.
 */
public class MealIngredientListAdapter extends RecyclerView.Adapter<MealIngredientListAdapter.MealIngredientViewHolder> {
    ArrayList<Ingredient> ingredients;
    public MealIngredientListAdapter(ArrayList<Ingredient> ingredients) {
        this.ingredients = ingredients;
    }

    public class MealIngredientViewHolder extends RecyclerView.ViewHolder {
        View view;
        TextView ingredientName, ingredientAvaliable;

        public MealIngredientViewHolder(View view) {
            super(view);
            this.view = view;

            this.ingredientName = (TextView) view.findViewById(R.id.recipe_ingredient_name);
            this.ingredientAvaliable = (TextView) view.findViewById(R.id.recipe_ingredient_avaliable);
        }

        public void setupIngredient(Ingredient ingredient) {
            ingredientName.setText(ingredient.getName());
            ingredientAvaliable.setText(ingredient.isAvaliable() ? "Avaliable" : "Not avaliable");
        }
    }

    @Override
    public MealIngredientViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        // Create a view to display the ingredient.
        View inflatedView = LayoutInflater.from(parent.getContext()).inflate(R.layout.recipe_ingredient, parent, false);
        return new MealIngredientViewHolder(inflatedView);
    }

    @Override
    public void onBindViewHolder(MealIngredientViewHolder holder, int position) {
        holder.setupIngredient(ingredients.get(position));
    }

    @Override
    public int getItemCount() {
        return ingredients.size();
    }
}
