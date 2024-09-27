package recipe.foodbar.presenter;

import recipe.foodbar.usecase.recipe.ds.RecipeEditedResponseModel;
import recipe.foodbar.usecase.recipe.port.RecipeOut;

public class RecipePresenter implements RecipeOut {

    @Override
    public void displayEdited(RecipeEditedResponseModel.ResponseDataType type) {
        System.out.println(RecipeEditedResponseModel.getRecipeResponseText(type));
    }
}
