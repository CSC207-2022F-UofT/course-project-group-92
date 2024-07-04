package recipe.foodbar.usecase.recipe.manager.filter;

import recipe.foodbar.entities.Cuisine;

public class CuisineFilterData {
    private Cuisine cuisine;

    /**
     * Constructor for CuisineFilterData to be handled by the input boundary and sent to the usecase interactor
     * @param cuisine
     */
    public CuisineFilterData(Cuisine cuisine) {
        this.cuisine = cuisine;
    }

    /**
     * Get this data transfer objects cuisine attribute
     * @return
     */
    public Cuisine getCuisine() {
        return this.cuisine;
    }
}
