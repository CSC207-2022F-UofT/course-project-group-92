package recipe.foodbar.usecase.userLogin;

import recipe.foodbar.entities.User;
import recipe.foodbar.usecase.user.port.UserRepositoryInterface;

import java.util.Objects;
import java.util.Optional;


public class UserLoginValidator {

    private final UserRepositoryInterface repo;


    /**
     * Constructor for UserLoginValidator object
     * @param repo the given repository used
     */
    public UserLoginValidator(UserRepositoryInterface repo) {
        this.repo = repo;
    }

    /**
     * validation check for any null entries
     * @param username String representation of the username
     * @param password String representation of the password
     * @return boolean result on check
     */
    public boolean fullEntries(String username, String password) {
        return !Objects.equals(username, null) && !Objects.equals(password, null);
    }

    /**
     * validation check for whether username exists
     * @param username String representation of the username
     * @return boolean result on check
     */
    public boolean validateExist(String username) {
        return (repo.existsByUsername(username));

    }


    /**
     * validation check for whether password matches user object
     * @param username String representation of the username
     * @param password String representation of the password
     * @return boolean result on check
     */
    public boolean validatePassword(String username, String password) {
        //interactor already checks if user.isEmpty()
        //code purpose is to satisfy java warnings
        Optional<User> user = repo.findByUsername(username);
        if (user.isEmpty()) {
            return false;
        }
        String pass = user.get().getPassword();
        return password.equals(pass);
    }
}
