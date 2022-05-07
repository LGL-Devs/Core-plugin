package net.lglprison.discord;

import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.entities.Activity;
import net.lglprison.util.Chat;

import javax.security.auth.login.LoginException;

import static net.lglprison.Main.config;

public class bot {
    public static JDA jda;
    public static void enable() throws LoginException, InterruptedException {

        //TextChannel textChannel = event.getGuild().getTextChannelsByName("CHANNEL_NAME",true).get(0);
        //textChannel.sendMessage("MESSAGE").queue();

        String token = config.getString("bot-token");

        if(token.length() > 1) {

            jda = JDABuilder.createDefault(token)
                    .setActivity(Activity.playing("LGL Prisons"))
                    .addEventListeners(new slashCommand())
                    .addEventListeners(new selectMenu())
                    .build();
            jda.awaitReady();
            Chat.console("Discord Bot Online - " + jda.getSelfUser().getName());
        } else {
            Chat.console("Failed to build the Bot");
        }

    }

    public static void disable() {
        jda.shutdown();
        Chat.console("Bot Shutting Down");
    }
}
