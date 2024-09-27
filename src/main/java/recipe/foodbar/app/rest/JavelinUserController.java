package recipe.foodbar.app.rest;

import io.javalin.http.BadRequestResponse;
import io.javalin.http.Handler;
import io.javalin.http.UnauthorizedResponse;
import recipe.foodbar.controller.user.AccountController;
import recipe.foodbar.controller.user.login.UserLoginController;
import recipe.foodbar.controller.user.logout.UserLogoutController;

import recipe.foodbar.usecase.user.ds.UserInputData;
import recipe.foodbar.usecase.userLogin.UserLoginInput;
import recipe.foodbar.usecase.userLogout.UserLogoutInput;

import java.util.Objects;

public class JavelinUserController {

    private static AccountController accountController;
    private static UserLoginController userLoginController;
    private static UserLogoutController userLogoutController;

    public JavelinUserController(AccountController accountController, UserLoginController userLoginController, UserLogoutController userLogoutController) {
        JavelinUserController.accountController = accountController;
        JavelinUserController.userLoginController = userLoginController;
        JavelinUserController.userLogoutController = userLogoutController;
    }

    /**
     * Handles /api/user path which creates a user when called
     */
    public static Handler createUser = ctx -> {
        UserInputData user = ctx.bodyAsClass(UserInputData.class);
        String creationStatus = accountController.data.create(user);
        System.out.println(creationStatus);
        if (Objects.equals(creationStatus, "UserCreation Successful, no problems encountered.")) {
            ctx.json(user);
        } else {
            throw new BadRequestResponse(creationStatus);
        }
    };

    /**
     * Handles /api/login path which lets user login
     */
    public static Handler loginUser = ctx -> {
        UserLoginInput userLoginInput = ctx.bodyAsClass(UserLoginInput.class);
        String loginStatus = userLoginController.data.login(userLoginInput);
        System.out.println(loginStatus);
        switch (loginStatus) {
            case "login Failed: Invalid Password", "login Failed: User Does Not Exist" ->
                    throw new UnauthorizedResponse(loginStatus);
            default -> ctx.json(userLoginInput).cookie("session", loginStatus, 4 * 60 * 60 * 1000);
        }
    };

    /**
     * Handles /api/logout path which lets user logout
     */
    public static Handler logoutUser = ctx -> {
        String logoutStatus = userLogoutController.data.logout(new UserLogoutInput(ctx.cookie("session")));
        ctx.removeCookie("session");
        System.out.println(logoutStatus);
    };
}
