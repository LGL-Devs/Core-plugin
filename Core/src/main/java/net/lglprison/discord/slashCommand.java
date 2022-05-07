package net.lglprison.discord;

import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.Emoji;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import net.dv8tion.jda.api.interactions.components.selections.SelectMenu;
import net.dv8tion.jda.api.interactions.components.selections.SelectOption;

import java.awt.*;

public class slashCommand extends ListenerAdapter {
    @Override
    public void onSlashCommandInteraction(SlashCommandInteractionEvent interaction) {

        if(interaction.getName().equals("help")) {
            interaction.replyEmbeds(
                            new EmbedBuilder()
                                    .setColor(new Color(255, 0, 54))
                                    .setTitle("Testing")
                                    .setDescription("Text")
                                    .build()
                    )
                    .addActionRow(
                            SelectMenu.create("choose-roles")
                                    .setMaxValues(5)
                                    .setMinValues(1)
                                    .addOption("Pizza", "pizza", "Classic")
                                    .addOptions(SelectOption.of("Hamburger", "hamburger")
                                            .withDescription("Tasty")
                                            .withEmoji(Emoji.fromUnicode("\uD83C\uDF54"))
                                    )
                                    .build()
                    )
                    .queue();
        }
    }
}
