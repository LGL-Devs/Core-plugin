package net.lglprison.mongo;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import net.lglprison.util.Chat;
import org.bson.Document;

import java.util.HashMap;
import java.util.UUID;
import static net.lglprison.Main.config;

public class Database {

    public static HashMap<UUID, Integer> blockbroken = new HashMap<UUID, Integer>();
    public static MongoClient client;
    public static MongoDatabase database;
    public static MongoCollection<Document> collection;

    public static void connect() {

        if(config.getString("DBusername") == null && config.getString("DBpassword") == null && config.getString("DBuri") == null) {
            Chat.console("No DataBase Uri Found");
            return;
        }

        try {

            client = MongoClients.create("mongodb+srv://" + config.getString("DBusername") + ":" + config.getString("DBpassword") + "@" + config.getString("DBuri"));

        } finally {

            database = client.getDatabase("LGL");
            collection = database.getCollection("test");
            Chat.console("DataBase Connected");
            return;

        }

    }


    public static void disable() {
        client.close();
        Chat.console("Closing DataBase");
    }

}
