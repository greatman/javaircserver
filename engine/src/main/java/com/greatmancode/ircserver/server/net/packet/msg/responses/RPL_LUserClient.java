package com.greatmancode.ircserver.server.net.packet.msg.responses;

import com.greatmancode.ircserver.api.net.interfaces.Message;

public class RPL_LUserClient implements Message {

    private final int userCount, serviceCount, serverCount;
    public RPL_LUserClient(int userCount, int serviceCount, int serverCount) {
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
