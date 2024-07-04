package recipe.foodbar.presenter;

import recipe.foodbar.entities.Review;
import recipe.foodbar.usecase.review.port.ReviewOutputBoundary;

public class ReviewPresenter implements ReviewOutputBoundary {
    @Override
    public Review present(Review review) {
        return review;
    }
}
