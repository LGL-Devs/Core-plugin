package net.lglprison.discord;

import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

public class slashCommand extends ListenerAdapter {
    @Override
    public void onSlashCommandInteraction(SlashCommandInteractionEvent interaction) {

        if(interaction.getName().equals("help")) {
            return;
        }
        if(interaction.getName().equals("selector")) {
            Commands.seletor(interaction);
        }
        if(interaction.getName().equals("status")) {
            return;
        }
    }
}
