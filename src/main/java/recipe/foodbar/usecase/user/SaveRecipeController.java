package recipe.foodbar.usecase.user;

import recipe.foodbar.entities.Recipe.Recipe;
import recipe.foodbar.entities.User;

public class SaveRecipeController {

    public final SaveRecipeInputBoundary inputBoundary;

    public SaveRecipeController(SaveRecipeInputBoundary inputBoundary){
        this.inputBoundary = inputBoundary;
    }


    public SaveRecipeData saveRecipe(User saver, Recipe recipeGiven) {
        SaveRecipeData saveRecipeData = new SaveRecipeData(saver, recipeGiven);
        return saveRecipeData;
    }
}
