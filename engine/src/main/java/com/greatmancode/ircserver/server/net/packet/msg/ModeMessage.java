package com.greatmancode.ircserver.server.net.packet.msg;

import com.greatmancode.ircserver.api.Representable;
import com.greatmancode.ircserver.api.net.interfaces.Message;

public class ModeMessage extends MessageHeaderRepresentation implements Message {

    //Request
    public final String channel, flag;
    private final String[] users;
    private String action;
    private String parameter;

    public ModeMessage(String channel) {
        super(null);
        this.channel = channel;
        this.flag = null;
        this.users = null;
    }

    public ModeMessage(String channel, String flag) {
        super(null);
        this.channel = channel;
        this.flag = flag;
        this.users = null;

    }

    public ModeMessage(String channel, String flags, String[] users) {
        super(null);
        this.channel = channel;
        this.flag = flags;
        this.users = users;
    }


    //The send constructor
    public ModeMessage(Representable creator, String channel, String action, String parameter) {
        super(creator);
        this.channel = channel;
        this.users = null;
        this.flag = null;
        this.action = action;
        this.parameter = parameter;
    }
    public String getChannel() {
        return channel;
    }

    public String getFlag() {
        return flag;
    }

    public String[] getUsers() {
        return users;
    }

    public String getAction() {
        return action;
    }

    public String getParameter() {
        return parameter;
    }
}
