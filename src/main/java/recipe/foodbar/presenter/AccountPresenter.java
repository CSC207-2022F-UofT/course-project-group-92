/*
AccountPresenter class to present the appropriate information from the user creation process
 */
package recipe.foodbar.presenter;

import recipe.foodbar.usecase.user.port.UserCreatorOutputBoundary;

public class AccountPresenter implements UserCreatorOutputBoundary {

    /**
     * A User-creatorOutputBoundary method override to allow information to pass from usecase layer to presenter
     * without any outwards dependency
     *
     * @param message the String message given
     * @return the String message returned from the creation process
     */
    @Override
    public String present(String message) {
        return message;
    }
}
