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
import com.greatmancode.ircserver.api.channel.ChannelModes;
import com.greatmancode.ircserver.api.client.Client;
import com.greatmancode.ircserver.server.net.packet.msg.JoinMessage;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class IRCChannel implements Channel {

    private List<Client> clientList = new ArrayList<>();
    private Map<ChannelModes, List<String>> modeList = new HashMap<>();

    private final String name;
    public IRCChannel(String name) {
        this.name = name;
    }

    public void addClient(Client client) {
        if (!clientList.contains(client)) {
            clientList.add(client);
            client.sendPacket(new JoinMessage(name, client.getRepresentation()));
            //TODO: Send to everybody
        }
    }

    public void removeClient(Client client) {
        clientList.remove(client);
        //TODO: Send the leave packet;
    }

    public void sendMessage(Client sender, String message) {
        if (modeList.containsKey(ChannelModes.NO_EXTERNAL_MESAGES) && !clientList.contains(sender)) {
            return;
        } else {
            for (Client receiver : clientList) {
                //TODO: Send the packet
                //receiver.sendPacket();
            }
        }
    }

    public void sendNotice(Client sender, String message) {
        if (modeList.containsKey(ChannelModes.NO_EXTERNAL_MESAGES) && !clientList.contains(sender)) {
            return;
        } else {
            for (Client receiver : clientList) {
                //TODO: Send the packet
                //receiver.sendPacket();
            }
        }
    }

    public void changeMode(ChannelModes mode, String value) {
        //Do whatever depending of the mode
    }

}
