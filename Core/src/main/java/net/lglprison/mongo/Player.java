package net.lglprison.mongo;

import com.mongodb.BasicDBObject;
import com.mongodb.client.FindIterable;
import com.mongodb.client.model.UpdateOptions;
import com.mongodb.client.model.Updates;
import org.bson.Document;
import org.bson.conversions.Bson;

public class Player {

    public static void findPlayer() {

        FindIterable<Document> user = Database.collection.find(new Document("name", "SirBlob"));// give Document as the find() argument

        if(user.first() == null) {
            System.out.println("User not in database");
            return;
        }

        System.out.println(user.first());
        return;

    }

    public static void createPlayer(org.bukkit.entity.Player p) {

        BasicDBObject query = new BasicDBObject("name", "MongoDB");

        Bson updates = Updates.combine(
                Updates.set("runtime", 99),
                Updates.addToSet("genres", "Sports"),
                Updates.currentTimestamp("lastUpdated")
        );

        UpdateOptions options = new UpdateOptions().upsert(true);

        Database.collection.updateOne(query, updates, options);
    }

    public static void updatePlayer(String type) {

        BasicDBObject query = new BasicDBObject("$inc", "sorghum");

        BasicDBObject update = new BasicDBObject("$inc", new BasicDBObject("name", "d"));

        UpdateOptions options = new UpdateOptions().upsert(true);

        Database.collection.updateOne(query, update, options);
    }

}
