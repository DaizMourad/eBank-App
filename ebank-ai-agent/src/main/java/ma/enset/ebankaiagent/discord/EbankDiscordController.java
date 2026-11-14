package ma.enset.ebankaiagent.discord;

import com.zgamelogic.discord.annotations.DiscordController;
import com.zgamelogic.discord.annotations.DiscordMapping;
import ma.enset.ebankaiagent.agents.EbankAiagent;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;

public class EbankDiscordController {
    private EbankAiagent ebankAiagent;
    public EbankDiscordController(EbankAiagent ebankAiagent) {
        this.ebankAiagent = ebankAiagent;
    }
@DiscordMapping
    private void perform(MessageReceivedEvent event) {
        if (event.getAuthor().isBot()) return;
        String query = event.getMessage().getContentRaw();
        String response = ebankAiagent.chat(query);
        event.getChannel().sendMessage(response).queue();
    }

}
