package net.lglprison.mongo;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import net.lglprison.util.Chat;
import org.bson.Document;

import static net.lglprison.Main.config;

public class Database {
    public static MongoClient client;
    public static MongoDatabase database;
    public static MongoCollection<Document> collection;

    public static void connect() {

        if(config.getString("DBusername") == null && config.getString("DBpassword") == null && config.getString("DBuri") == null) return;

        try {
            client = MongoClients.create("mongodb://" + config.getString("DBusername") + ":" + config.getString("DBpassword") + "@" + config.getString("DBuri"));
            database = client.getDatabase("LGL");
            collection = database.getCollection("test");
        } finally {
            Chat.console("DataBase Connected");
            Chat.console(config.getString("Uri"));
            return;
        }

    }

    public static void disable() {
        client.close();
        Chat.console("Closing DataBase");
    }

}
