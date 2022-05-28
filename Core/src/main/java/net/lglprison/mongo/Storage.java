package net.lglprison.mongo;

import com.mongodb.BasicDBObject;
import com.mongodb.client.FindIterable;
import com.mongodb.client.model.UpdateOptions;
import com.mongodb.client.model.Updates;
import net.lglprison.util.Chat;
import org.bson.Document;
import org.bson.conversions.Bson;

import java.util.UUID;

public class Storage {
    public enum choices { silvers, doubloons, rubies, discord, blocks, minerank }
    public enum currency { silvers, doubloons, rubies, blocks }

    public static boolean findPlayer(UUID uuid) {
        FindIterable<Document> user = Database.collection.find(new Document("UUID", uuid.toString()));
        //uuid.fromString(arg)
        if(user.first() == null) {
            createPlayer(uuid);
            return false;
        }
        return true;
    }

    public static Document getPlayer(UUID uuid) {
        FindIterable<Document> user = Database.collection.find(new Document("UUID", uuid.toString()));
        if(user.first() == null) {
            createPlayer(uuid);
            Chat.console("User: " + uuid + " not in database");
            return null;
        }
        return user.first();
    }

    public static Document getPlayer(String ID) {
        FindIterable<Document> user = Database.collection.find(new Document("discord", ID));
        if(user.first() == null) {
            Chat.console("Discord User: " + ID + " not in database");
            return null;
        }
        return user.first();
    }

    public static void createPlayer(UUID uuid) {
        BasicDBObject query = new BasicDBObject("UUID", uuid.toString());
        Bson updates = Updates.combine(
                Updates.set("rubies", 0),
                Updates.set("doubloons", 0),
                Updates.set("silvers", 0),
                Updates.set("blocks", 0),
                Updates.set("minerank", 0),
                Updates.set("discord", "")
        );
        UpdateOptions options = new UpdateOptions().upsert(true);
        Database.collection.updateOne(query, updates, options);
    }

    public static void updatePlayer(UUID uuid, currency c, int amount) {

        if(findPlayer(uuid)) {
            BasicDBObject query = new BasicDBObject("UUID", uuid.toString());

            BasicDBObject update = new BasicDBObject("$inc", new BasicDBObject(c.toString(), amount));

            UpdateOptions options = new UpdateOptions().upsert(true);

            Database.collection.updateOne(query, update, options);
        } else {
            createPlayer(uuid);
        }
    }

    public static void setPlayer(UUID uuid, choices c, String input) {

        if(findPlayer(uuid)) {
            BasicDBObject query = new BasicDBObject("UUID", uuid.toString());

            Bson update = Updates.combine(
                    Updates.set(c.toString(), input)
            );

            UpdateOptions options = new UpdateOptions().upsert(true);

            Database.collection.updateOne(query, update, options);
        } else {
            createPlayer(uuid);
        }
    }

    public static void setPlayer(UUID uuid, choices c, int value) {

        if(findPlayer(uuid)) {
            BasicDBObject query = new BasicDBObject("UUID", uuid.toString());

            Bson update = Updates.combine(
                    Updates.set(c.toString(), value)
            );

            UpdateOptions options = new UpdateOptions().upsert(true);

            Database.collection.updateOne(query, update, options);
        } else {
            createPlayer(uuid);
        }
    }

}
