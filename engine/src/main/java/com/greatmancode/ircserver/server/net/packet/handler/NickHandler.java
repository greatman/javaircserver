package com.greatmancode.ircserver.server.net.packet.handler;

import com.greatmancode.ircserver.api.client.Client;
import com.greatmancode.ircserver.api.net.interfaces.MessageHandler;
import com.greatmancode.ircserver.server.net.packet.msg.NickMessage;

public class NickHandler extends MessageHandler<NickMessage>{
    @Override
    public void handle(Client session, NickMessage message) {
        System.out.println("HANDLING IT");
        session.setNickname(message.getNickname());
    }
}
