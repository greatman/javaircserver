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

import com.greatmancode.ircserver.api.Representable;
import com.greatmancode.ircserver.api.channel.Channel;
import com.greatmancode.ircserver.api.channel.ChannelModes;
import com.greatmancode.ircserver.api.client.Client;
import com.greatmancode.ircserver.server.IRCServer;
import com.greatmancode.ircserver.server.net.packet.msg.JoinMessage;
import com.greatmancode.ircserver.server.net.packet.msg.ModeMessage;
import com.greatmancode.ircserver.server.net.packet.msg.PrivmsgMessage;
import com.greatmancode.ircserver.server.net.packet.msg.responses.RPLEndOfNamesMessage;
import com.greatmancode.ircserver.server.net.packet.msg.responses.RPLEndOfWhoMessage;
import com.greatmancode.ircserver.server.net.packet.msg.responses.RPLNameReplyMessage;
import com.greatmancode.ircserver.server.net.packet.msg.responses.RPLWhoReplyMessage;

import java.util.*;

public class IRCChannel implements Channel {

    private List<Client> clientList = new ArrayList<>();
    private Map<ChannelModes, List<String>> modeList = new HashMap<>();

    private final String name;

    public IRCChannel(String name) {
        this.name = name;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public void addClient(Client client) {
        if (!clientList.contains(client)) {
            clientList.add(client);
            client.sendPacket(new JoinMessage(name, client));
            client.sendPacket(new RPLNameReplyMessage(client.getNickname(), this, clientList.toArray(new Client[clientList.size()])));
            client.sendPacket(new RPLEndOfNamesMessage(client.getNickname(), name));
            for (Client receiver : clientList) {
                System.out.println("RECEIVER NAME IS :" + receiver.getNickname());
                if (!receiver.equals(client)) {
                    System.out.println("IT ISN'T THE ORIGINAL GUY");
                    receiver.sendPacket(new JoinMessage(name, client));
                }
            }
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
                receiver.sendPacket(new PrivmsgMessage(sender, name, message));
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
    public void changeMode(Representable changer, ChannelModes mode, boolean add, String value) {
        //Do whatever depending of the mode
        System.out.println("SETTING MODE " + mode + " A: " + add + " V:" + value);
        if (mode.equals(ChannelModes.OPERATOR)) {
            if (!modeList.containsKey(ChannelModes.OPERATOR)) {
                modeList.put(ChannelModes.OPERATOR, new ArrayList<String>());
            }
            if (add) {
                modeList.get(ChannelModes.OPERATOR).add(value);
                for (Client client : clientList) {
                    client.sendPacket(new ModeMessage(changer, getName(), "+o", value));
                }
            } else {
                modeList.get(ChannelModes.OPERATOR).remove(value);
                for (Client client : clientList) {
                    client.sendPacket(new ModeMessage(changer, getName(), "-o", value));
                }
            }
        } else {
            if (add) {
                modeList.put(mode, null);
            } else {
                modeList.remove(mode);
            }
        }
    }

    @Override
    public Collection<String> getModeValues(ChannelModes mode) {
        if (modeList.get(mode) == null) {
            return Collections.unmodifiableList(new ArrayList<String>());
        }
        return Collections.unmodifiableCollection(modeList.get(mode));
    }

    @Override
    public void sendWho(Client receiver) {
        for (Client client : clientList) {
            receiver.sendPacket(new RPLWhoReplyMessage(receiver.getNickname(), name, client.getUsername(), client.getHostname(), IRCServer.serverName, client.getNickname(), "0", client.getRealName()));
        }
        receiver.sendPacket(new RPLEndOfWhoMessage(receiver.getNickname(), name));
    }

    @Override
    public String getModes() {
        String result = "";
        for (Map.Entry<ChannelModes, List<String>> modes : modeList.entrySet()) {
            if (!modes.getKey().equals(ChannelModes.BAN) && !modes.getKey().equals(ChannelModes.OPERATOR)) {
                result += modes.getKey().getValue();
            }
        }
        return result;
    }

}
