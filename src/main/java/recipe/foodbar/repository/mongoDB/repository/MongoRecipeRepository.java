package recipe.foodbar.repository.mongoDB.repository;

import com.mongodb.MongoException;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.ReplaceOptions;
import com.mongodb.client.result.InsertOneResult;
import com.mongodb.client.result.UpdateResult;
import org.bson.BsonDocument;
import org.bson.BsonInt64;
import org.bson.Document;
import org.bson.conversions.Bson;
import org.bson.types.ObjectId;

import recipe.foodbar.entities.Cuisine;
import recipe.foodbar.entities.Recipe;
import recipe.foodbar.repository.mongoDB.mapper.RecipeMapper;
import recipe.foodbar.repository.mongoDB.model.RecipeModel;
import recipe.foodbar.usecase.recipe.port.RecipeRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static com.mongodb.client.model.Filters.eq;

/**
 * MongoRecipeRepository implements RecipeRepository for MongoDB
 */
public class MongoRecipeRepository implements RecipeRepository {
    MongoCollection<RecipeModel> collection;

    /**
     * Constructor responsible for creating Recipe collection
     * @param db MongoDatabase instance
     */
    public MongoRecipeRepository(MongoDatabase db) {
        Bson command = new BsonDocument("ping", new BsonInt64(1));
        Document commandResult = db.runCommand(command);
        System.out.println(this.getClass().getSimpleName() + ": Connected successfully to server." + commandResult);
        collection = db.getCollection("Recipes", RecipeModel.class);
    }

    /**
     * Method resposible getting all users from DB
     *
     * @return ArrayList of Recipe
     */
    @Override
    public ArrayList<Recipe> getAllRecipes() {
        List<RecipeModel> recipeModelList = collection.find().into(new ArrayList<>());
        List<Recipe> recipeList = recipeModelList.stream().map(RecipeMapper::toEntity).collect(Collectors.toList());
        return (ArrayList<Recipe>) recipeList;
    }

    /**
     * @param recipe recipe to be created
     * @return the created Recipe Object
     */
    @Override
    public Recipe create(Recipe recipe) {
        try {
            RecipeModel rm = RecipeMapper.toUserModel(recipe);
            InsertOneResult result = collection.insertOne(rm);
            System.out.println("Success! Inserted document id: " + result.getInsertedId());
        } catch (MongoException me) {
            System.err.println("Unable to insert user due to an error: " + me);
        }
        return recipe;
    }

    /**
     * @param recipe the recipe to be updated
     * @return the updated Recipe Object
     */
    @Override
    public Recipe update(Recipe recipe) {
        RecipeModel rm = RecipeMapper.toUserModel(recipe);
        Bson query = eq("_id", new ObjectId(recipe.getId()));
        ReplaceOptions opts = new ReplaceOptions().upsert(true);
        UpdateResult result = collection.replaceOne(query, rm, opts);
        System.out.println(result);
        return findById(recipe.getId()).get();
    }

    /**
     * @param cuisine cuisine to search for
     * @return ArrayList of all recipe that match a particular cuisine
     */
    @Override
    public ArrayList<Recipe> getByCuisine(Cuisine cuisine) {
        Bson query = eq("cuisine.name", cuisine.getName());
        List<RecipeModel> recipeModelList = collection.find(query).into(new ArrayList<>());
        List<Recipe> recipeList = recipeModelList.stream().map(RecipeMapper::toEntity).collect(Collectors.toList());
//        List<Recipe> recipeList = recipeModelList.stream().map(RecipeMapper::toEntity).collect(Collectors.toList());
        return (ArrayList<Recipe>) recipeList;
    }

    /**
     * @param id recipe id
     * @return an Optional if matched recipe is found
     */
    @Override
    public Optional<Recipe> findById(String id) {
        try {
            Bson query = eq("_id", new ObjectId(id));
            Optional<RecipeModel> rm = Optional.ofNullable(collection.find(query).first());
            return rm.map(RecipeMapper::toEntity);
        } catch (Exception e){
            return Optional.empty();
        }
    }
}
