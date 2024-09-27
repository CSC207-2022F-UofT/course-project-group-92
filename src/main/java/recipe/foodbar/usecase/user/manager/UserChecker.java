/*
Class to check the validity of the information provided and return the results
 */

package recipe.foodbar.usecase.user;

import recipe.foodbar.entities.User;
import recipe.foodbar.usecase.user.ds.UserInputData;
import recipe.foodbar.usecase.user.port.UserRepository;

import java.util.List;

public class UserChecker {

    private final UserRepository repo;


    /**
     * Constructor for UserChecker to allow the class to use repository methods.
     *
     * @param repo the interface representing a repository with methods.
     */
    public UserChecker(UserRepository repo) {
        this.repo = repo;

    }

    /**
     * Boolean method for checking if the passwords match
     *
     * @param password1 variable for the first password entered
     * @param password2 variable for the second password entered
     * @return boolean variable for checking if the two strings match
     */
    public static boolean checkPasswordMatch(String password1, String password2) {
        return password1.equals(password2);
    }

    /**
     * Boolean method for checking if there are any null entries in the data
     *
     * @param userInput the UserInputData object bundling all the necessary information
     * @return boolean variable for whether there are any null entries
     */
    public static Boolean[] checkNullEntries(UserInputData userInput) {

        Boolean[] array = new Boolean[6];
        array[0] = false;
        array[1] = false;
        array[2] = false;
        array[3] = false;
        if (userInput.getUsername() == null) {
            array[0] = true;
        }
        if (userInput.getPassword() == null) {
            array[1] = true;

        }
        if (userInput.getPasswordShadow() == null) {
            array[2] = true;


        }
        if (userInput.getEmail() == null) {
            array[3] = true;

        }
        return array;
    }

    /**
     * Boolean method for checking the given username to see if it is taken in the repository
     *
     * @param userInput bundled data in a UserInputData object
     * @return boolean for the results of if the username is taken
     */
    public boolean checkUserTaken(UserInputData userInput) {
        List<User> allUsers = repo.findAllUsers();
        for (User user : allUsers) {
            if (user.getUsername().equals(userInput.getUsername())) {
                return true;
            }

        }
        return false;
    }


}