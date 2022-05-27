package net.lglprison.discord;

import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.events.interaction.component.SelectMenuInteractionEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

import java.util.List;

import static net.lglprison.discord.Bot.jda;

public class selectMenu extends ListenerAdapter {
    public void onSelectMenuInteraction(SelectMenuInteractionEvent interaction) {
        if (interaction.getComponentId().equals("choose-roles")) {
            interaction.reply("You chose these roles" + interaction.getValues()).setEphemeral(true).queue();

            List<String> roleList = interaction.getValues();

            if(roleList.contains("keyalls")) {
                interaction.getGuild().addRoleToMember(interaction.getMember(), jda.getRoleById("960976236514000906"));
            } else {
                interaction.getGuild().removeRoleFromMember(interaction.getMember(), jda.getRoleById("960976236514000906"));
            }

            if(roleList.contains("revive")) {
                interaction.getGuild().addRoleToMember(interaction.getMember(), jda.getRoleById("735404147662258187"));
            } else {
                interaction.getGuild().removeRoleFromMember(interaction.getMember(), jda.getRoleById("735404147662258187"));
            }

            if(roleList.contains("updates")) {
                interaction.getGuild().addRoleToMember(interaction.getMember(), jda.getRoleById("960975995320565821"));
            } else {
                interaction.getGuild().removeRoleFromMember(interaction.getMember(), jda.getRoleById("960975995320565821"));
            }

            if(roleList.contains("giveaways")) {
                interaction.getGuild().addRoleToMember(interaction.getMember(), jda.getRoleById("723112997094031460"));
            } else {
                interaction.getGuild().removeRoleFromMember(interaction.getMember(), jda.getRoleById("723112997094031460"));
            }

            if(roleList.contains("peaks")) {
                interaction.getGuild().addRoleToMember(interaction.getMember(), jda.getRoleById("960976060437118977"));
            } else {
                interaction.getGuild().removeRoleFromMember(interaction.getMember(), jda.getRoleById("960976060437118977"));
            }

        }
    }

}
