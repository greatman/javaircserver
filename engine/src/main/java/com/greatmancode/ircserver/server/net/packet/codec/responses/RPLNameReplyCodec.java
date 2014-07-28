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
package com.greatmancode.ircserver.server.net.packet.codec.responses;

import com.greatmancode.ircserver.api.channel.ChannelModes;
import com.greatmancode.ircserver.api.client.Client;
import com.greatmancode.ircserver.api.net.interfaces.MessageCodec;
import com.greatmancode.ircserver.server.net.packet.msg.responses.RPLNameReplyMessage;

public class RPLNameReplyCodec extends MessageCodec<RPLNameReplyMessage>{

    public RPLNameReplyCodec() {
        super(RPLNameReplyMessage.class, "353");
    }

    public String encode(RPLNameReplyMessage message) {
        String result = message.getNickname() + " = " + message.getChannel().getName() + " :";
        for (Client client : message.getClients()) {
            if (message.getChannel().getModeValues(ChannelModes.OPERATOR).contains(client.getNickname())) {
                result += "@";
            }
            result += client.getNickname() + " ";
        }
        return result;
    }
}
