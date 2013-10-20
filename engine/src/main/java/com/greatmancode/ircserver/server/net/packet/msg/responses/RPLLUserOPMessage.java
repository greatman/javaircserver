package com.greatmancode.ircserver.server.net.packet.msg.responses;

import com.greatmancode.ircserver.api.net.interfaces.Message;

public class RPLLUserOPMessage extends MessageUsername {

    private final int opCount;
    public RPLLUserOPMessage(String nickname, int opCount) {
        super(nickname);
        this.opCount = opCount;
    }

    public int getOpCount() {
        return opCount;
    }
}
