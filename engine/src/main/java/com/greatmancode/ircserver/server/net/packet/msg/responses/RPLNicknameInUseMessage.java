package com.greatmancode.ircserver.server.net.packet.msg.responses;

/**
 * Created by greatman on 14-07-27.
 */
public class RPLNicknameInUseMessage extends MessageUsername {

    private final String currentNick;

    public RPLNicknameInUseMessage(String currentNick, String nickname) {
        super(nickname);
        this.currentNick = currentNick;
    }

    public String getCurrentNick() {
        return currentNick;
    }
}
