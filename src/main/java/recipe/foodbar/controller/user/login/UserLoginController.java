package recipe.foodbar.controller.user.login;

import recipe.foodbar.usecase.userLogin.UserLoginInput;
import recipe.foodbar.usecase.userLogin.port.UserLoginInputBoundary;

public class UserLoginController {

    public final UserLoginInputBoundary data;

    /**
     * Constructor for UserLoginController object
     *
     * @param data the interactor passed in as input boundary object
     */
    public UserLoginController(UserLoginInputBoundary data) {
        this.data = data;
    }


    /**
     * The UserLoginInput method to pass in the needed information to the interactor
     *
     * @param username String representation of the username
     * @param password String representation of the password
     * @return the completed UserLoginInput object
     */
    public String login(String username, String password) {
        UserLoginInput UserLogin = new UserLoginInput(username, password);

        return data.login(UserLogin);
    }


}
