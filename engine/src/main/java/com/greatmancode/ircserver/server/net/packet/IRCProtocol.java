package com.greatmancode.ircserver.server.net.packet;

import com.greatmancode.ircserver.api.net.Protocol;
import com.greatmancode.ircserver.api.net.interfaces.MessageCodec;
import com.greatmancode.ircserver.server.IRCServer;
import com.greatmancode.ircserver.server.net.packet.codec.NickCodec;
import com.greatmancode.ircserver.server.net.packet.codec.UserCodec;
import com.greatmancode.ircserver.server.net.packet.handler.NickHandler;
import com.greatmancode.ircserver.server.net.packet.handler.UserHandler;
import com.greatmancode.ircserver.server.net.packet.msg.NickMessage;

import java.util.HashMap;
import java.util.Map;

public class IRCProtocol extends Protocol {

    public IRCProtocol() {
        registerPacket(NickCodec.class, new NickHandler());
        registerPacket(UserCodec.class, new UserHandler());
    }
    @Override
    public MessageCodec<?> readHeader(String[] buffer) {
        System.out.println("READING HEADER");
        System.out.println("BUFFER:  " + buffer[0]);
        return IRCServer.getInstance().getProtocol().getCodecLookupService().find(buffer[0]);
    }

    @Override
    public String writeHeader(MessageCodec<?> codec, String data) {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }
}
