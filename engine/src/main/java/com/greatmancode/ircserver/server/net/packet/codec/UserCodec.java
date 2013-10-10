package com.greatmancode.ircserver.server.net.packet.codec;

import com.greatmancode.ircserver.api.net.interfaces.MessageCodec;
import com.greatmancode.ircserver.server.net.packet.msg.UserMessage;

import java.io.IOException;

public class UserCodec extends MessageCodec<UserMessage>{

    public UserCodec() {
        super(UserMessage.class, "USER");
    }

    public UserMessage decode(String[] buffer) throws IOException {
        return new UserMessage(buffer[0], Integer.parseInt(buffer[1]), buffer[2], buffer[3]);
    }
}
