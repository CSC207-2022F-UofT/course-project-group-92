package recipe.foodbar.usecase.userInteractions;

import recipe.foodbar.entities.Recipe;
import recipe.foodbar.entities.User;
import recipe.foodbar.usecase.recipe.port.RecipeRepository;
import recipe.foodbar.usecase.user.port.UserRepository;
import recipe.foodbar.usecase.userInteractions.ds.InteractRequestModel;
import recipe.foodbar.usecase.userInteractions.ds.UserRecipeRequestModel;
import recipe.foodbar.usecase.userInteractions.ds.UserRecipeResponseModel;
import recipe.foodbar.usecase.userInteractions.port.UserInteractionInputBoundary;
import recipe.foodbar.usecase.userInteractions.port.UserInteractionOutputBoundary;

import java.util.ArrayList;
import java.util.Optional;

public class UserInteractionInteractor implements UserInteractionInputBoundary {
    final UserInteractionOutputBoundary userInteractionOutputBoundary;
    final UserRepository userRepository;
    final RecipeRepository recipeRepository;

    /**
     * constructor for this use case interactor.
     * @param userInteractionOutputBoundary the presenter.
     * @param userRepository the userRepo to interact with outer layer.
     * @param recipeRepository the recipeRepo to interact with outer layer.
     */
    public UserInteractionInteractor(UserInteractionOutputBoundary userInteractionOutputBoundary,
                                     UserRepository userRepository, RecipeRepository recipeRepository){
        this.userInteractionOutputBoundary = userInteractionOutputBoundary;
        this.userRepository = userRepository;
        this.recipeRepository = recipeRepository;
    }

    /**
     * @param interactRequestModel the input data from the controller
     * @return a msg if this is successful or not.
     */
    @Override
    public String follow(InteractRequestModel interactRequestModel) {
        Optional<User> u1 = userRepository.findById(interactRequestModel.getId1());
        Optional<User> u2 = userRepository.findById(interactRequestModel.getId2());
        try{
            User uu1 = u1.get();
            User uu2 = u2.get();
            if (uu1.getFollowers().contains(uu2)) {
                uu2.addFollower(uu1);
                uu1.followUser(uu2);
                userRepository.update(uu1);
                userRepository.update(uu2);
                return userInteractionOutputBoundary.displayFollow("success");
            }
        } catch (Exception e) {
            System.out.println("User not found");
        }
        return userInteractionOutputBoundary.displayFollow("user1 is already following user2");
    }

    /**
     * @param interactRequestModel the input data from the controller
     * @return a msg if this is successful or not.
     */
    @Override
    public String unfollow(InteractRequestModel interactRequestModel) {
        Optional<User> u1 = userRepository.findById(interactRequestModel.getId1());
        Optional<User> u2 = userRepository.findById(interactRequestModel.getId2());
        try{
            User uu1 = u1.get();
            User uu2 = u2.get();
            if (uu1.getFollowers().contains(uu2)) {
                uu2.removeFollower(uu1);
                uu1.unfollowUser(uu2);
                userRepository.update(uu1);
                userRepository.update(uu2);
                return userInteractionOutputBoundary.displayFollow("success");
            }
        } catch (Exception e) {
            System.out.println("User not found");
        }
        return userInteractionOutputBoundary.displayFollow("user1 is already NOT following user2");
    }

    /**
     * @param userRecipeRequestModel the input data from the controller
     * @return a UserRecipeResponseModel with all of
     */
    @Override
    public UserRecipeResponseModel getRecipes(UserRecipeRequestModel userRecipeRequestModel) {
        Optional<User> u = userRepository.findById(userRecipeRequestModel.getRecipeRequestID());

        UserRecipeResponseModel userRecipeResponseModel;
        try{
            User uu = u.get();
            ArrayList<Recipe> getRecipes = new ArrayList<>();
            for (User s: uu.getFollowers())
                getRecipes.addAll(s.getRecipes());
            userRecipeResponseModel = new UserRecipeResponseModel(getRecipes);
            return userRecipeResponseModel;
        } catch (Exception e) {
            System.out.println("User not found");
        }
        return null;
    }

}
