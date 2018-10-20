package net.stashai;

import net.stashai.events.AnnotationListener;
import sx.blah.discord.api.ClientBuilder;
import sx.blah.discord.api.IDiscordClient;
import sx.blah.discord.api.events.EventDispatcher;
import sx.blah.discord.util.DiscordException;

import java.util.logging.Logger;

//TODO Implement next game database, work on URL Encoder?, add implementation for a D&D helper
//Link to add bot: https://discordapp.com/api/oauth2/authorize?client_id=491779560577433609&permissions=36883520&scope=bot
public class Bot {

    static Logger logger = Logger.getLogger("Stash AI");
    public static final String BOT_PREFIX = "?";

    public static void main(String[] args) {
        logger.info("LOGGING IN");
        IDiscordClient client = Bot.createClient(args[0], true);
        logger.info("REGISTERING CLIENT DISPATCHER");
        EventDispatcher dispatcher = client.getDispatcher();
        logger.info("REGISTERING LISTENERS");
        dispatcher.registerListener(new AnnotationListener());
    }

    public static IDiscordClient createClient(String token, boolean login) {
        ClientBuilder clientBuilder = new ClientBuilder();
        clientBuilder.withToken(token);
        try {
            if (login) {
                return clientBuilder.login();
            } else {
                return clientBuilder.build();
            }
        } catch (DiscordException e) {
            e.printStackTrace();
            return null;
        }
    }
}
