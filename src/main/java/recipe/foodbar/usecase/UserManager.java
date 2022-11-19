/* Below is the UserManger class which acts as the usecase interactor,
 * using all the separate classes to run the necessary code for the use case
 */

package recipe.foodbar.usecase;


public class UserManager implements UserCreatorInputBoundary{


    private UserCreatorOutputBoundary output;
    private UserRepositoryInterface repo;


    /**
     * Constructor for UserManager object taking both output boundary and
     * repository interface objects
     *
     * @param output UserCreatorOutputBoundary object to follow dependency rules
     * @param repo UserRepositoryInterface type to allow interactions with repository indirectly
     */
    public UserManager (UserCreatorOutputBoundary output, UserRepositoryInterface repo) {
        this.output = output;
        this.repo = repo;
    }


    /**
     * Method to create a RegisteredUser object after checking if it contains valid information
     *
     * @param input a bundle of information in the form of a UserInputData object
     * @return boolean of whether the account creation was successful
     */
    @Override
    public String create(UserInputData input) {
        String id = input.getUsername();
        String password = input.getPassword();
        String passwordShadow = input.getPasswordShadow();
        String email = input.getEmail();
        String firstName = input.getFirstName();
        String lastName = input.getLastName();

        Boolean[] nullChecks = UserChecker.checkNullEntries(input);

        UserChecker repoChecker = new UserChecker(repo);
        UserFactory repoFactory = new UserFactory(repo);

        //if code works fix the password parameter
        if (nullChecks[0] || nullChecks[1] || nullChecks[2] ||
                nullChecks[3] || nullChecks[4] || nullChecks[5]) {
            return output.present(UserConfirmer.userInformationNull(nullChecks));

        }  else if (!(UserChecker.checkPasswordMatch(password, passwordShadow))) {
            return output.present(UserConfirmer.passwordConfirmation());


        } else if (repoChecker.checkUserTaken(input)){
            return output.present(UserConfirmer.userTakenError());


        } else {

            //creation of the account and added to the repository
            repoFactory.createAccount(id, password, firstName, lastName, email);

            return output.present("UserCreation Successful, no problems encountered.");
        }

    }


}