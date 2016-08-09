package kiwi.jordancrawford.whattomake;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 * Created by Jordan on 9/08/16.
 */
public class MealIngredientListAdapter extends RecyclerView.Adapter<MealIngredientListAdapter.MealIngredientViewHolder> {
    String[] ingredients;
    public MealIngredientListAdapter(String[] ingredients) {
        this.ingredients = ingredients;
    }

    public class MealIngredientViewHolder extends RecyclerView.ViewHolder {
        View view;
        TextView ingredientName;

        public MealIngredientViewHolder(View view) {
            super(view);
            this.view = view;

            this.ingredientName = (TextView) view.findViewById(R.id.recipe_ingredient_name);
        }

        public void setupIngredient(String ingredient) {
            ingredientName.setText(ingredient);
        }
    }

    @Override
    public MealIngredientViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        // Create a view to display the meal.
        View inflatedView = LayoutInflater.from(parent.getContext()).inflate(R.layout.recipe_ingredient, parent, false);
        return new MealIngredientViewHolder(inflatedView);
    }

    @Override
    public void onBindViewHolder(MealIngredientViewHolder holder, int position) {
        holder.setupIngredient(ingredients[position]);
    }

    @Override
    public int getItemCount() {
        return ingredients.length;
    }
}
