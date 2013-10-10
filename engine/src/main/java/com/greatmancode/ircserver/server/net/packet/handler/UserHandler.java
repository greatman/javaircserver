package com.greatmancode.ircserver.server.net.packet.handler;

import com.greatmancode.ircserver.api.client.Client;
import com.greatmancode.ircserver.api.net.interfaces.MessageHandler;
import com.greatmancode.ircserver.server.net.packet.msg.UserMessage;

public class UserHandler extends MessageHandler<UserMessage> {
    @Override
    public void handle(Client session, UserMessage message) {
        session.setUsername(message.getUsername());
        session.setRealName(message.getRealName());
    }
}
