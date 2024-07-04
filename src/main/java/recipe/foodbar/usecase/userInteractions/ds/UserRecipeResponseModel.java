package recipe.foodbar.usecase.userInteractions.ds;

import recipe.foodbar.entities.Recipe;

import java.util.ArrayList;
import java.util.Objects;

public class UserRecipeResponseModel {
    public ArrayList<Recipe> listOf;

    /**
     * constructor for this data class.
     * @param listOf getting back the list of recipes.
     */
    public UserRecipeResponseModel(ArrayList<Recipe> listOf){
        this.listOf = listOf;
    }

    /**
     * getter method for grabbing list of recipes
     * @return the list of recipes.
     */
    public ArrayList<Recipe> getListOf(){
        return Objects.requireNonNullElseGet(this.listOf, ArrayList::new);
    }
}
