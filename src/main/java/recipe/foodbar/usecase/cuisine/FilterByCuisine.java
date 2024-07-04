package recipe.foodbar.usecase.cuisine;

import recipe.foodbar.entities.Recipe;
import recipe.foodbar.usecase.recipe.manager.filter.CuisineFilterData;
import recipe.foodbar.usecase.recipe.manager.filter.CuisineFilterInputBoundary;
import recipe.foodbar.usecase.recipe.manager.filter.CuisineFilterOutputBoundary;
import recipe.foodbar.usecase.recipe.port.RecipeRepository;

import java.util.ArrayList;

/* FilterByCuisine class which acts as the usecase interactor
 */
public final class FilterByCuisine implements CuisineFilterInputBoundary {

    private final RecipeRepository repository;
    private final CuisineFilterOutputBoundary output;

    /**
     * Constructor for FilterByCuisine
     *
     * @param repository Data access interface to allow interactions with the repository
     */
    public FilterByCuisine(final RecipeRepository repository, final CuisineFilterOutputBoundary output) {
        this.repository = repository;
        this.output = output;
    }

    /**
     * Method to fetch and return an ArrayList which only contains recipes with a specific cuisine
     *
     * @param cuisineData CuisineInputData representing desired cuisine
     * @return ArrayList of recipes with desired cuisine
     */
    @Override
    public ArrayList<Recipe> filterByCuisine(CuisineFilterData cuisineData) {
        return output.presentFilteredRecipes(repository.getByCuisine(cuisineData.getCuisine()));
    }
}
