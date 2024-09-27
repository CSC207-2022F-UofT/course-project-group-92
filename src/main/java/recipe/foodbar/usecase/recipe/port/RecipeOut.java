package recipe.foodbar.usecase.recipe.port;

import recipe.foodbar.usecase.recipe.ds.RecipeEditedResponseModel;

/**
 * The output boundary for recipe saving/creation
 */
public interface RecipeOut {
    public default void displayEdited(RecipeEditedResponseModel.ResponseDataType type) {
    }
}
