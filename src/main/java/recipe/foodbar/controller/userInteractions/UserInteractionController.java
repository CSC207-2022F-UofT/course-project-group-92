package recipe.foodbar.controller.userInteractions;

import recipe.foodbar.usecase.userInteractions.ds.InteractRequestModel;
import recipe.foodbar.usecase.userInteractions.ds.UserRecipeRequestModel;
import recipe.foodbar.usecase.userInteractions.ds.UserRecipeResponseModel;
import recipe.foodbar.usecase.userInteractions.port.UserInteractionInputBoundary;

public class UserInteractionController {
    public final UserInteractionInputBoundary userInteractionInputBoundary;

    /**
     * constructor for the controller class of use case of user interactions
     * @param userInteractionInputBoundary the input boundary interface
     */
    public UserInteractionController(UserInteractionInputBoundary userInteractionInputBoundary){
        this.userInteractionInputBoundary = userInteractionInputBoundary;
    }

    /**
     * creates suitable object in order to use interactor to follow.
     * @param uId1 id of user1
     * @param uId2 id of user2
     * @return a string of whether it is successful.
     */
    public String follow(String uId1, String uId2){
        InteractRequestModel interactRequestModel = new InteractRequestModel(uId1, uId2);

        return userInteractionInputBoundary.follow(interactRequestModel);
    }

    /**
     * creates suitable object in order to use interactor to unfollow.
     * @param uId1 id of user1
     * @param uId2 id of user2
     * @return a string of whether it is successful.
     */
    public String unfollow(String uId1, String uId2){
        InteractRequestModel interactRequestModel = new InteractRequestModel(uId1, uId2);

        return userInteractionInputBoundary.unfollow(interactRequestModel);
    }

    /**
     * creates suitable object in order to use interactor to get Recipes of all following.
     * @param uId id of the user.
     * @return a list of recipes.
     */
    public UserRecipeResponseModel listOf(String uId){
        UserRecipeRequestModel userRecipeRequestModel = new UserRecipeRequestModel(uId);

        return userInteractionInputBoundary.getRecipes(userRecipeRequestModel);
    }
}
