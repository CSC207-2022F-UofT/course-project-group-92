//package recipe.foodbar.android.foodbar.ui.home;
//
//import android.text.Editable;
//import android.text.TextWatcher;
//import android.util.Log;
//import android.view.LayoutInflater;
//import android.view.View;
//import android.view.ViewGroup;
//import android.widget.TextView;
//
//import androidx.annotation.NonNull;
//import androidx.recyclerview.widget.RecyclerView;
//
//import com.google.android.material.textfield.TextInputEditText;
//
//import java.util.ArrayList;
//
//import recipe.foodbar.android.foodbar.R;
//import recipe.foodbar.android.foodbar.api.model.Ingredient;
//import recipe.foodbar.android.foodbar.api.model.Recipe;
//import recipe.foodbar.android.foodbar.databinding.IngredientItemBinding;
//
//
//public class RecipeAdapter extends RecyclerView.Adapter<RecipeAdapter.ViewHolder> {
//
//    public ArrayList<Recipe> recipes;
//
//    /**
//     * Provide a reference to the type of views that you are using
//     * (custom ViewHolder).
//     */
//    public static class ViewHolder extends RecyclerView.ViewHolder {
//        private TextView titleTextView;
//
//        public ViewHolder(View view) {
//            super(view);
//            // Define click listener for the ViewHolder's View
//
//            titleTextView = view.findViewById(R.id.recipe_title)
//        }
//
////        public TextView getTextView() {
////            return textView;
////        }
//    }
//
//    /**
//     * Initialize the dataset of the Adapter.
//     *
//     * @param dataSet String[] containing the data to populate views to be used
//     *                by RecyclerView.
//     */
//    public RecipeAdapter(ArrayList<Recipe> dataSet) {
//        recipes = dataSet;
//    }
//
//    // Create new views (invoked by the layout manager)
//    @Override
//    public RecipeAdapter.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
//        // Create a new view, which defines the UI of the list item
//        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.recipe_item, viewGroup, false);
//        return new ViewHolder(view);
//    }
//
//    // Replace the contents of a view (invoked by the layout manager)
//    @Override
//    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
//
//        // Get element from your dataset at this position and replace the
//        // contents of the view with that element
//        TextView titleTextView = holder.titleTextView;
//        titleTextView.setText(recipes.get(position).getTitle());
//    }
//
//    // Return the size of your dataset (invoked by the layout manager)
//    @Override
//    public int getItemCount() {
//        return recipes.size();
//    }
//}
//
//
