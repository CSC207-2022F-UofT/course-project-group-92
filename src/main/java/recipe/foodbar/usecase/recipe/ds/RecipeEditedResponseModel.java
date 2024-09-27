package recipe.foodbar.usecase.recipe.ds;

/**
 * The output data for recipes.
 */
public class RecipeEditedResponseModel {
    public static String getRecipeResponseText(ResponseDataType type) {
        return switch (type) {
            case EDIT -> "Recipe has been edited successfully.";
        };
    }

    public String getRecipeDataText(RecipeDataType type) {
        return switch (type) {
            case ID -> "Id: ";
            case TITLE -> "Title: ";
            case AUTHOR -> "Author: ";
            case REVIEWS -> "Reviews: ";
            case CUISINE -> "Cuisine: ";
            case DATECREATED -> "Date: ";
            case INGREDIENTS -> "Ingredients: ";
            case PORTIONSIZE -> "Servings: ";
            case INSTRUCTIONS -> "Instructions: ";
            case DIETARYRESTRICTIONS -> "Dietary Restrictions: ";
        };
    }

    public enum RecipeDataType {
        ID,
        TITLE,
        AUTHOR,
        PORTIONSIZE,
        INSTRUCTIONS,
        CUISINE,
        DIETARYRESTRICTIONS,
        DATECREATED,
        INGREDIENTS,
        REVIEWS
    }

    public enum ResponseDataType {
        EDIT
    }
}
