package recipe.foodbar.usecase.userInteractions.exceptions;

public class UserAlreadyUnfollowedException extends Exception{
    /**
     * raise this exception when the user clicks on the unfollow button on another user
     * that he is not following/recently unfollowed.
     * @param msg the msg to print out.
     */
    public UserAlreadyUnfollowedException(final String msg){
        super(msg);
    }
}
