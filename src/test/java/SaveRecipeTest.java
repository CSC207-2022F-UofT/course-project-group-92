import com.mongodb.client.MongoDatabase;
//import org.junit.After;
//import org.junit.Before;
import recipe.foodbar.controller.recipe.CreateRecipeController;
import recipe.foodbar.controller.user.AccountController;

import recipe.foodbar.entities.*;
import recipe.foodbar.entities.Cuisine;
import recipe.foodbar.id_generator.jug.JugIdGenerator;
import recipe.foodbar.presenter.AccountPresenter;
import recipe.foodbar.repository.mongoDB.repository.MongoRecipeRepository;
import recipe.foodbar.repository.mongoDB.repository.MongoUserRepository;
import recipe.foodbar.usecase.commonport.IdGenerator;
import recipe.foodbar.usecase.recipe.manager.CreateRecipeInputBoundary;
import recipe.foodbar.usecase.recipe.manager.CreateRecipeInteractor;
import recipe.foodbar.usecase.recipe.manager.CreateRecipePresenter;
import recipe.foodbar.usecase.user.*;
//import recipe.foodbar.usecase.user.port.IdGenerator;
import recipe.foodbar.usecase.user.manager.UserManager;
import recipe.foodbar.usecase.user.port.UserCreatorInputBoundary;
import static junit.framework.TestCase.assertEquals;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import static junit.framework.TestCase.assertEquals;

import static recipe.foodbar.repository.mongoDB.MongoDB.getMongoDB;

public class SaveRecipeTest {
//    @Before
//    public void setUp() {
//    }
//
//    @After
//    public void tearDown() {
//    }

    // Creating the repositories and ID generator needed for the test cases
    MongoDatabase db = getMongoDB();
    MongoUserRepository userRepo = new MongoUserRepository(db);
    MongoRecipeRepository recipeRepo = new MongoRecipeRepository(db);
    final IdGenerator idGenerator = new JugIdGenerator();

    // also creating presenter, controller and input boundary objects for the following use cases:
    // User creation, Recipe Creation, Recipe saving
    AccountPresenter accountPresenter = new AccountPresenter();
    UserCreatorInputBoundary data = new UserManager(accountPresenter, userRepo, idGenerator);
    AccountController accountController = new AccountController(data);

    // Recipe creation controller and presenter
    CreateRecipePresenter createRecipePresenter = new CreateRecipePresenter();
    CreateRecipeInputBoundary recipeData = new CreateRecipeInteractor(recipeRepo, userRepo, idGenerator, createRecipePresenter);
    CreateRecipeController recipeController = new CreateRecipeController(recipeData);

    // creating presenter and controllers for saving recipe. prerequisite steps(only need to be done once)
    SaveRecipePresenter saveRecipePresenter = new SaveRecipePresenter();
    SaveRecipeInputBoundary saveRecipeData = new SaveRecipeInteractor(saveRecipePresenter, userRepo, recipeRepo);
    SaveRecipeController saveRecipeController = new SaveRecipeController(saveRecipeData);

    @Test
    public void testUserCreation(){
        String username = "Bartholomew";
        String password = "123";
        String firstName = "tobias";
        String lastName = "razor";
        String passwordShadow = "123";
        String email = "a@gmail.com";
        // the line below formats the data into a format that can be used by the use case interactor
//        UserInputData user = accountController.create(username, password, passwordShadow, email);
        String userCreationConfirmation = accountController.create(username, password, passwordShadow, email);

        String username2 = "Bartholomew";
        String password2 = "1232";
        String firstName2 = "tobias2";
        String lastName2 = "razor2";
        String passwordShadow2 = "1232";
        String email2 = "a2@gmail.com";
//        UserInputData user2Data = accountController.create(username2, password2, passwordShadow2, email2);
        String userCreationConfirmation2 = accountController.create(username2, password2, passwordShadow2, email2);

        assertEquals(userCreationConfirmation2, "Username is taken");

        String username3 = "Bartholomew2";
        String password3 = "1232";
        String firstName3 = "tobias2";
        String lastName3 = "razor2";
        String passwordShadow3 = "1232";
        String email3 = "a2@gmail.com";
//        UserInputData user3Data = accountController.create(username3, password3, passwordShadow3, email3);
        String userCreationConfirmation3 = accountController.create(username3, password3, passwordShadow3, email3);

//        assertEquals(userCreationConfirmation3, "UserCreation Successful, no problems encountered.");
    }

