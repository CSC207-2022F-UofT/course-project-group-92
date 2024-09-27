/* Below is the UserManger class which acts as the usecase interactor,
 * using all the separate classes to run the necessary code for the use case
 */

package recipe.foodbar.usecase.user.manager;


import recipe.foodbar.usecase.commonport.IdGenerator;
import recipe.foodbar.usecase.user.UserChecker;
import recipe.foodbar.usecase.user.ds.UserConfirmer;
import recipe.foodbar.usecase.user.ds.UserInputData;
import recipe.foodbar.usecase.user.port.UserCreatorInputBoundary;
import recipe.foodbar.usecase.user.port.UserCreatorOutputBoundary;
import recipe.foodbar.usecase.user.port.UserRepository;

public class UserManager implements UserCreatorInputBoundary {


    private final UserCreatorOutputBoundary output;
    private final UserRepository repo;
    private final IdGenerator idGenerator;


    /**
     * Constructor for UserManager object taking both output boundary and
     * repository interface objects
     *
     * @param output      UserCreatorOutputBoundary object to follow dependency rules
     * @param repo        UserRepositoryInterface type to allow interactions with repository indirectly
     * @param idGenerator IdGenerator interface to allow easy creation of id
     */
    public UserManager(UserCreatorOutputBoundary output, UserRepository repo, IdGenerator idGenerator) {
        this.output = output;
        this.repo = repo;
        this.idGenerator = idGenerator;
    }


    /**
     * Method to create a RegisteredUser object after checking if it contains valid information
     *
     * @param input a bundle of information in the form of a UserInputData object
     * @return boolean of whether the account creation was successful
     */
    @Override
    public String create(UserInputData input) {
        String id = idGenerator.generate();
        String username = input.getUsername();
        String password = input.getPassword();
        String passwordShadow = input.getPasswordShadow();
        String email = input.getEmail();

        Boolean[] nullChecks = UserChecker.checkNullEntries(input);

        UserChecker repoChecker = new UserChecker(repo);
        UserFactory repoFactory = new UserFactory(repo);
        if (nullChecks[0] || nullChecks[1] || nullChecks[2] ||
                nullChecks[3]) {
            return output.present(UserConfirmer.userInformationNull(nullChecks));

        } else

            //if code works fix the password parameter
        if (!(UserChecker.checkPasswordMatch(password, passwordShadow))) {
            return output.present(UserConfirmer.passwordConfirmation());
        } else if (repoChecker.checkUserTaken(input)) {
            return output.present(UserConfirmer.userTakenError());
        } else {
            //creation of the account and added to the repository
            repoFactory.createAccount(id, username, password, email);
            return output.present("UserCreation Successful, no problems encountered.");
        }
    }
}
