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
    private String realname;
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
    public void setUsername(String username) {
        this.username = username;
    }

    @Override
    public String getHostname() {
        return hostname;
    }

    @Override
    public void setHostname(String hostname) {
        this.hostname = hostname;
    }

    @Override
    public String getNickname() {
        return nickname;
    }

    @Override
    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    @Override
    public String getRealName() {
        return realname;
    }

    @Override
    public void setRealName(String realName) {
        this.realname = realName;
    }

    @Override
    public List<Channel> getchannelList() {
        return Collections.unmodifiableList(channelList);
    }
}
