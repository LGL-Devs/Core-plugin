package net.lglprison.discord;

import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.Emoji;
import net.dv8tion.jda.api.entities.User;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.interactions.components.selections.SelectMenu;
import net.dv8tion.jda.api.interactions.components.selections.SelectOption;

import java.awt.*;


public class Commands {

    public static void help(SlashCommandInteractionEvent interaction) {
        return;
    }
    public static void status(SlashCommandInteractionEvent interaction) {
        return;
    }
    public static void info(SlashCommandInteractionEvent interaction) {

        User target = (User) interaction.getOption("user");
        System.out.println(target);

        interaction.replyEmbeds(
                new EmbedBuilder()
                        .setColor(new Color(255, 0, 0))
                        .setTitle("inter")
                        .build()
        );

    }
    public static void seletor(SlashCommandInteractionEvent interaction) {

        interaction.replyEmbeds(
                        new EmbedBuilder()
                                .setColor(new Color(255, 0, 0))
                                .setTitle("Notification Roles")
                                .setDescription(
                                        "\uD83D\uDD11 : Get notified for keyalls\n" +
                                                "\n" +
                                                "☠️ : Get notified to revive chat\n" +
                                                "\n" +
                                                "\uD83D\uDCE3 : Get notified for server updates\n" +
                                                "\n" +
                                                "\uD83C\uDF81 : Get notified for new giveaways\n" +
                                                "\n" +
                                                "\uD83D\uDC40 : Get notified for new sneak peaks\n"
                                )
                                .build()
                )
                .addActionRow(
                        SelectMenu.create("choose-roles")
                                .setMaxValues(5)
                                .setMinValues(1)
                                .addOptions(SelectOption.of("Keyalls", "keyalls")
                                        .withEmoji(Emoji.fromUnicode("\uD83D\uDD11"))
                                )
                                .addOptions(SelectOption.of("Revive Chat", "revive")
                                        .withEmoji(Emoji.fromUnicode("☠"))
                                )
                                .addOptions(SelectOption.of("Server Updates", "updates")
                                        .withEmoji(Emoji.fromUnicode("\uD83D\uDCE3"))
                                )
                                .addOptions(SelectOption.of("Giveaways", "giveaways")
                                        .withEmoji(Emoji.fromUnicode("\uD83C\uDF81"))
                                )
                                .addOptions(SelectOption.of("Sneak Peaks", "peaks")
                                        .withEmoji(Emoji.fromUnicode("\uD83D\uDC40"))
                                )
                                .build()
                )
                .queue();
    }
}
