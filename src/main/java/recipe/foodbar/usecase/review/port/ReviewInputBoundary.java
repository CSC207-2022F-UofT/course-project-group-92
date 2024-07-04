package recipe.foodbar.usecase.review.port;

import recipe.foodbar.controller.dto.ReviewDTO;
import recipe.foodbar.entities.Review;
import recipe.foodbar.usecase.review.ReviewInputData;

public interface ReviewInputBoundary {
    Review writeReview(ReviewInputData data);
}
