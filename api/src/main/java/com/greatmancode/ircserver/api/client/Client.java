/*
 * This file is part of IRCServer API.
 *
 * Copyright (c) 2013-2013, Greatmancode <http://www.greatmancode.com/>
 *
 * IRCServer API is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Lesser General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * IRCServer API is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public License
 * along with IRCServer API.  If not, see <http://www.gnu.org/licenses/>.
 */
package com.greatmancode.ircserver.api.client;

import com.greatmancode.ircserver.api.channel.Channel;
import com.greatmancode.ircserver.api.net.interfaces.Message;
import io.netty.channel.ChannelHandlerContext;

import java.util.List;

public abstract class Client {

    public abstract ChannelHandlerContext getSocket();

    public abstract String getUsername();

    public abstract void setUsername(String username); //TODO: Do some checks if the username already exist

    public abstract String getHostname();

    public abstract void setHostname(String hostname); //TODO: Do some checks if the username already exist

    public abstract String getNickname();

    public abstract void setNickname(String nickname); //TODO: Do some checks if the username already exist

    public abstract String getRealName();

    public abstract void setRealName(String realName);

    public abstract List<Channel> getchannelList();

    public String getRepresentation() {
        return getNickname() + "!" + getUsername() + "@" + getHostname();
    }

    public void disconnect(String disconnectMessage) {
        sendQuit(disconnectMessage);
        getSocket().disconnect();
    }
    public void sendQuit(String quitMessage) {
        for (Channel channel : getchannelList()) {
            channel.sendMessage(this, "");
        }
    }

    public abstract void sendPacket(Message message);
}
