package recipe.foodbar.usecase.recipe.manager.edit;

import recipe.foodbar.interfaces.Editor;
import recipe.foodbar.usecase.recipe.ds.RecipeRequestModel;

/**
 * An input boundary for editing recipes.
 */
public interface IRecipeEditor extends Editor {

    /**
     * Edit the portion size of a recipe.
     *
     * @param rm The request to edit.
     */
    void editPortionSize(RecipeRequestModel rm);

    void editTitle(RecipeRequestModel rm);

    void editCuisine(RecipeRequestModel rm);

    void editDietaryRestrictions(RecipeRequestModel rm);

    boolean editIngredients(RecipeRequestModel rm);

    boolean editInstructions(RecipeRequestModel rm);
}
