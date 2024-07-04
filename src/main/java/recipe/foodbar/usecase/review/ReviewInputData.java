package recipe.foodbar.usecase.review;

import recipe.foodbar.entities.Review;
import recipe.foodbar.entities.User;
import recipe.foodbar.usecase.review.port.ReviewInputBoundary;

public class ReviewInputData {
    private String recipeId;
    private static final int MAX_LENGTH = 300;
    private String title;
    private String text;
    private User author;

    public ReviewInputData(String recipeId, String title, String text, User author){
        this.recipeId = recipeId;
        this.title = title;
        this.text = text;
        this.author = author;
    }

    public String getRecipeId() {
        return recipeId;
    }

    public void setRecipeId(String recipeId) {
        this.recipeId = recipeId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public User getAuthor() {
        return author;
    }

    public void setAuthor(User author) {
        this.author = author;
    }

    public int getMaxLength() {
        return MAX_LENGTH;
    }
}
