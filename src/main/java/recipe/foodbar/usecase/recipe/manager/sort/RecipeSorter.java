package recipe.foodbar.usecase.recipe.manager.sort;

import recipe.foodbar.entities.Recipe;
import recipe.foodbar.presenter.RecipePresenter;
import recipe.foodbar.usecase.recipe.port.RecipeRepository;

import java.util.ArrayList;
import java.util.Comparator;

public class RecipeSorter implements IRecipeSorter {

    private final RecipeRepository recipeRepo;
    private final RecipePresenter rp;
    /* Depends on DAI */
    public RecipeSorter(RecipeRepository recipeRepo, RecipePresenter rp) {
        this.recipeRepo = recipeRepo;
        this.rp = rp;
    }

    /**
     * @return Recipe View
     */
    @Override
    public ArrayList<Recipe> sortBy(Comparator<Recipe> comparator) {
        ArrayList<Recipe> recipes = this.recipeRepo.getAllRecipes();
        recipes.sort(comparator);
        return recipes;
    }
}
