import com.mongodb.client.MongoDatabase;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import static junit.framework.TestCase.assertEquals;
import recipe.foodbar.controller.recipe.FilterRecipes;
import recipe.foodbar.entities.Cuisine;
import recipe.foodbar.presenter.CuisineFilterPresenter;
import recipe.foodbar.repository.mongoDB.repository.MongoRecipeRepository;
import recipe.foodbar.usecase.cuisine.FilterByCuisine;
import recipe.foodbar.usecase.recipe.manager.filter.CuisineFilterData;
import recipe.foodbar.usecase.recipe.manager.filter.CuisineFilterInputBoundary;
import recipe.foodbar.usecase.recipe.manager.filter.CuisineFilterOutputBoundary;
import recipe.foodbar.usecase.recipe.port.RecipeRepository;
import com.mongodb.client.MongoDatabase;
import org.bson.BsonDocument;
import org.bson.BsonInt64;
import org.bson.Document;
import org.bson.conversions.Bson;
import recipe.foodbar.entities.Recipe;
import recipe.foodbar.entities.User;
import recipe.foodbar.entities.*;
import recipe.foodbar.id_generator.jug.JugIdGenerator;
import recipe.foodbar.repository.mongoDB.repository.MongoRecipeRepository;
import recipe.foodbar.usecase.commonport.IdGenerator;

import java.util.ArrayList;
import java.util.Date;

import static recipe.foodbar.repository.mongoDB.MongoDB.getMongoDB;

public class FilterTest {
    MongoDatabase db = getMongoDB();
    MongoRecipeRepository recipeRepo = new MongoRecipeRepository(db);

    final IdGenerator idGenerator = new JugIdGenerator();

    @Test
    public void testFilterChineseRecipes() {
        CuisineFilterOutputBoundary outputBoundary = new CuisineFilterPresenter();
        CuisineFilterInputBoundary inputBoundary = new FilterByCuisine(recipeRepo, outputBoundary);
        FilterRecipes filterController = new FilterRecipes(inputBoundary);
        CuisineFilterPresenter presenter = new CuisineFilterPresenter();

        //Test Chinese
        Cuisine chineseCuisine = Cuisine.builder()
                .id(idGenerator.generate())
                .name("Chinese")
                .build();

        ArrayList<Recipe> chineseRecipes = filterController.filter(chineseCuisine);

        Assertions.assertEquals(3, chineseRecipes.size());
        Assertions.assertEquals("Chinese", presenter.presentFilteredRecipes(chineseRecipes).get(0).getCuisine().getName());
    }

    @Test
    public void testFilterItalianRecipes() {
        CuisineFilterOutputBoundary outputBoundary = new CuisineFilterPresenter();
        CuisineFilterInputBoundary inputBoundary = new FilterByCuisine(recipeRepo, outputBoundary);
        FilterRecipes filterController = new FilterRecipes(inputBoundary);
//        FilterByCuisine interactor = new FilterByCuisine(recipeRepo, outputBoundary);

        //Test Italian
        Cuisine italianCuisine = Cuisine.builder()
                .id(idGenerator.generate())
                .name("Italian")
                .build();

        ArrayList<Recipe> italianRecipes = filterController.filter(italianCuisine);
//        ArrayList<Recipe> italianRecipes = interactor.filterByCuisine(filterController.data(italianCuisine));

        Assertions.assertEquals(6, italianRecipes.size()); //Change expected value to the actual amount in your database
        System.out.println(italianRecipes.get(0));
    }

    @Test
    public void testFilterEthiopianRecipes() {
        CuisineFilterOutputBoundary outputBoundary = new CuisineFilterPresenter();
        CuisineFilterInputBoundary inputBoundary = new FilterByCuisine(recipeRepo, outputBoundary);
        FilterRecipes filterController = new FilterRecipes(inputBoundary);
//        FilterByCuisine interactor = new FilterByCuisine(recipeRepo, outputBoundary);

        //Test Ethiopian
        Cuisine ethiopianCuisine = Cuisine.builder()
                .id(idGenerator.generate())
                .name("Ethiopian")
                .build();

//        ArrayList<Recipe> ethiopianRecipes = interactor.filterByCuisine(filterController.data(ethiopianCuisine));
        ArrayList<Recipe> ethiopianRecipes = filterController.filter(ethiopianCuisine);
        Assertions.assertEquals(0, ethiopianRecipes.size());
    }




}
