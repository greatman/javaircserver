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
package com.greatmancode.ircserver.server.net.packet.msg.responses;

import com.greatmancode.ircserver.api.channel.Channel;
import com.greatmancode.ircserver.api.client.Client;
import com.greatmancode.ircserver.api.net.interfaces.Message;

public class RPLNameReplyMessage implements Message {

    private final Client[] clients;
    private final Channel channel;
    private final String nickname;
    public RPLNameReplyMessage(String nickname, Channel channel, Client[] clients) {
        this.nickname = nickname;
        this.channel = channel;
        this.clients = clients;
    }

    public String getNickname() {
        return nickname;
    }

    public Channel getChannel() {
        return channel;
    }

    public Client[] getClients() {
        return clients;
    }
}
