package recipe.foodbar.controller.user.logout;

import recipe.foodbar.entities.User;
import recipe.foodbar.usecase.userLogout.UserLogoutInput;
import recipe.foodbar.usecase.userLogout.port.UserLogoutInputBoundary;

public class UserLogoutController {

    public final UserLogoutInputBoundary data;

    /**
     * Constructor for UserLoginController object
     *
     * @param data the interactor passed in as input boundary object
     */
    public UserLogoutController(UserLogoutInputBoundary data) {
        this.data = data;
    }


    /**
     * The UserLoginInput method to pass in the needed information to the interactor
     *
     * @param cookie String representation of the cookie
     * @return the completed UserLoginInput object
     */
    public String logout(String cookie) {
        UserLogoutInput UserLogout = new UserLogoutInput(cookie);

        return data.logout(UserLogout);
    }
}