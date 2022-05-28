package net.lglprison.discord;

import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;

import net.lglprison.util.Chat;

import static net.lglprison.Main.config;

import javax.security.auth.login.LoginException;

public class Bot {
    public static JDA jda;
    public static void enable() throws LoginException, InterruptedException {

        //TextChannel textChannel = event.getGuild().getTextChannelsByName("CHANNEL_NAME",true).get(0);
        //textChannel.sendMessage("MESSAGE").queue();

        String token = config.getString("bottoken");

        if(token != null) {

            jda = JDABuilder.createDefault(token)
                    /*.setEnabledIntents(
                            GatewayIntent.GUILD_MEMBERS,
                            GatewayIntent.DIRECT_MESSAGES,
                            GatewayIntent.GUILD_WEBHOOKS,
                            GatewayIntent.GUILD_PRESENCES,
                            GatewayIntent.GUILD_VOICE_STATES,
                            GatewayIntent.GUILD_EMOJIS
                    )*/
                    //.setActivity(Activity.playing("LGL Prisons"))
                    .addEventListeners(new readyEvent())
                    .addEventListeners(new slashCommand())
                    .addEventListeners(new selectMenu())
                    .build().awaitReady();
            Chat.console("Discord Bot Online - " + jda.getSelfUser().getName());

        } else {
            Chat.console("Failed to build the Bot");
        }

    }
    public static void shutdown() {
        if(jda != null) {
            jda.shutdown();
        }
        Chat.console("Bot Shutting Down");
    }

}
