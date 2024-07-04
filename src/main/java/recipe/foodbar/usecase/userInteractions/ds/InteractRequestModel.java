package recipe.foodbar.usecase.userInteractions.ds;

public class InteractRequestModel {
    private final String userId1;
    private final String userId2;

    /**
     * The constructor for this data class, with inputs of two users' ids.
     * @param userId1 userId of the first user.
     * @param userId2 userId of the second user.
     */
    public InteractRequestModel(String userId1, String userId2){
        this.userId1 = userId1;
        this.userId2 = userId2;
    }

    /**
     * getter method for the first user's id.
     * @return the id that is a String.
     */
    public String getId1(){
        return this.userId1;
    }

    /**
     * getter method for the second user's id.
     * @return the id that is a String.
     */
    public String getId2(){
        return this.userId2;
    }
}
