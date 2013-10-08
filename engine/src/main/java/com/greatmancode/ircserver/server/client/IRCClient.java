package com.greatmancode.ircserver.server.client;

import com.greatmancode.ircserver.api.channel.Channel;
import com.greatmancode.ircserver.api.client.Client;
import io.netty.channel.ChannelHandlerContext;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class IRCClient extends Client {

    private List<Channel> channelList = new ArrayList<Channel>();
    private String username;
    private String hostname;
    private String nickname;
    private ChannelHandlerContext socket;

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
    public String getHostname() {
        return hostname;
    }

    @Override
    public String getNickname() {
        return nickname;
    }

    @Override
    public List<Channel> getchannelList() {
        return Collections.unmodifiableList(channelList);
    }
}
