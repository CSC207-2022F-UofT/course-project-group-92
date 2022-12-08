package recipe.foodbar.app.rest;

import io.javalin.Javalin;
import recipe.foodbar.config.JavelinConfig;

public class JavelinApplication {
//    private final UserController userController = new UserController(javelinConfig.createUser(), javelinConfig.findUser(), javelinConfig.loginUser());
//    private final VertxUserController controller = new VertxUserController(userController);

    public static void main(String[] args) {
        JavelinConfig javelinConfig = new JavelinConfig();
        JavelinUserController userController = new JavelinUserController(javelinConfig.getAccountController(), javelinConfig.getUserLoginController(), javelinConfig.getUserLogoutController());
        JavelinRecipeController recipeController = new JavelinRecipeController(javelinConfig.getCreateRecipeController());
        JavelinReviewController reviewController = new JavelinReviewController(javelinConfig.getWriteInteractor());

        var app = Javalin.create().get("/", ctx -> ctx.result("Hello World!")).start(4040);

//        app.get("hello", ctx -> ctx.html("Hello World"));

//        User
        app.post("/api/register", userController.createUser);
        app.post("/api/login", userController.loginUser);
        app.get("/logout", userController.logoutUser);

//        Recipe
        app.post("/api/recipe", recipeController.createRecipe);

//        Review
        app.post("/api/review", reviewController.createReview);
    }
}
