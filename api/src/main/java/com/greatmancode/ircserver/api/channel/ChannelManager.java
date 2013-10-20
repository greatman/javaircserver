package com.greatmancode.ircserver.api.channel;

import com.greatmancode.ircserver.api.client.Client;

import java.util.HashMap;
import java.util.Map;

public class ChannelManager {

    private final Map<String, Channel> channels = new HashMap<>();

    public void joinChannel(Client client, String channelName) {
        Channel channel = channels.get(channelName);
        if (channel == null) {
            channel = new Channel();
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
}
