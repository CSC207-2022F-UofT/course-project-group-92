package recipe.foodbar.usecase.recipe.manager.sort.comparators;

import recipe.foodbar.entities.Ingredient;
import recipe.foodbar.entities.Recipe;

import java.util.Comparator;

public class IngredientComparator implements Comparator<Recipe> {
    /**
     * @param r1 the first object to be compared.
     * @param r2 the second object to be compared.
     * @return
     */
    @Override
    public int compare(Recipe r1, Recipe r2) {
        String s1 = null;
        String s2 = null;

        for (Ingredient ingredient : r1.getIngredients()) {
            if (r1.getTitle().contains(ingredient.getName())) {
                s1 = ingredient.getName();
            }
        }

        for (Ingredient ingredient : r2.getIngredients()) {
            if (r1.getTitle().contains(ingredient.getName())) {
                s2 = ingredient.getName();
            }
        }

        if(s1 == null || s2 == null) {
            return String.CASE_INSENSITIVE_ORDER.compare(s1, s2);}
        else {
            return String.CASE_INSENSITIVE_ORDER.compare(r1.getTitle(), r2.getTitle());
        }
    }
}
