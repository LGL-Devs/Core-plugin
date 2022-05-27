package net.lglprison.mongo;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import net.lglprison.util.Chat;
import org.bson.Document;

import static net.lglprison.Main.config;

public class Database {
    public static MongoClient client = null;
    public static MongoDatabase database;
    public static MongoCollection<Document> collection;

    public static Storage storage;

    public static void connect() {

        if(config.getString("DBusername") == null && config.getString("DBpassword") == null && config.getString("DBuri") == null) {
            Chat.console("No DataBase Uri Found");
            return;
        }

        try {

            client = MongoClients.create("mongodb+srv://" + config.getString("DBusername") + ":" + config.getString("DBpassword") + "@" + config.getString("DBuri"));

        } finally {

            String col = config.getString("collection");

            database = client.getDatabase("LGL");

            if(col != null) {
                collection = database.getCollection(col);
                Chat.console("DataBase Connected - " + config.getString("collection"));
            } else {
                collection = database.getCollection("test");
                Chat.console("DataBase Connected - test");
            }

            return;

        }

    }
    public static void disable() {

        if(client != null) {
            client.close();
        }
        Chat.console("Closing DataBase");
    }

}
