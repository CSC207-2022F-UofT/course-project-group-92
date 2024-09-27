package recipe.foodbar.config;

import com.mongodb.client.MongoDatabase;
import recipe.foodbar.controller.recipe.CreateRecipeController;
import recipe.foodbar.controller.review.ReviewController;
import recipe.foodbar.controller.user.AccountController;

import recipe.foodbar.controller.user.login.UserLoginController;
import recipe.foodbar.controller.user.login.UserLoginPresenter;
import recipe.foodbar.controller.user.logout.UserLogoutController;
import recipe.foodbar.controller.user.logout.UserLogoutPresenter;
import recipe.foodbar.id_generator.jug.JugIdGenerator;
import recipe.foodbar.presenter.AccountPresenter;
import recipe.foodbar.presenter.ReviewPresenter;
import recipe.foodbar.repository.mongoDB.MongoDB;
import recipe.foodbar.repository.mongoDB.repository.MongoRecipeRepository;
import recipe.foodbar.repository.mongoDB.repository.MongoUserRepository;
import recipe.foodbar.repository.simpleDB.InMemoryCookieRepository;
import recipe.foodbar.usecase.commonport.IdGenerator;
import recipe.foodbar.usecase.recipe.manager.CreateRecipeInputBoundary;
import recipe.foodbar.usecase.recipe.manager.CreateRecipeInteractor;
import recipe.foodbar.usecase.recipe.manager.CreateRecipePresenter;
import recipe.foodbar.usecase.recipe.port.RecipeRepository;
import recipe.foodbar.usecase.review.WriteReviewInteractor;
import recipe.foodbar.usecase.review.port.ReviewOutputBoundary;

import recipe.foodbar.usecase.user.manager.UserManager;
import recipe.foodbar.usecase.user.port.UserCreatorInputBoundary;
import recipe.foodbar.usecase.user.port.UserRepository;
import recipe.foodbar.usecase.userLogin.UserLogin;
import recipe.foodbar.usecase.userLogin.port.LoginRepositoryInterface;
import recipe.foodbar.usecase.userLogin.port.UserLoginInputBoundary;
import recipe.foodbar.usecase.userLogin.port.UserLoginOutputBoundary;
import recipe.foodbar.usecase.userLogout.UserLogout;
import recipe.foodbar.usecase.userLogout.port.UserLogoutInputBoundary;
import recipe.foodbar.usecase.userLogout.port.UserLogoutOutputBoundary;

/**
 * JavelinConfig class responsible for passing the required repository, presenter, idgenerator to each REST controller
 */
public class JavelinConfig {
    MongoDatabase db = MongoDB.getMongoDB();
    private final UserRepository userRepository = new MongoUserRepository(db);
    private final RecipeRepository recipeRepository = new MongoRecipeRepository(db);
    LoginRepositoryInterface loggedInRepo = new InMemoryCookieRepository();
    private final IdGenerator idGenerator = new JugIdGenerator();
    //    User Account creation, login, logout
    AccountPresenter accountPresenter = new AccountPresenter();
    UserLoginOutputBoundary userLoginOutputBoundary = new UserLoginPresenter();
    UserLogoutOutputBoundary userLogoutOutputBoundary = new UserLogoutPresenter();
    UserCreatorInputBoundary data = new UserManager(accountPresenter, userRepository, idGenerator);
    UserLoginInputBoundary userLoginInputBoundary = new UserLogin(userLoginOutputBoundary, userRepository, loggedInRepo, idGenerator);
    AccountController accountController = new AccountController(data);
    UserLoginController userLoginController = new UserLoginController(userLoginInputBoundary);
    UserLogoutInputBoundary userLogoutInputBoundary = new UserLogout(userLogoutOutputBoundary, loggedInRepo);
    UserLogoutController userLogoutController = new UserLogoutController(userLogoutInputBoundary);

    //    Recipe Creation
    CreateRecipePresenter createRecipePresenter = new CreateRecipePresenter();
    CreateRecipeInputBoundary recipeData = new CreateRecipeInteractor(recipeRepository, userRepository, idGenerator, createRecipePresenter);
    CreateRecipeController createRecipeController = new CreateRecipeController(recipeData);
    //    Review Creation
    ReviewOutputBoundary outputBoundary = new ReviewPresenter();
    WriteReviewInteractor interactor = new WriteReviewInteractor(recipeRepository, userRepository, outputBoundary);
    ReviewController reviewController = new ReviewController(interactor);
//    Get Recipe by Cuisine


    public AccountController getAccountController() {
        return accountController;
    }

    public UserLoginController getUserLoginController() {
        return userLoginController;
    }

    public UserLogoutController getUserLogoutController() {
        return userLogoutController;
    }

    public CreateRecipeController getCreateRecipeController() {
        return createRecipeController;
    }

    public WriteReviewInteractor getWriteInteractor() {
        return interactor;
    }
}
