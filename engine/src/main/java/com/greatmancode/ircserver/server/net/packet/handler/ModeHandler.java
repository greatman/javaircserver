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
                if (message.getFlag() != null) {
                    ChannelModes mode = ChannelModes.getValue(message.getFlag());
                    Collection<String> values = channel.getModeValues(mode);
                    for (String value : values) {
                        //Do whatever
                    }
                    if (mode.equals(ChannelModes.BAN)) {
                        session.sendPacket(new RPLEndOfBanListMessage(session.getNickname(), channel.getName()));
                    }
                } else {
                    //The client request information about the channel. Let's give them to him
                    session.sendPacket(new RPLChannelModeIsMessage(session.getNickname(), channel.getName(), channel.getModes()));
                }
            }
        }
    }
}
