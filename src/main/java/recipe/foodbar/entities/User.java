package recipe.foodbar.entities;

import lombok.*;

import java.util.ArrayList;

@AllArgsConstructor
@Builder
@Data
@NoArgsConstructor
@ToString
public class User {
    private String id;
    private String username;
    private String password;
    private String email;
    @Builder.Default
    private ArrayList<User> following = new ArrayList<>();
    @Builder.Default
    private ArrayList<User> followers = new ArrayList<>();
    @Builder.Default
    private ArrayList<Recipe> savedRecipes = new ArrayList<Recipe>();


//    /**
//     * This method returns the list of savedRecipes saved by the user object
//     *
//     * @return the list of recipes saved by this user
//     */
//    public ArrayList<Recipe> getSavedRecipes() { return this.savedRecipes; }


    /**
     * This method return true if the recipe r is already in the list of saved recipes
     *
     * @param r the recipe whose existence in savedRecipes list needs to be checked
     * @return True iff recipe is in the list of savedRecipes. Returns false otherwise.
     */
    public boolean containsRecipe(Recipe r) {
        return this.savedRecipes.contains(r);
    }

    /**
     * addRecipe adds the recipe being passed in to the user's list of savedRecipes
     *
     * @param recipeToBeSaved The recipe object being saved by the user
     */
    public void addRecipe(Recipe recipeToBeSaved) {
        this.savedRecipes.add(recipeToBeSaved);
    }

    /**
     * removes the recipe being passed in from the list of saved recipes
     *
     * @param recipeToBeRemoved the recipe object to be removed from the list of savedRecipes
     */
    public void removeRecipe(Recipe recipeToBeRemoved) {
        this.savedRecipes.remove(recipeToBeRemoved);
    }

    /**
     * add a user to this user's following list.
     *
     * @param toFollow the user object to be added to the list of following.
     */
    public void followUser(User toFollow){
        this.following.add(toFollow);
    }

    /**
     * remove a user from this user's following list.
     *
     * @param toUnfollow the user object to be removed from the list of following.
     */
    public void unfollowUser(User toUnfollow){
        this.following.remove(toUnfollow);
    }

    /**
     * return a list of recipes that this user created.
     */
    public ArrayList<Recipe> getRecipes(){
        return this.savedRecipes;
    }

    /**
     * add a user to this user's follower's list. (generally used when someone follows this user)
     *
     * @param toAdd the user object to be added to this user's followers list.
     */
    public void addFollower(User toAdd){
        this.followers.add(toAdd);
    }

    /**
     * remove a user from this user's following list. (generally used when someone unfollows this user)
     *
     * @param toRemove the user object to be removed from this user's followers list.
     */
    public void removeFollower(User toRemove){
        this.followers.remove(toRemove);
    }
}
