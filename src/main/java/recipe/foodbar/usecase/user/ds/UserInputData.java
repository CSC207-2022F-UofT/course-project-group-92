/*
Class for the input data passed through to the usecase interactor of usercreation
 */
package recipe.foodbar.usecase.user.ds;

import lombok.NoArgsConstructor;

@NoArgsConstructor
public class UserInputData {

    private String username;
    private String password;
    private String passwordShadow;
    private String email;


    /**
     * Constructor for UserInputData to be bundled to the usecase interactor
     *
     * @param username       String representing the username
     * @param password       String representing the password
     * @param passwordShadow String representing the passwordShadow
     * @param email          String representing the email
     */

    public UserInputData(String username, String password, String passwordShadow, String email) {
        this.username = username;
        this.password = password;
        this.passwordShadow = passwordShadow;
        this.email = email;

    }


    /**
     * getter function for the username
     *
     * @return the username variable
     */
    public String getUsername() {
        return this.username;
    }

    /**
     * getter function for the password
     *
     * @return the password variable
     */
    public String getPassword() {
        return this.password;
    }

    /**
     * getter function for the passwordShadow
     *
     * @return the passwordShadow variable
     */
    public String getPasswordShadow() {
        return this.passwordShadow;
    }


    /**
     * getter function for the email
     *
     * @return the email variable
     */
    public String getEmail() {
        return this.email;
    }
}
