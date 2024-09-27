import com.mongodb.client.MongoDatabase;
import org.junit.jupiter.api.Test;
import recipe.foodbar.controller.user.AccountController;

import recipe.foodbar.controller.user.login.UserLoginController;
import recipe.foodbar.controller.user.login.UserLoginPresenter;
import recipe.foodbar.entities.User;
import recipe.foodbar.id_generator.jug.JugIdGenerator;
import recipe.foodbar.presenter.AccountPresenter;
import recipe.foodbar.repository.mongoDB.repository.MongoUserRepository;
import recipe.foodbar.repository.simpleDB.InMemoryCookieRepository;

import recipe.foodbar.usecase.commonport.IdGenerator;
import recipe.foodbar.usecase.user.manager.UserManager;
import recipe.foodbar.usecase.user.port.UserCreatorInputBoundary;
import recipe.foodbar.usecase.userLogin.UserLogin;
import recipe.foodbar.usecase.userLogin.port.LoginRepositoryInterface;
import recipe.foodbar.usecase.userLogin.port.UserLoginInputBoundary;
import recipe.foodbar.usecase.userLogin.port.UserLoginOutputBoundary;


import static recipe.foodbar.repository.mongoDB.MongoDB.getMongoDB;

public class UserLoginTest {

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
    public void accountCreationMethod(String username, String password, String passwordShadow, String email,
                                      MongoUserRepository repo, IdGenerator idGenerator) {

        AccountPresenter accountPresenterTwo = new AccountPresenter();
        UserCreatorInputBoundary data = new UserManager(accountPresenterTwo, repo, idGenerator);
        AccountController accountController = new AccountController(data);

        accountController.create(username, password, passwordShadow, email);
    }


    /**
     * Test to see if user can successfully login
     */
    @Test
    public void loginCheck() {

        final IdGenerator idGenerator = new JugIdGenerator();

        MongoDatabase db = getMongoDB();
        MongoUserRepository repo = new MongoUserRepository(db);
        LoginRepositoryInterface loginRepositoryInterface = new InMemoryCookieRepository();

        String username = "Frank98";
        String password = "godfather2";
        String passwordShadow = "godfather2";
        String email = "frank978@gmail.com";

        UserLoginOutputBoundary userLoginOutputBoundary = new UserLoginPresenter();
        accountCreationMethod(username, password, passwordShadow, email, repo, idGenerator);
        UserLoginInputBoundary userLoginInputBoundary = new UserLogin(userLoginOutputBoundary, repo,
                loginRepositoryInterface, idGenerator);
        UserLoginController userLoginController = new UserLoginController(userLoginInputBoundary);
        String cookie = userLoginController.login(username, password);

        System.out.println(loginRepositoryInterface.findByCookie(cookie));
        System.out.println(repo.findByUsername("Frank98"));

        //For this particular case we know the user is added to the repository
        if (repo.findByUsername("Frank98").isEmpty()) {
            System.out.println("User Account Creation Failed.");
            assert false;
        } else if (loginRepositoryInterface.findByCookie(cookie).isEmpty()) {
            System.out.println("Login Repository Process has failed.");
            assert false;
        } else {
            User actual = repo.findByUsername("Frank98").get();
            User expected = repo.findByUsername("Frank98").get();
            assert actual.equals(expected);
        }

    }


    /**
     * Test to see if user login fails due to missing entries
     */
    @Test
    public void loginFailMissingEntries() {

        final IdGenerator idGenerator = new JugIdGenerator();

        MongoDatabase db = getMongoDB();
        MongoUserRepository repo = new MongoUserRepository(db);
        LoginRepositoryInterface loginRepositoryInterface = new InMemoryCookieRepository();

        String username = "Frank99";
        String password = "godfather2";
        String passwordShadow = "godfather2";
        String email = "frank978@gmail.com";

        UserLoginOutputBoundary userLoginOutputBoundary = new UserLoginPresenter();
        accountCreationMethod(username, password, passwordShadow, email, repo, idGenerator);
        UserLoginInputBoundary userLoginInputBoundary = new UserLogin(userLoginOutputBoundary, repo,
                loginRepositoryInterface, idGenerator);
        UserLoginController userLoginController = new UserLoginController(userLoginInputBoundary);
        String actual = userLoginController.login(null, null);
        String expected = "login Failed: Missing Entries";

        assert actual.equals(expected);
    }


    /**
     * Test to see if user login fails with invalid password correctly
     */
    @Test
    public void loginFailedPasswordInvalid() {

        final IdGenerator idGenerator = new JugIdGenerator();

        MongoDatabase db = getMongoDB();
        MongoUserRepository repo = new MongoUserRepository(db);
        LoginRepositoryInterface loginRepositoryInterface = new InMemoryCookieRepository();

        String username = "Frank100";
        String password = "godfather2";
        String passwordShadow = "godfather2";
        String email = "frank978@gmail.com";

        UserLoginOutputBoundary userLoginOutputBoundary = new UserLoginPresenter();
        accountCreationMethod(username, password, passwordShadow, email, repo, idGenerator);
        UserLoginInputBoundary userLoginInputBoundary = new UserLogin(userLoginOutputBoundary, repo,
                loginRepositoryInterface, idGenerator);
        UserLoginController userLoginController = new UserLoginController(userLoginInputBoundary);
        String actual = userLoginController.login("Frank100", "Scarface");
        String expected = "login Failed: Invalid Password";

        assert actual.equals(expected);
    }

}
