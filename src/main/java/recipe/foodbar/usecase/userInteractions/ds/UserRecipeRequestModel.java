package recipe.foodbar.usecase.userInteractions.ds;

public class UserRecipeRequestModel {
    private final String userID;

    /**
     * constructor for this data class
     * @param userID the id of the user.
     */
    public UserRecipeRequestModel(String userID){
        this.userID = userID;
    }

    /**
     * getter method for userID
     * @return the userID which is a String.
     */
    public String getRecipeRequestID(){
        return this.userID;
    }
}
