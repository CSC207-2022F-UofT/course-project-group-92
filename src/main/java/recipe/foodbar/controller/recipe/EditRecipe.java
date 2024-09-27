package recipe.foodbar.controller.recipe;

import recipe.foodbar.presenter.RecipePresenter;
import recipe.foodbar.usecase.recipe.ds.RecipeEditedResponseModel;
import recipe.foodbar.usecase.recipe.ds.RecipeRequestModel;
import recipe.foodbar.usecase.recipe.manager.edit.IRecipeEditor;

/**
 * A controller to edit a recipe.
 */
public class EditRecipe {
    private final RecipeRequestModel rm;
    private final IRecipeEditor e;
    private final RecipePresenter rp;

    /**
     * Constructor for EditRecipe
     *
     * @param rm request to edit.
     * @param e   An editor interface.
     */
    public EditRecipe(RecipeRequestModel rm, IRecipeEditor e, RecipePresenter rp) {
        this.rm = rm;
        this.e = e;
        this.rp = rp;

    }

    /**
     * Edits a recipe.
     *
     */
    public void edit() {
        e.editTitle(this.rm);
        e.editPortionSize(this.rm);
        e.editCuisine(this.rm);
        e.editDietaryRestrictions(this.rm);
        e.editIngredients(this.rm);
        e.editInstructions(this.rm);
        rp.displayEdited(RecipeEditedResponseModel.ResponseDataType.EDIT);
    }
}