    @Test
    public void testRecipeCreation() {
        // creating the same users as the one in the previous testcase but with different names
        String username = "toby";
        String password = "123";
        String firstName = "tobias";
        String lastName = "razor";
        String passwordShadow = "123";
        String email = "a24@gmail.com";
        // the line below formats the data into a format that can be used by the use case interactor
//        UserInputData user = accountController.create(username, password, passwordShadow, email);
        String userCreationConfirmation = accountController.create(username, password, passwordShadow, email);

        String username3 = "Toby2";
        String password3 = "1232";
        String firstName3 = "tobias2";
        String lastName3 = "razor2";
        String passwordShadow3 = "1232";
        String email3 = "a242@gmail.com";
        String userCreationConfirmation3 = accountController.create(username3, password3, passwordShadow3, email3);

        assertEquals(userCreationConfirmation3, "UserCreation Successful, no problems encountered.");
        // fetching user objects that can create the recipes
        Optional<User> user1 = userRepo.findByEmail("a24@gmail.com");
        Optional<User> user2 = userRepo.findByEmail("a242@gmail.com");
        Optional<User> userTest = userRepo.findByEmail("aaa@gmail.com");
        // check that userTest doesn't exist in the repo.
        assertEquals(userTest, Optional.empty());

        String idNum = idGenerator.generate();
        String rTitle = "curry";
        String authorID = user1.get().getId();
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
        ArrayList<String> rLikers = new ArrayList<>();
        ArrayList<String> rDislikers = new ArrayList<>();
        ArrayList<Review> rReviews = new ArrayList<>();
//
//
//        RecipeInputData recipeInputData = recipeController.createRecipe(rTitle, rAuthor, rPortionSize, instructions, cui, dr,
//                rDate, ing, rReviews, rLikers, rDislikers);

//      The line below creates the recipe object using the CreateRecipeInteractor and saves it to the repo
        String recipeID = recipeController.createRecipe(rTitle, authorID, rPortionSize, instructions, cui, dr,
                ing);
        Optional<Recipe> recipeFromRepo = recipeRepo.findById(recipeID);
        Recipe recipe1 = recipeFromRepo.get();
        assertEquals(recipe1.getTitle(), rTitle);
    }


