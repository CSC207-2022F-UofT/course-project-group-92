package recipe.foodbar.usecase.review.port;

import recipe.foodbar.entities.Review;

public interface ReviewOutputBoundary {
    Review present(Review review);
}
