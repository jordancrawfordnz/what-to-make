package kiwi.jordancrawford.whattomake;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import org.w3c.dom.Text;

/**
 * Created by Jordan on 8/08/16.
 */
public class RecipeStepsListAdapter extends RecyclerView.Adapter<RecipeStepsListAdapter.RecipeStepViewHolder> {
    String[] steps;
    public RecipeStepsListAdapter(String[] steps) {
        this.steps = steps;
    }

   public class RecipeStepViewHolder extends RecyclerView.ViewHolder {
        View view;
        TextView stepNumber, stepInstruction;

        public RecipeStepViewHolder(View view) {
            super(view);
            this.view = view;

            this.stepNumber = (TextView) view.findViewById(R.id.recipe_step_number);
            this.stepInstruction = (TextView) view.findViewById(R.id.recipe_step_instruction);
        }

        public void setupStep(int index, String step) {
            this.stepNumber.setText(String.valueOf(index+1));
            this.stepInstruction.setText(step);
        }
    }

    @Override
    public RecipeStepViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        // Create a view to display the meal.
        View inflatedView = LayoutInflater.from(parent.getContext()).inflate(R.layout.recipe_step, parent, false);
        return new RecipeStepViewHolder(inflatedView);
    }

    @Override
    public void onBindViewHolder(RecipeStepViewHolder holder, int position) {
        String step = steps[position];
        holder.setupStep(position, step);
    }

    @Override
    public int getItemCount() {
        return steps.length;
    }
}
