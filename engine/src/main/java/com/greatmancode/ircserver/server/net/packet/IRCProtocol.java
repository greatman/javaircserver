/*
 * This file is part of ${name}.
 *
 * ${copyright} <http://www.greatmancode.com/>
 *
 * ${name} is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Lesser General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * ${name} is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public License
 * along with ${name}.  If not, see <http://www.gnu.org/licenses/>.
 */
package com.greatmancode.ircserver.server.net.packet;

import com.greatmancode.ircserver.api.net.Protocol;
import com.greatmancode.ircserver.api.net.interfaces.MessageCodec;
import com.greatmancode.ircserver.server.IRCServer;
import com.greatmancode.ircserver.server.net.packet.codec.NickCodec;
import com.greatmancode.ircserver.server.net.packet.codec.UserCodec;
import com.greatmancode.ircserver.server.net.packet.codec.responses.RPLCreatedCodec;
import com.greatmancode.ircserver.server.net.packet.codec.responses.RPLMyInfoCodec;
import com.greatmancode.ircserver.server.net.packet.codec.responses.RPLWelcomeCodec;
import com.greatmancode.ircserver.server.net.packet.codec.responses.RPLYourHostCodec;
import com.greatmancode.ircserver.server.net.packet.handler.NickHandler;
import com.greatmancode.ircserver.server.net.packet.handler.UserHandler;
import com.greatmancode.ircserver.server.net.packet.msg.NickMessage;

import java.util.HashMap;
import java.util.Map;

public class IRCProtocol extends Protocol {

    public IRCProtocol() {
        registerPacket(NickCodec.class, new NickHandler());
        registerPacket(UserCodec.class, new UserHandler());
        registerPacket(RPLCreatedCodec.class, null);
        registerPacket(RPLMyInfoCodec.class, null);
        registerPacket(RPLWelcomeCodec.class, null);
        registerPacket(RPLYourHostCodec.class, null);
    }
    @Override
    public MessageCodec<?> readHeader(String[] buffer) {
        System.out.println("READING HEADER");
        System.out.println("BUFFER:  " + buffer[0]);
        return IRCServer.getInstance().getProtocol().getCodecLookupService().find(buffer[0]);
    }

    @Override
    public String writeHeader(MessageCodec<?> codec, String data) {
        return ":" + IRCServer.serverName + " " + codec.getOpcode() + " " +data;
    }
}
