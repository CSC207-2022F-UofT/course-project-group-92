package recipe.foodbar.controller.recipe;

import recipe.foodbar.entities.Cuisine;
import recipe.foodbar.entities.Recipe;
import recipe.foodbar.usecase.recipe.manager.filter.CuisineFilterData;
import recipe.foodbar.usecase.recipe.manager.filter.CuisineFilterInputBoundary;

import java.util.ArrayList;

/**
 * A controller to filter recipes.
 */
public class FilterRecipes {
    public final CuisineFilterInputBoundary f;

    /**
     * Constructor for FilterRecipes
     *
     * @param f
     */
    public FilterRecipes(CuisineFilterInputBoundary f) {
        this.f = f;
    }

    /**
     * Creates and returns Cuisine input data
     *
     * @param cuisine
     * @return
     */
//    public CuisineFilterData data(Cuisine cuisine) {
//        return new CuisineFilterData(cuisine);
//    }

    /**
     * Filter method which interacts with the usecase input boundary; retrieves and returns an arraylist of recipes from
     * the database which are of the cuisine <cuisine>
     * @param cuisine
     * @return
     */
    public ArrayList<Recipe> filter(Cuisine cuisine) {
        CuisineFilterData inputData = new CuisineFilterData(cuisine);
        return f.filterByCuisine(inputData);
    }

}
