package recipe.foodbar.usecase.userLogin.port;


import recipe.foodbar.usecase.userLogin.UserLoginInput;

/**
 *UserLoginInputBoundary to be implemented by the interactor
 */
public interface UserLoginInputBoundary {

    String login(UserLoginInput input);

}
