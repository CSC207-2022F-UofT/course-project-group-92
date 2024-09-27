package recipe.foodbar.controller.recipe;

import recipe.foodbar.entities.Recipe;
import recipe.foodbar.usecase.recipe.manager.sort.IRecipeSorter;
import recipe.foodbar.usecase.recipe.manager.sort.comparators.CuisineComparator;
import recipe.foodbar.usecase.recipe.manager.sort.comparators.DateComparator;
import recipe.foodbar.usecase.recipe.manager.sort.comparators.RatingComparator;

import java.util.ArrayList;

/**
 * A controller to sort recipes.
 */
public class SortRecipes {
    private final IRecipeSorter sorter;

    /**
     * Constructor for SortRecipes.
     *
     * @param sorter IRecipeSorter
     */
    public SortRecipes(IRecipeSorter sorter) {
        this.sorter = sorter;
        /* TODO: Click button Input Data from user. */
    }

    /**
     * Returns a sorted list of recipes.
     *
     * @param order string
     */
    public ArrayList<Recipe> sort(String order) {
        return switch (order) {
            case "by_rating" -> sorter.sortBy(new RatingComparator());
            case "by_cuisine" -> sorter.sortBy(new CuisineComparator());
            default -> sorter.sortBy(new DateComparator());
        };
    }

}
