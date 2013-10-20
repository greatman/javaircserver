package com.greatmancode.ircserver.server.net.packet.msg.responses;


public class RPLLUserClientMessage extends MessageUsername {

    private final int userCount, serviceCount, serverCount;
    public RPLLUserClientMessage(String username, int userCount, int serviceCount, int serverCount) {
        super(username);
        this.userCount = userCount;
        this.serviceCount = serviceCount;
        this.serverCount = serverCount;
    }

    public int getUserCount() {
        return userCount;
    }

    public int getServerCount() {
        return serverCount;
    }

    public int getServiceCount() {
        return serviceCount;
    }
}
