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

import com.greatmancode.ircserver.api.net.interfaces.Message;

public class RPLWhoReplyMessage implements Message {

    //Response
    private final String receiverNickname, channel, username, hostname, server, nickname, hopCount, realName;

    public RPLWhoReplyMessage(String receiverNickname, String channel, String username, String hostname, String server, String nickname, String hopCount, String realName) {
        this.receiverNickname = receiverNickname;
        this.channel = channel;
        this.username = username;
        this.hostname = hostname;
        this.server = server;
        this.nickname = nickname;
        this.hopCount = hopCount;
        this.realName = realName;
    }

    public String getReceiverNickname() {
        return receiverNickname;
    }

    public String getRealName() {
        return realName;
    }

    public String getHopCount() {
        return hopCount;
    }

    public String getNickname() {
        return nickname;
    }

    public String getServer() {
        return server;
    }

    public String getHostname() {
        return hostname;
    }

    public String getUsername() {
        return username;
    }

    public String getChannel() {
        return channel;
    }
}
