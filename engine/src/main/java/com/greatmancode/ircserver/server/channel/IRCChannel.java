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
import com.greatmancode.ircserver.server.net.packet.msg.PrivmsgMessage;
import com.greatmancode.ircserver.server.net.packet.msg.responses.RPLEndOfNamesMessage;
import com.greatmancode.ircserver.server.net.packet.msg.responses.RPLNameReplyMessage;

import java.util.*;

public class IRCChannel implements Channel {

    private List<Client> clientList = new ArrayList<>();
    private Map<ChannelModes, List<String>> modeList = new HashMap<>();

    private final String name;

    public IRCChannel(String name) {
        this.name = name;
    }

    @Override
    public void addClient(Client client) {
        if (!clientList.contains(client)) {
            clientList.add(client);
            client.sendPacket(new JoinMessage(name, client.getRepresentation()));
            List<String> clientlistEntry = new ArrayList<>();
            for (Client clientEntry : clientList) {
                System.out.println("ADDING " + clientEntry.getNickname());
                clientlistEntry.add(clientEntry.getNickname());
            }
            client.sendPacket(new RPLNameReplyMessage(client.getNickname(), name, clientlistEntry.toArray(new String[clientlistEntry.size()])));
            client.sendPacket(new RPLEndOfNamesMessage(client.getNickname(), name));
            //TODO: Send to everybody
        }
    }

    @Override
    public void removeClient(Client client) {
        clientList.remove(client);
        //TODO: Send the leave packet;
    }

    @Override
    public void sendMessage(Client sender, String message) {
        if (modeList.containsKey(ChannelModes.NO_EXTERNAL_MESAGES) && !clientList.contains(sender)) {
            return;
        } else {
            for (Client receiver : clientList) {
                if (receiver.equals(sender)) {
                    continue;
                }
                receiver.sendPacket(new PrivmsgMessage(sender.getRepresentation(), name, message));
            }
        }
    }

    @Override
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

    @Override
    public void changeMode(ChannelModes mode, String value) {
        //Do whatever depending of the mode
    }

    @Override
    public Collection<String> getModeValues(ChannelModes mode) {
        if (modeList.get(mode) == null) {
            return Collections.unmodifiableList(new ArrayList<String>());
        }
        return Collections.unmodifiableCollection(modeList.get(mode));
    }

}
