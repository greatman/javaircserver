/*
 * This file is part of IRCServer Engine.
 *
 * Copyright (c) 2013-2013, Greatmancode <http://www.greatmancode.com/>
 *
 * IRCServer Engine is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Lesser General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * IRCServer Engine is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public License
 * along with IRCServer Engine.  If not, see <http://www.gnu.org/licenses/>.
 */
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
        if (message.getChannel().startsWith("#")) {
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
