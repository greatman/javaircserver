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
package com.greatmancode.ircserver.server.channel;

import com.greatmancode.ircserver.api.channel.Channel;
import com.greatmancode.ircserver.api.channel.ChannelManager;
import com.greatmancode.ircserver.api.channel.ChannelModes;
import com.greatmancode.ircserver.api.client.Client;

import java.util.HashMap;
import java.util.Map;

public class IRCChannelManager implements ChannelManager{

    private final Map<String, Channel> channels = new HashMap<>();

    public void joinChannel(Client client, String channelName) {
        Channel channel = channels.get(channelName);
        if (channel == null) {
            channel = new IRCChannel(channelName);
            channel.changeMode(ChannelModes.NO_EXTERNAL_MESAGES, null);
            channel.changeMode(ChannelModes.TOPIC_LOCK, null);
            channel.addClient(client);
            channel.changeMode(ChannelModes.OPERATOR, client.getNickname());
            channels.put(channelName,channel);
        } else {
            channel.addClient(client);
        }
    }

    public void leaveChannel(Client client, String channelName) {
        Channel channel = channels.get(channelName);
        if (channel != null) {
            channel.removeClient(client);
        }
    }

    @Override
    public Channel getChannel(String channel) {
        return channels.get(channel);
    }
}
