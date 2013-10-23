package com.greatmancode.ircserver.server.net.packet.handler;

import com.greatmancode.ircserver.api.channel.Channel;
import com.greatmancode.ircserver.api.channel.ChannelModes;
import com.greatmancode.ircserver.api.client.Client;
import com.greatmancode.ircserver.api.net.interfaces.MessageHandler;
import com.greatmancode.ircserver.server.IRCServer;
import com.greatmancode.ircserver.server.net.packet.msg.PrivmsgMessage;

public class PrivmsgHandler extends MessageHandler<PrivmsgMessage>{
    @Override
    public void handle(Client session, PrivmsgMessage message) {

        //If it's a channel
        if (message.getChannel().contains("#")) {
            Channel channel = IRCServer.getInstance().getChannelManager().getChannel(message.getChannel());
            if (channel != null) {
                if (channel.getModeValues(ChannelModes.NO_EXTERNAL_MESAGES).contains("true") && !session.getchannelList().contains(channel)) {
                    //TODO: Access denied
                }
                channel.sendMessage(session, message.getMessage());
            }
        } else {
            Client client = IRCServer.getInstance().getClientManager().getClient(message.getChannel());
            if (client != null) {
                client.sendMessage(session, message.getMessage());
            }
        }
    }
}
