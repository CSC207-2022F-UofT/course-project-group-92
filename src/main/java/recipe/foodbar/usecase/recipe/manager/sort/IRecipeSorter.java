package recipe.foodbar.usecase.recipe.manager.sort;


import recipe.foodbar.entities.Recipe;
import recipe.foodbar.interfaces.Sorter;

import java.util.ArrayList;
import java.util.Comparator;

public interface IRecipeSorter extends Sorter {
    ArrayList<Recipe> sortBy(Comparator<Recipe> comparator);
}
