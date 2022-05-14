package net.lglprison.mongo;

import com.mongodb.BasicDBObject;
import com.mongodb.client.FindIterable;
import com.mongodb.client.model.UpdateOptions;
import com.mongodb.client.model.Updates;
import net.lglprison.util.Chat;
import org.bson.Document;
import org.bson.conversions.Bson;

public class Player {

    public enum currency { silvers, doubloons, rubies, blocks }

    public static boolean findPlayer(org.bukkit.entity.Player p) {

        FindIterable<Document> user = Database.collection.find(new Document("UUID", p.getUniqueId()));

        if(user.first() == null) {
            System.out.println("User not in database");
            return false;
        }

        System.out.println(user.first());
        return true;

    }

    public static Document getPlayer(org.bukkit.entity.Player p) {

        FindIterable<Document> user = Database.collection.find(new Document("UUID", p));

        if(user.first() == null) {
            Chat.console("User: " + p + " not in database");
            return null;
        }

        return user.first();

    }

    public static void createPlayer(org.bukkit.entity.Player p) {

        BasicDBObject query = new BasicDBObject("UUID", p);

        Bson updates = Updates.combine(
                Updates.set("rubies", 0),
                Updates.set("doubloons", 0),
                Updates.set("silvers", 0),
                Updates.set("blocks", 0)
        );

        UpdateOptions options = new UpdateOptions().upsert(true);

        Database.collection.updateOne(query, updates, options);
    }

    public static void updatePlayer(org.bukkit.entity.Player p, currency c, int amount) {

        if(findPlayer(p)) {
            BasicDBObject query = new BasicDBObject("UUID", p.getUniqueId());

            BasicDBObject update = new BasicDBObject("$inc", new BasicDBObject(c.toString(), amount));

            UpdateOptions options = new UpdateOptions().upsert(true);

            Database.collection.updateOne(query, update, options);
        } else {
            createPlayer(p);
        }
    }

}
