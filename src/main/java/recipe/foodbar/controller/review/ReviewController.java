package recipe.foodbar.controller.review;

import recipe.foodbar.controller.dto.ReviewDTO;
import recipe.foodbar.entities.User;
import recipe.foodbar.usecase.review.ReviewInputData;
import recipe.foodbar.usecase.review.port.ReviewInputBoundary;

public class ReviewController {
    public final ReviewInputBoundary f;
    public ReviewController(ReviewInputBoundary f) {
        this.f = f;
    }

    public ReviewInputData data(String recipeID, String title, String text, User author) {
        return new ReviewInputData(recipeID, title, text, author);
    }

}
