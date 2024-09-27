package recipe.foodbar.usecase.user.manager;

import recipe.foodbar.entities.User;
import recipe.foodbar.usecase.user.port.UserRepository;


public class UserFactory {

    private final UserRepository repo;


    /**
     * A constructor to provide the given UserRepositoryInterface
     *
     * @param repo the given repository interface object
     */
    public UserFactory(UserRepository repo) {
        this.repo = repo;

    }

    /**
     * Create a  RegisteredUser object given the information and add it to the repository
     *
     * @param username String representation for the username
     * @param password String representation for the password
     * @param email    String representation for the email
     */
    public void createAccount(String id, String username, String password, String email) {
        User user = User.builder().id(id).username(username).password(password).email(email).build();
        repo.create(user);
    }
}