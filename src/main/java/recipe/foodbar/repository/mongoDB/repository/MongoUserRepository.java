package recipe.foodbar.repository.mongoDB.repository;

import com.mongodb.MongoException;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.codecs.configuration.CodecProvider;
import org.bson.codecs.configuration.CodecRegistry;
import org.bson.codecs.pojo.PojoCodecProvider;
import org.bson.conversions.Bson;
import recipe.foodbar.entities.User;
import recipe.foodbar.repository.mongoDB.MongoDB;
import recipe.foodbar.repository.mongoDB.mapper.UserMapper;
import recipe.foodbar.repository.mongoDB.model.UserModel;
import recipe.foodbar.usecase.user.port.UserRepositoryInterface;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static com.mongodb.MongoClientSettings.getDefaultCodecRegistry;
import static com.mongodb.client.model.Filters.eq;
import static org.bson.codecs.configuration.CodecRegistries.fromProviders;
import static org.bson.codecs.configuration.CodecRegistries.fromRegistries;

public class MongoUserRepository implements UserRepositoryInterface {

    MongoClient mongoClient = new MongoDB().getMongoClient();
    CodecProvider pojoCodecProvider = PojoCodecProvider.builder().automatic(true).build();
    CodecRegistry pojoCodecRegistry = fromRegistries(getDefaultCodecRegistry(), fromProviders(pojoCodecProvider));
    MongoDatabase db = mongoClient.getDatabase("FoodBar").withCodecRegistry(pojoCodecRegistry);
    MongoCollection<UserModel> collection = db.getCollection("User", UserModel.class);

    @Override
    public void create(User user) {
        try {
            UserModel um = UserMapper.toUserModel(user);
//            InsertOneResult result = collection.insertOne(um);
//            System.out.println("Success! Inserted document id: " + result.getInsertedId());
        } catch (MongoException me) {
            System.err.println("Unable to insert user due to an error: " + me);
        }
    }

    @Override
    public Optional<User> findByUsername(String username) {
        Bson query = eq("username", username);
        Optional<UserModel> um = Optional.ofNullable(collection.find(query)
                .first());
        return um.map(UserMapper::toEntity);
    }

    @Override
    public Optional<User> findByEmail(String email) {
        Bson query = eq("email", email);
        Optional<UserModel> um = Optional.ofNullable(collection.find(query)
                .first());
        return um.map(UserMapper::toEntity);
    }

    @Override
    public ArrayList<User> findAllUsers() {
        List<UserModel> userModelList = collection.find().into(new ArrayList<>());
        List<User> userList = userModelList.stream().map(UserMapper::toEntity).collect(Collectors.toList());
        return (ArrayList<User>) userList;
    }

    @Override
    public boolean existsByUsername(String username) {
        return collection.countDocuments(eq("username", username)) > 0;
    }
}
