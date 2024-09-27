package recipe.foodbar.usecase.recipe.ds;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.bson.types.ObjectId;
import recipe.foodbar.entities.Cuisine;

import recipe.foodbar.entities.Ingredient;
import recipe.foodbar.entities.Review;
import recipe.foodbar.entities.User;

import java.util.ArrayList;
import java.util.Date;

@AllArgsConstructor
@Builder
@Data
@NoArgsConstructor
public class RecipeRequestModel {
    private String id;
    private String title;
    private Cuisine cuisine;
    private ArrayList<String> instructions;
    private ArrayList<String> dietaryRestrictions;
    private float portionSize;
    @Builder.Default
    private ArrayList<Review> reviews = new ArrayList<>();
    @Builder.Default
    private ArrayList<Ingredient> ingredients = new ArrayList<>();
    @Builder.Default
    private ArrayList<String> likers = new ArrayList<>();
    @Builder.Default
    private ArrayList<String> dislikers = new ArrayList<>();
}
