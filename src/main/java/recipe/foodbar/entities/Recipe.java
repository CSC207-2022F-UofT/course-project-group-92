package recipe.foodbar.entities;

import lombok.*;

import java.util.ArrayList;
import java.util.Date;


@AllArgsConstructor
@Builder
@Data
@NoArgsConstructor
@ToString
public class Recipe {

    private String id;
    private String title;
    private User user;
    private float portionSize;
    private ArrayList<String> instructions;
    private recipe.foodbar.entities.Cuisine cuisine;
    private ArrayList<String> dietaryRestrictions;
    private Date dateCreated;
    @Builder.Default
    private ArrayList<Ingredient> ingredients = new ArrayList<>();
    @Builder.Default
    private ArrayList<Review> reviews = new ArrayList<>();
    @Builder.Default
    private ArrayList<String> likers = new ArrayList<>();
    @Builder.Default
    private ArrayList<String> dislikers = new ArrayList<>();


//    /**
//     * Constructs a Recipe with id, title, author, portionSize, instructions, cuisine, dietaryRestrictions, dateCreated,
//     * ingredients, and reviews.
//     *
//     * @param id                  The id of the recipe.
//     * @param title               The title of the recipe.
//     * @param user                The author of the recipe.
//     * @param portionSize         The portion size of the recipe.
//     * @param instructions        The instructions of the recipe.
//     * @param cuisine             The cuisine of the recipe.
//     * @param dietaryRestrictions The dietary restrictions of the recipe.
//     * @param dateCreated         The date the recipe was created.
//     * @param ingredients         The ingredients of the recipe.
//     * @param likers              The ID of Users who have liked this recipe.
//     * @param dislikers           The ID of Users who have disliked this recipe.
//     * @param reviews             The reviews of the recipe.
//     */

    /**
     * Sets the portionSize and
     * modifies each ingredient based on the portionSize multiplier.
     *
     * @param portionSize The portion size of the Recipe.
     */
    public void modifyIngredients(int portionSize) {
        this.portionSize = portionSize;
        for (Ingredient ingredient : ingredients) {
            ingredient.setSize(portionSize * ingredient.getSize());
        }
    }

    /**
     * Returns the rating of the recipe.
     *
     * @return int likers.size() - dislikers.size()
     */
    public int getRating() {
        return likers.size() - dislikers.size();
    }

    /**
     * Returns the likes of the recipe.
     *
     * @return int likers.size
     */
    public int getLikes() {
        return likers.size();
    }

    /**
     * Returns the dislikes of the recipe.
     *
     * @return int dislikers.size
     */
    public int getDislikes() {
        return dislikers.size();
    }

    /**
     * Adds the voter to the list of Users who like this recipe. If the voter has previously liked or disliked this recipe,
     * remove that like or dislike before liking this recipe.
     *
     * @param voterID User who liked this recipe
     */
    public void like(String voterID) {
        if (this.likers.contains(voterID)) {
            this.likers.remove(voterID);
        } else if (this.dislikers.contains(voterID)) {
            this.dislikers.remove(voterID);
            this.likers.add(voterID);
        } else {
            this.likers.add(voterID);
        }
    }

    /**
     * Adds the voter to the list of Users who dislike this recipe. If the voter has previously liked or disliked this recipe,
     * remove that like or dislike before disliking this recipe.
     *
     * @param voterID User who disliked this recipe
     */
    public void dislike(String voterID) {
        if (this.dislikers.contains(voterID)) {
            this.dislikers.remove(voterID);
        } else if (this.likers.contains(voterID)) {
            this.likers.remove(voterID);
            this.dislikers.add(voterID);
        } else {
            this.dislikers.add(voterID);
        }
    }

    /**
     * Adds a review to this Recipe.
     *
     * @param review review to be added to recipe
     */
    public void addReview(Review review) {
        this.reviews.add(review);
    }

    /**
     * Adds new ingredients to the ingredients ArrayList.
     *
     * @param ingredient An ingredient.
     */
    public boolean addIngredient(Ingredient ingredient) {
        if (this.ingredients.contains(ingredient)) {
            return true;
        } else {
            this.ingredients.add(ingredient);
            return false;
        }
    }

    /**
     * Removes ingredient from ingredients ArrayList.
     *
     * @param ingredient An ingredient.
     */
    public boolean removeIngredient(Ingredient ingredient) {
        if (this.ingredients.contains(ingredient)) {
            this.ingredients.remove(ingredient);
            return true;
        } else {
            return false;
        }
    }
}
