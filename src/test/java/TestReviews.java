import com.mongodb.client.MongoDatabase;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import static junit.framework.TestCase.assertEquals;
import recipe.foodbar.controller.recipe.FilterRecipes;
import recipe.foodbar.controller.review.ReviewController;
import recipe.foodbar.entities.Cuisine;
import recipe.foodbar.presenter.ReviewPresenter;
import recipe.foodbar.repository.mongoDB.repository.MongoRecipeRepository;
import recipe.foodbar.usecase.cuisine.FilterByCuisine;
import recipe.foodbar.usecase.recipe.manager.filter.CuisineFilterData;
import recipe.foodbar.usecase.recipe.manager.filter.CuisineFilterInputBoundary;
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
import recipe.foodbar.usecase.review.ReviewInputData;
import recipe.foodbar.usecase.review.WriteReviewInteractor;
import recipe.foodbar.usecase.review.port.ReviewInputBoundary;
import recipe.foodbar.usecase.review.port.ReviewOutputBoundary;
import recipe.foodbar.usecase.user.port.IdGenerator;

import java.util.ArrayList;
import java.util.Date;

import static recipe.foodbar.repository.mongoDB.MongoDB.getMongoDB;

public class TestReviews {
    MongoDatabase db = getMongoDB();
    MongoRecipeRepository recipeRepo = new MongoRecipeRepository(db);

    final IdGenerator idGenerator = new JugIdGenerator();

    @Test
    public void testRecipeExists() {
        ArrayList<Recipe> allRecipes = recipeRepo.getAllRecipes();
        System.out.println(allRecipes);
    }
    @Test
    public void testCreateReviewItalianCuisine() {
        //Input, Output boundaries, controller, usecase interactor
        ReviewOutputBoundary outputBoundary = new ReviewPresenter();
        ReviewInputBoundary inputBoundary = new WriteReviewInteractor(recipeRepo, outputBoundary);
        ReviewController reviewController = new ReviewController(inputBoundary);
        WriteReviewInteractor interactor = new WriteReviewInteractor(recipeRepo, outputBoundary);

        //Creating author, input data, etc
        User author1 = User.builder()
                .id(idGenerator.generate())
                .username("Deez")
                .password("Nutz")
                .email("Deez.Nutz@gmail.com")
                .followers(new ArrayList<User>())
                .following(new ArrayList<User>())
                .build();
        String recipeID = "638e886d2aaa9312bfd0d283";
        ReviewInputData inputData = reviewController.data(recipeID, "DUMPLINGS F***ING SUCK",
                "This dumpling recipe sucks2. Followed the instructions, ended up buying Amouranth merch", author1);

        Review newReview = interactor.writeReview(inputData);

        //Present added review
        ReviewPresenter presenter = new ReviewPresenter();
        System.out.println();

    }
}
