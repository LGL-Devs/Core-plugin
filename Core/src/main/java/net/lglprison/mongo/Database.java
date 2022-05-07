package net.lglprison.mongo;

import com.mongodb.BasicDBObject;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.UpdateOptions;
import com.mongodb.client.model.Updates;
import net.lglprison.util.Chat;
import org.bson.Document;
import org.bson.conversions.Bson;

public class Database {
    private static String Uri = "mongodb://admin:aP%4055word@46.4.55.26:27017";
    public static MongoClient client;
    public static MongoDatabase database;
    public static MongoCollection<Document> collection;
    public static void createPlayer() {

        BasicDBObject query = new BasicDBObject("name", "MongoDB");

        Bson updates = Updates.combine(
                Updates.set("runtime", 99),
                Updates.addToSet("genres", "Sports"),
                Updates.currentTimestamp("lastUpdated")
        );

        UpdateOptions options = new UpdateOptions().upsert(true);

        collection.updateOne(query, updates, options);
    }
    public static void connect() {

        try {
            client = MongoClients.create(Uri);
            database = client.getDatabase("LGL");
            collection = database.getCollection("test");
            createPlayer();
            Chat.console("DataBase Connected");
        } finally {
            return;
        }

    }

    public static void disable() {
        client.close();
        Chat.console("CLosing DataBase");
    }

}