    @Test
    public void testRecipeSaving() {
        // creating the same users and recipes the one in the previous testcase but with different names
        String username = "maguire";
        String password = "123";
        String firstName = "tobias";
        String lastName = "razor";
        String passwordShadow = "123";
        String email = "m@gmail.com";
        String userCreationConfirmation = accountController.create(username, password, passwordShadow, email);


        String username2 = "maguire";
        String password2 = "1232";
        String firstName2 = "tobias2";
        String lastName2 = "razor2";
        String passwordShadow2 = "1232";
        String email2 = "a2222@gmail.com";
        String userCreationConfirmation2 = accountController.create(username2, password2, passwordShadow2, email2);

        assertEquals(userCreationConfirmation2, "Username is taken");

        String username3 = "maguire2";
        String password3 = "1232";
        String firstName3 = "tobias2";
        String lastName3 = "razor2";
        String passwordShadow3 = "1232";
        String email3 = "m2@gmail.com";
        String userCreationConfirmation3 = accountController.create(username3, password3, passwordShadow3, email3);

        // fetching user objects that can create the recipes
        Optional<User> user1 = userRepo.findByEmail("m@gmail.com");
        Optional<User> user2 = userRepo.findByEmail("m2@gmail.com");
        Optional<User> userTest = userRepo.findByEmail("aaa@gmail.com");
        // check that userTest doesn't exist in the repo.
        assertEquals(userTest, Optional.empty());

        String idNum = idGenerator.generate();
        String rTitle = "curry";
        User rAuthor = user1.get();
        float rPortionSize = (float) 2.55;

        ArrayList<String> instructions = new ArrayList<>(Arrays.asList("idk bro. add salt. do the rest idk"));
//        instructions.add(0, "idk bro. add salt. do the rest idk");
        String cui = Cuisine.builder().id(idGenerator.generate()).name("Indian").build().getName();
        ArrayList<String> dr = new ArrayList<>(Arrays.asList("Vegan", "Halal"));
        Date rDate = new Date();
        ArrayList<Ingredient> ing = new ArrayList<>();
        Ingredient ing1 = new Ingredient("Potatoes", 69);
        ing.add(ing1);
        ArrayList<String> rLikers = new ArrayList<>();
        ArrayList<String> rDislikers = new ArrayList<>();
        ArrayList<Review> rReviews = new ArrayList<>();
//
//
//        RecipeInputData recipeInputData = recipeController.createRecipe(rTitle, rAuthor, rPortionSize, instructions, cui, dr,
//                rDate, ing, rReviews, rLikers, rDislikers);

//      The line below creates the recipe object using the CreateRecipeInteractor and saves it to the repo
        String recipeID = recipeController.createRecipe(rTitle, rAuthor.getId(), rPortionSize, instructions, cui, dr,
                ing);
        Optional<Recipe> recipeFromRepo = recipeRepo.findById(recipeID);
        Recipe recipe1 = recipeFromRepo.get();
        assertEquals(recipe1.getTitle(), rTitle);
        // TEST 1: recipe creator tries to save the recipe that they have created
        String saveRecipeConfirmation = saveRecipeController.saveRecipe(user1.get(), recipe1);
//        SaveRecipeData dataForSaving = saveRecipeController.saveRecipe(user1.get(), recipe1);
        // line below prints a message telling the user that they save a recipe that they have created
//        String saveRecipeConfirmation = saveRecipeController.inputBoundary.saveRecipe(dataForSaving);

        assertEquals(saveRecipeConfirmation, "You can't save a recipe that you have created");
        ArrayList<Recipe> testResult1 = user1.get().getSavedRecipes();
        assertEquals(testResult1, new ArrayList<>());

        // TEST2: User saves a recipe successfully
//        SaveRecipeData dataForSaving2 = saveRecipeController.saveRecipe(user2.get(), recipe1);
        String saveRecipeConfirmation2 = saveRecipeController.saveRecipe(user2.get(), recipe1);
        String userIDData = user2.get().getId();
        user2 = userRepo.findById(userIDData);

        ArrayList<Recipe> testResult2 = user2.get().getSavedRecipes();
        assertEquals(testResult2.size(), 1);

        // TEST3: User tries to save a recipe that's already been saved
//        SaveRecipeData dataForSaving3 = saveRecipeController.saveRecipe(user2.get(), recipe1);
        String saveRecipeConfirmation3 = saveRecipeController.saveRecipe(user2.get(), recipe1);

        ArrayList<Recipe> testResult3 = user2.get().getSavedRecipes();
        assertEquals(testResult3.size(), 1);

        // TEST4: Test that user can successfully un save a recipe.
        String unsaveRecipeConfirmation3 = saveRecipeController.unsaveRecipe(user2.get(), recipe1);
//        SaveRecipeData dataForUnsaving = saveRecipeController.saveRecipe(user2.get(), recipe1);
//        String unsaveRecipeConfirmation = saveRecipeController.inputBoundary.unsaveRecipe(dataForUnsaving);

        user2 = userRepo.findById(user2.get().getId());
        ArrayList<Recipe> testResult4 = user2.get().getSavedRecipes();

        assertEquals(testResult4.size(), 0);

        // Test 5: when user tries to un-save a recipe that they haven't saved
//        SaveRecipeData dataForUnsaving2 = saveRecipeController.saveRecipe(user1.get(), recipe1);
        String unsaveRecipeConfirmation2 = saveRecipeController.unsaveRecipe(user1.get(), recipe1);

        user1 = userRepo.findById(user1.get().getId());
        ArrayList<Recipe> testResult5 = user1.get().getSavedRecipes();
        assertEquals(unsaveRecipeConfirmation2, "Recipe is not in the list of savedRecipes");
    }



}

