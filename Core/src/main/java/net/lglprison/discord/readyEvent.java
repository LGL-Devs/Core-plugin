package net.lglprison.discord;

import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.events.ReadyEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import net.dv8tion.jda.api.interactions.commands.OptionType;
import net.dv8tion.jda.api.interactions.commands.build.Commands;
import net.dv8tion.jda.api.interactions.commands.build.OptionData;
import net.lglprison.util.Chat;

public class readyEvent extends ListenerAdapter {

    public void onReady(ReadyEvent event) {
        for (Guild guild : event.getJDA().getGuilds()) {
            guild.updateCommands().addCommands(
                    Commands.slash("strike", "Gives a User a Strike")
                            .addOption(OptionType.USER, "user", "Targeted user"),
                    Commands.slash("info", "Gets a players info")
                            .addOption(OptionType.USER, "user", "Targeted user"),
                    Commands.slash("selector", "Posts the Roles Selector")
                            .addOption(OptionType.CHANNEL, "channel", "Targeted channel")
                            .addOptions(
                                    new OptionData(OptionType.STRING, "type", "Tickets or Roles")
                                            .addChoice("Tickets", "ticket")
                                            .addChoice("Roles", "roles")
                            ),
                    Commands.slash("status", "Status Command")
                            .addOptions(
                                    new OptionData(OptionType.STRING, "type", "Bot or Server")
                                            .addChoice("Bot", "bot")
                                            .addChoice("Server", "server")
                            )
            ).queue();
            Chat.console(guild.getName() + " - Commands Loaded");
        }
    }

                            /*.addOption(OptionType.USER, "message", "The message to repeat.")
                            .addOption(OptionType.INTEGER, "times", "The number of times to repeat the message.")
                            .addOption(OptionType.BOOLEAN, "ephemeral", "Whether or not the message should be sent as an ephemeral message.")
                    Commands.slash("animal", "Finds a random animal")
                            .addOptions(
                                    new OptionData(OptionType.STRING, "type", "The type of animal to find")
                                            .addChoice("Bird", "bird")
                                            .addChoice("Big Cat", "bigcat")
                                            .addChoice("Canine", "canine")
                                            .addChoice("Fish", "fish")
                            )*/

}
