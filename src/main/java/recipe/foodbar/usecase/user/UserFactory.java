package recipe.foodbar.usecase.user;

import recipe.foodbar.entities.User;
import recipe.foodbar.usecase.user.port.UserRepositoryInterface;


public class UserFactory {

    private final UserRepositoryInterface repo;


    /**
     * A constructor to provide the given UserRepositoryInterface
     *
     * @param repo the given repository interface object
     */
    public UserFactory(UserRepositoryInterface repo) {
        this.repo = repo;

    }

    /**
     * Create a  RegisteredUser object given the information and add it to the repository
     *
     * @param username String representation for the username
     * @param password String representation for the password
     * @param first    String representation for the firstname
     * @param last     String representation for the lastname
     * @param email    String representation for the email
     */
    public void createAccount(String id, String username, String password, String first, String last, String email) {
        User user = new User(id, username, password, first, last, email);
        repo.create(user);
        //code to add the user to the repository
        //
        //
    }
}