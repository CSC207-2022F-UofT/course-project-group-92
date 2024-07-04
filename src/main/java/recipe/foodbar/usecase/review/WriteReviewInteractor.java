package recipe.foodbar.usecase.review;

import recipe.foodbar.entities.Recipe;
import recipe.foodbar.entities.Review;
import recipe.foodbar.usecase.recipe.port.RecipeRepository;
import recipe.foodbar.usecase.review.exception.CharLimitException;
import recipe.foodbar.usecase.review.port.ReviewInputBoundary;
import recipe.foodbar.usecase.review.port.ReviewOutputBoundary;

import java.util.Optional;

public class WriteReviewInteractor implements ReviewInputBoundary {
    private final RecipeRepository recipeRepo;
    private ReviewOutputBoundary outputBoundary;

    public WriteReviewInteractor(RecipeRepository recipeRepo, ReviewOutputBoundary outputBoundary) {
        this.recipeRepo = recipeRepo;
        this.outputBoundary = outputBoundary;
    }

    @Override
    public Review writeReview(ReviewInputData inputData) {
        if (inputData.getText().length() > inputData.getMaxLength()) {
            throw new CharLimitException("This review exceeds the character limit!");
        }
        Review reviewToSave = Review.builder()
                .recipeId(inputData.getRecipeId())
                .title(inputData.getTitle())
                .text(inputData.getText())
                .author(inputData.getAuthor())
                .build();
        Optional<Recipe> r = recipeRepo.findById(reviewToSave.getRecipeId());
        try {
            Recipe r2 = r.get();
            r2.addReview(reviewToSave);
            recipeRepo.update(r2);
        } catch (Exception e) {
            System.out.println("Error LOL");
        }

        return reviewToSave;
    }
}
