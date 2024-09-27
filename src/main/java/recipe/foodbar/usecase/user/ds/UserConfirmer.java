package recipe.foodbar.usecase.user.ds;

/*
A class to return the correct error methods from the given information
 */
public class UserConfirmer {

    /**
     * The error message given when the username is taken
     *
     * @return the appropriate error message
     */
    public static String userTakenError() {
        return "Username is taken";
    }

    /**
     * The error message given depending on the null entries of information
     *
     * @param array A boolean array consisting of 6 values one for each of the
     *              entries of information
     * @return the appropriate error message for the number of null entries and types
     */
    public static String userInformationNull(Boolean[] array) {

        StringBuilder returnMessage = new StringBuilder();
        if (array[0]) {
            returnMessage.append("Username field is empty, ");
        }
        if (array[1]) {
            returnMessage.append("Password field is empty, ");
        }
        if (array[2]) {
            returnMessage.append("Password Confirmation field is empty, ");
        }
        if (array[3]) {
            returnMessage.append("Email field is empty, ");
        }
        if (!(returnMessage.length() == 0)) {
            returnMessage.deleteCharAt(returnMessage.lastIndexOf(","));
            returnMessage.deleteCharAt(returnMessage.lastIndexOf(" "));
        }
        return returnMessage.toString();

    }


    /**
     * The error message given when the passwords do not match
     *
     * @return the appropriate error message;
     */
    public static String passwordConfirmation() {
        return "Passwords do not match";
    }
}
