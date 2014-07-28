package com.greatmancode.ircserver.server.net.packet.handler;

import com.greatmancode.ircserver.api.channel.Channel;
import com.greatmancode.ircserver.api.channel.ChannelModes;
import com.greatmancode.ircserver.api.client.Client;
import com.greatmancode.ircserver.api.net.interfaces.MessageHandler;
import com.greatmancode.ircserver.server.IRCServer;
import com.greatmancode.ircserver.server.net.packet.msg.ModeMessage;
import com.greatmancode.ircserver.server.net.packet.msg.responses.RPLChannelModeIsMessage;
import com.greatmancode.ircserver.server.net.packet.msg.responses.RPLEndOfBanListMessage;

import java.util.Collection;

public class ModeHandler extends MessageHandler<ModeMessage>{
    @Override
    public void handle(Client session, ModeMessage message) {

        //If it's a channel
        if (message.getChannel().startsWith("#")) {
            Channel channel = IRCServer.getInstance().getChannelManager().getChannel(message.getChannel());

            if (channel != null) {
                //We first check if we have a general /mode #channel
                if (message.getFlag() == null) {
                    //The client request information about the channel. Let's give them to him
                    session.sendPacket(new RPLChannelModeIsMessage(session.getNickname(), channel.getName(), channel.getModes()));
                } else if (message.getFlag() != null && message.getUsers() == null) { //Are we setting channel settings?
                    ChannelModes mode = ChannelModes.getValue(message.getFlag());
                    Collection<String> values = channel.getModeValues(mode);
                    for (String value : values) {
                        //Do whatever
                    }
                    if (mode.equals(ChannelModes.BAN)) {
                        session.sendPacket(new RPLEndOfBanListMessage(session.getNickname(), channel.getName()));
                    }
                } else {
                    String mode = "";
                    for (int i = 0; i < message.getFlag().length(); i++) {
                        //If it is the first character, it is to set if we add or remove a user from a mode
                        if (i == 0) {
                            mode = Character.toString(message.getFlag().charAt(i));
                            continue;
                        }
                        channel.changeMode(session, ChannelModes.getValue(Character.toString(message.getFlag().charAt(i))), mode.equals("+"), message.getUsers()[i - 1]);
                    }
                }
            }
        }
    }
}
