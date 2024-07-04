package recipe.foodbar.usecase.userLogin.port;

import recipe.foodbar.entities.User;

import java.util.ArrayList;
import java.util.Optional;

/**
 * UserLoginRepositoryInterface to be implemented by the database for logged in users
 */
public interface LoginRepositoryInterface {

    /**
     * Method for adding a logged-in user to the repository
     * @param user given user object
     * @param cookie given String cookie
     */
    void add(final User user, final String cookie);

    /**
     * Method for finding a logged in user by a cookie
     * @param cookie given String cookie
     * @return user object if found.
     */

    Optional<User> findByCookie(final String cookie);

    /**
     * Method for finding all logged-in users
     * @return Array list of all users in the repository
     */
    ArrayList<User> findAllActive();

    /**
     * Method for finding a user by their username in the repository
     * @param username String representation of the username
     * @return user object if found
     */

    Optional<User> findByUsername(String username);

    /**
     * Method for removing a user from the repository by their given cookie
     * @param cookie String representing cookie
     */
    void remove(String cookie);
}
