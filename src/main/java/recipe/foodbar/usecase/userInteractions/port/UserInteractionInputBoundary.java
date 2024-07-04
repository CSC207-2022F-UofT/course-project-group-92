package recipe.foodbar.usecase.userInteractions.port;

import recipe.foodbar.usecase.userInteractions.ds.InteractRequestModel;
import recipe.foodbar.usecase.userInteractions.ds.UserRecipeRequestModel;
import recipe.foodbar.usecase.userInteractions.ds.UserRecipeResponseModel;

public interface UserInteractionInputBoundary {

    String follow(InteractRequestModel interactRequestModel);

    String unfollow(InteractRequestModel interactRequestModel);

    UserRecipeResponseModel getRecipes(UserRecipeRequestModel userRecipeRequestModel);
}
