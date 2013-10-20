package com.greatmancode.ircserver.server.net.packet.msg.responses;

public class RPLLUserMeMessage extends MessageUsername{

    private final int clientCount, serverCount;
    public RPLLUserMeMessage(String nickname, int clientCount, int serverCount) {
        super(nickname);
        this.clientCount = clientCount;
        this.serverCount = serverCount;
    }

    public int getClientCount() {
        return clientCount;
    }

    public int getServerCount() {
        return serverCount;
    }
}
