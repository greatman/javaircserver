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
package com.greatmancode.ircserver.server.client;

import com.greatmancode.ircserver.api.channel.Channel;
import com.greatmancode.ircserver.api.client.Client;
import com.greatmancode.ircserver.api.client.UserMode;
import com.greatmancode.ircserver.api.net.interfaces.Message;
import com.greatmancode.ircserver.server.IRCServer;
import com.greatmancode.ircserver.server.channel.IRCChannel;
import com.greatmancode.ircserver.server.net.packet.msg.responses.RPLCreatedMessage;
import com.greatmancode.ircserver.server.net.packet.msg.responses.RPLMyInfoMessage;
import com.greatmancode.ircserver.server.net.packet.msg.responses.RPLWelcomeMessage;
import com.greatmancode.ircserver.server.net.packet.msg.responses.RPLYourHostMessage;
import io.netty.channel.ChannelHandlerContext;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

public class IRCClient extends Client {

    private List<Channel> channelList = new ArrayList<Channel>();
    private String username;
    private String hostname;
    private String nickname;
    private String realname;
    private ChannelHandlerContext socket;
    private boolean ready = false;

    public IRCClient(ChannelHandlerContext socket) {
        this.socket = socket;
    }

    @Override
    public ChannelHandlerContext getSocket() {
        return socket;
    }

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public void setUsername(String username) {
        this.username = username;
        isReady();
    }

    @Override
    public String getHostname() {
        return hostname;
    }

    @Override
    public void setHostname(String hostname) {
        this.hostname = hostname;
        isReady();
    }

    @Override
    public String getNickname() {
        return nickname;
    }

    @Override
    public void setNickname(String nickname) {
        this.nickname = nickname;
        isReady();
    }

    @Override
    public String getRealName() {
        return realname;
    }

    @Override
    public void setRealName(String realName) {
        this.realname = realName;
        isReady();
    }

    @Override
    public List<Channel> getchannelList() {
        return Collections.unmodifiableList(channelList);
    }

    private void isReady() {
        System.out.println("USERNAME: " + getUsername() + " HOSTNAME:" + getHostname() + " NICKNAME:" + getNickname() + " REALNAME:" + getRealName());
        if (getUsername() != null && getHostname() != null && getNickname() != null && getRealName() != null) {
            sendPacket(new RPLWelcomeMessage(getNickname(), getRepresentation()));
            sendPacket(new RPLYourHostMessage(getNickname(), IRCServer.serverName, IRCServer.version));
            sendPacket(new RPLCreatedMessage(getNickname(), new Date().toString()));
            sendPacket(new RPLMyInfoMessage(getNickname(), IRCServer.serverName, IRCServer.version, UserMode.getConnectString(), ""));
            ready = true;
        }
    }

    @Override
    public void sendPacket(Message message) {
        System.out.println("SENDING PACKET");
        getSocket().channel().writeAndFlush(message);
    }
}
