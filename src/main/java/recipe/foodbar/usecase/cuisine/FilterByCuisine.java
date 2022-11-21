package recipe.foodbar.usecase.cuisine;

import recipe.foodbar.usecase.recipe.port.RecipeRepository;
import recipe.foodbar.entities.Recipe;
import recipe.foodbar.entities.Cuisine;
import java.util.ArrayList;


public final class FilterByCuisine {

    private final RecipeRepository repository;

    public FilterByCuisine(final RecipeRepository repository) { this.repository = repository;}

    public ArrayList<Recipe> filterrecipe(Cuisine cuisine) {
        return repository.getByCuisine(cuisine);
    }

}
