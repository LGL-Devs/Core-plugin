package net.lglprison.discord;

import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.User;
import org.bukkit.entity.Player;

import java.awt.*;

import static net.lglprison.discord.Bot.jda;

public class discordUser {
    public static void AccountLinked(String ID, Player p) {
        User user = jda.getUserById(ID);

        user.openPrivateChannel().flatMap(channel -> channel.sendMessageEmbeds(
                new EmbedBuilder()
                        .setColor(new Color(255, 0, 0))
                        .setTitle("Your MC Account Has been Linked")
                        .setImage("https://crafatar.com/avatars/" + p.getUniqueId().toString())
                        .setDescription("Discord Username / ID : " + user.getName() + " / " + user.getId())
                        .addField("Name", "`" + p.getName() + "`", false)
                        .build()
        )).queue();
    }
}
