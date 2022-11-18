package recipe.foodbar.entities;

import java.util.ArrayList;

public class Cuisine {
    private String name;
    private String id;
    private ArrayList<Recipe> recipes;

    private Cuisine(String name, String id){
        this.name = name;
        this.id = id;
        this.recipes = new ArrayList<Recipe>();
    }

    public static class CuisineBuilder {

        private String name;
        private String id;
        private ArrayList<Recipe> recipes;

        CuisineBuilder() {}

        public CuisineBuilder name(final String name) {
            this.name = name;
            return this;
        }

        public CuisineBuilder recipes() {
            this.recipes = new ArrayList<Recipe>();
            return this;
        }

        public CuisineBuilder id(final String id) {
            this.id = id;
            return this;
        }

    }

    public String getId() { return this.id;}
    public String getName() {
        return this.name;
    }

    public void addRecipe(Recipe recipe){
        this.recipes.add(recipe);
    }

    public ArrayList<Recipe> getRecipes(){
        return this.recipes;
    }

    public boolean recipeExists(Recipe recipe){
        for (Recipe r: this.recipes){
            if (r.getId().equals(recipe.getId())){return true;}
        }
        return false;
    }


}
