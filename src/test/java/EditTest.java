import com.mongodb.client.MongoDatabase;

import org.junit.jupiter.api.Test;
import recipe.foodbar.controller.recipe.CreateRecipeController;
import recipe.foodbar.controller.recipe.EditRecipe;
import recipe.foodbar.controller.user.AccountController;
import recipe.foodbar.entities.Cuisine;
import recipe.foodbar.entities.Ingredient;
import recipe.foodbar.entities.Recipe;

import recipe.foodbar.id_generator.jug.JugIdGenerator;
import recipe.foodbar.presenter.AccountPresenter;
import recipe.foodbar.presenter.RecipePresenter;
import recipe.foodbar.repository.mongoDB.repository.MongoRecipeRepository;
import recipe.foodbar.repository.mongoDB.repository.MongoUserRepository;
import recipe.foodbar.usecase.commonport.IdGenerator;
import recipe.foodbar.usecase.recipe.ds.RecipeRequestModel;
import recipe.foodbar.usecase.recipe.manager.CreateRecipeInputBoundary;
import recipe.foodbar.usecase.recipe.manager.CreateRecipeInteractor;
import recipe.foodbar.usecase.recipe.manager.CreateRecipePresenter;
import recipe.foodbar.usecase.recipe.manager.edit.IRecipeEditor;
import recipe.foodbar.usecase.recipe.manager.edit.RecipeEditor;
import recipe.foodbar.usecase.user.manager.UserManager;
import recipe.foodbar.usecase.user.port.UserCreatorInputBoundary;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.Optional;

import static recipe.foodbar.repository.mongoDB.MongoDB.getMongoDB;

public class EditTest {
    MongoDatabase db = getMongoDB();
    MongoRecipeRepository recipeRepo = new MongoRecipeRepository(db);

    final IdGenerator idGenerator = new JugIdGenerator();

    MongoUserRepository userRepo = new MongoUserRepository(db);
    CreateRecipePresenter createRecipePresenter = new CreateRecipePresenter();
    CreateRecipeInputBoundary recipeData = new CreateRecipeInteractor(recipeRepo, userRepo, idGenerator, createRecipePresenter);
    CreateRecipeController recipeController = new CreateRecipeController(recipeData);

    AccountPresenter accountPresenter = new AccountPresenter();
    UserCreatorInputBoundary data = new UserManager(accountPresenter, userRepo, idGenerator);
    AccountController accountController = new AccountController(data);

    @Test
    public void testSetPortionSize() {

        String idNum = idGenerator.generate();
        String rTitle = "curry";
        float rPortionSize = (float) 2.55;

        ArrayList<String> instructions = new ArrayList<>(Arrays.asList("idk bro. add salt. do the rest idk"));
//        instructions.add(0, "idk bro. add salt. do the rest idk");
        String cui = Cuisine.builder().id(idGenerator.generate()).name("Indian").build().getName();
//        String cusine =
        ArrayList<String> dr = new ArrayList<>(Arrays.asList("Vegan", "Halal"));
        Date rDate = new Date();
        ArrayList<Ingredient> ing = new ArrayList<>();
        Ingredient ing1 = new Ingredient("Potatoes", 69);
        ing.add(ing1);

        Recipe recipeToSave = Recipe.builder()
                .id(idNum)
                .title(rTitle)
                .instructions(instructions)
                .cuisine(Cuisine.builder().id(idGenerator.generate()).name(cui).build())
                .portionSize(rPortionSize)
                .dietaryRestrictions(dr)
                .ingredients(ing)
                .dateCreated(rDate)
                .build();

        recipeRepo.create(recipeToSave);

        Optional<Recipe> recipeFromRepo = recipeRepo.findById(recipeToSave.getId());
       if(recipeFromRepo.isPresent()){
        Recipe recipe1 = recipeFromRepo.get();}

        RecipeRequestModel rm = new RecipeRequestModel();
        rm.setId(recipeToSave.getId());
        rm.setTitle(recipeToSave.getTitle());
        rm.setInstructions(recipeToSave.getInstructions());
        rm.setCuisine(recipeToSave.getCuisine());
        rm.setPortionSize(3);
        rm.setDietaryRestrictions(recipeToSave.getDietaryRestrictions());
        rm.setIngredients(recipeToSave.getIngredients());

        RecipePresenter presenter = new RecipePresenter();
        IRecipeEditor inputboundary = new RecipeEditor(recipeRepo, presenter);
        EditRecipe controller = new EditRecipe(rm, inputboundary, presenter);
        controller.edit();

        Optional<Recipe> recipeFromRepo2 = recipeRepo.findById(recipeToSave.getId());
        System.out.println(recipeFromRepo2.get().getPortionSize());
        assert recipeFromRepo2.get().getPortionSize() == 3;
    }
}

