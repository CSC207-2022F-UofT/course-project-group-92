package recipe.foodbar.usecase.userInteractions.exceptions;

public class UserAlreadyFollowedException extends Exception{
    /**
     * raise this exception when the user clicks on the follow button on another
     * user that he already followed.
     * @param msg the msg to print out.
     */
    public UserAlreadyFollowedException(final String msg){
        super(msg);
    }
}
