/*
Interface for the usecase interactor
 */
package recipe.foodbar.usecase.user.port;

import recipe.foodbar.usecase.user.ds.UserInputData;

public interface UserCreatorInputBoundary {

    /**
     * Abstract method to be implemented by the usecase interactor
     *
     * @param input the UserInputData representation of the bundled data provided
     * @return String value to be passed through to the AccountPresenter
     */
    String create(UserInputData input);
}
