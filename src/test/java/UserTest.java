import com.mongodb.client.MongoDatabase;
import org.junit.jupiter.api.Test;
import recipe.foodbar.controller.user.AccountController;
import recipe.foodbar.controller.user.AccountPresenter;
import recipe.foodbar.id_generator.jug.JugIdGenerator;
import recipe.foodbar.repository.mongoDB.repository.MongoUserRepository;
import recipe.foodbar.usecase.user.UserInputData;
import recipe.foodbar.usecase.user.UserManager;
import recipe.foodbar.usecase.user.port.IdGenerator;
import recipe.foodbar.usecase.user.port.UserCreatorInputBoundary;

import static recipe.foodbar.repository.mongoDB.MongoDB.getMongoDB;

public class UserTest {

    /**
     * AccountCreationMethod function that runs the creation of a user given parameters for tests
     *
     * @param username String representation of username
     * @param password String representation of password
     * @param passwordShadow String representation of passwordShadow
     * @param email String representation of email
     * @param repo given repository for user
     * @param idGenerator given idGenerator for user
     * @return created user object
     */
    public String accountCreationMethod(String username, String password, String passwordShadow, String email,
                                        recipe.foodbar.repository.mongoDB.repository.MongoUserRepository repo, IdGenerator idGenerator) {

        AccountPresenter accountPresenterTwo = new AccountPresenter();
        UserCreatorInputBoundary data = new UserManager(accountPresenterTwo, repo, idGenerator);
        AccountController accountController = new AccountController(data);

        return accountController.create(username, password, passwordShadow, email);
    }

    /**
     * Test to see if user can be successfully created
     */
    @Test
    public void successfulCreation(){
        final IdGenerator idGenerator = new JugIdGenerator();

        MongoDatabase db = getMongoDB();
        MongoUserRepository repo = new MongoUserRepository(db);

        String actual = accountCreationMethod("Daniel", "123", "123",
                "daniel@gmail.com", repo, idGenerator);
        String expected = "UserCreation Successful, no problems encountered.";
    }

    /**
     * Test to check that account creation fails if the username is taken
     */
    @Test
    public void usernameDuplicate() {
        final IdGenerator idGenerator = new JugIdGenerator();

        MongoDatabase db = getMongoDB();
        MongoUserRepository repo = new MongoUserRepository(db);


        String username = "Frank97";
        String password = "godfather2";
        String passwordShadow = "godfather2";
        String email = "frank978@gmail.com";

        String usernameTwo = "Frank97";
        String passwordTwo = "godfather2";
        String passwordShadowTwo = "godfather2";
        String emailTwo = "frank978@gmail.com";

        accountCreationMethod(username, password, passwordShadow, email, repo, idGenerator);


        String actual = "Username is taken";
        String expected = accountCreationMethod(usernameTwo, passwordTwo,
                passwordShadowTwo, emailTwo, repo, idGenerator);
        assert actual.equals(expected);
    }


    /**
     * Test to see if account creation fails if passwords do not match
     */
    @Test
    public void passwordsDoNotMatch() {
        final IdGenerator idGenerator = new JugIdGenerator();

        MongoDatabase db = getMongoDB();
        MongoUserRepository repo = new MongoUserRepository(db);


        String username = "Frank97";
        String password = "godfather2";
        String passwordShadow = "godfather3";
        String email = "frank978@gmail.com";


        String actual = "Passwords do not match";
        String expected = accountCreationMethod(username, password, passwordShadow, email, repo, idGenerator);
        assert actual.equals(expected);
    }

    /**
     * Test to see if account creation fails if entries are null
     */

    @Test
    public void allNullEntries() {
        final IdGenerator idGenerator = new JugIdGenerator();

        MongoDatabase db = getMongoDB();
        MongoUserRepository repo = new MongoUserRepository(db);


        String actual = "Username field is empty, Password field is empty, Password Confirmation field is empty, " +
                "Email field is empty";
        String expected = accountCreationMethod(null, null, null, null,
                repo, idGenerator);
        System.out.println(expected);
        assert actual.equals(expected);
    }


}
