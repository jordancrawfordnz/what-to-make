package kiwi.jordancrawford.whattomake;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 * Created by Jordan on 4/08/16.
 */
public class MealListAdapter extends RecyclerView.Adapter<MealListAdapter.MealViewHolder> {
    String[] strings;
    public MealListAdapter(String[] strings) {
        // TODO: Switch to Meals.
        this.strings = strings;
    }

    /**
     * Define a ViewHolder that keeps track of view fields for meals.
     */
    public class MealViewHolder extends RecyclerView.ViewHolder {
        public TextView mealCardName;
        public MealViewHolder(View view) {
            super(view);

            // Get the text view from insight the inflatedView.
            this.mealCardName = (TextView)view.findViewById(R.id.meal_card_name);
        }
    }

    /**
     * When a view holder is created, inflate the layout and setup a ViewHolder object.
     *
     * @param parent
     * @param viewType
     * @return
     */
    @Override
    public MealViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        // Create a view to display the meal.
        View inflatedView = LayoutInflater.from(parent.getContext()).inflate(R.layout.meal_card, parent, false);

        return new MealViewHolder(inflatedView);
    }

    /**
     * When a view holder is bound to the view, setup the view holder with data.
     *
     * @param holder
     * @param position
     */
    @Override
    public void onBindViewHolder(MealViewHolder holder, int position) {
        // Get the item for this index.
        String currentItem = strings[position];

        // Setup the view holder with the data.
        holder.mealCardName.setText(currentItem);
    }

    /**
     * Return the length of the dataset provided.
     * @return
     */
    @Override
    public int getItemCount() {
        return strings.length;
    }
}
