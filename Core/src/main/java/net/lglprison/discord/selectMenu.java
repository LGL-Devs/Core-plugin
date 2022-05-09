package net.lglprison.discord;

import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.events.interaction.component.SelectMenuInteractionEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

public class selectMenu extends ListenerAdapter {
    public void onSelectMenuInteraction(SelectMenuInteractionEvent interaction) {
        if (interaction.getComponentId().equals("choose-roles")) {
            interaction.reply("You chose " + interaction.getValues()).setEphemeral(true).queue();
        }
    }

    private void getRoles(SelectMenuInteractionEvent interaction) {
        Guild guild = interaction.getGuild();
        Member member = guild.getMemberById(interaction.getUser().getIdLong());

    }

}
