/*
 * This file is part of IRCServer Engine.
 *
 * Copyright (c) 2013-2013, Greatmancode <http://www.greatmancode.com/>
 *
 * IRCServer Engine is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Lesser General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * IRCServer Engine is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public License
 * along with IRCServer Engine.  If not, see <http://www.gnu.org/licenses/>.
 */
package com.greatmancode.ircserver.server.net.packet;

import com.greatmancode.ircserver.api.Representable;
import com.greatmancode.ircserver.api.net.Protocol;
import com.greatmancode.ircserver.api.net.interfaces.MessageCodec;
import com.greatmancode.ircserver.server.IRCServer;
import com.greatmancode.ircserver.server.net.packet.codec.*;
import com.greatmancode.ircserver.server.net.packet.codec.responses.*;
import com.greatmancode.ircserver.server.net.packet.handler.*;
import com.greatmancode.ircserver.server.net.packet.msg.NickMessage;

import java.util.HashMap;
import java.util.Map;

public class IRCProtocol extends Protocol {

    public IRCProtocol() {
        registerPacket(NickCodec.class, new NickHandler());
        registerPacket(UserCodec.class, new UserHandler());
        registerPacket(JoinCodec.class, new JoinHandler());
        registerPacket(PrivmsgCodec.class, new PrivmsgHandler());
        registerPacket(PingCodec.class, new PingHandler());
        registerPacket(PongCodec.class, null);
        registerPacket(WhoCodec.class, new WhoHandler());
        registerPacket(ModeCodec.class, new ModeHandler());
        //001
        registerPacket(RPLWelcomeCodec.class, null);
        //002
        registerPacket(RPLYourHostCodec.class, null);
        //003
        registerPacket(RPLCreatedCodec.class, null);
        //004
        registerPacket(RPLMyInfoCodec.class, null);
        //251
        registerPacket(RPLLUserClientCodec.class, null);
        //252
        registerPacket(RPLLUserOPCodec.class, null);
        //254
        registerPacket(RPLLUserChannelsCodec.class, null);
        //255
        registerPacket(RPLLUserMeCodec.class, null);
        //315
        registerPacket(RPLEndOfWhoCodec.class, null);
        //352
        registerPacket(RPLWhoReplyCodec.class, null);
        //353
        registerPacket(RPLNameReplyCodec.class, null);
        //366
        registerPacket(RPLEndOfNamesCodec.class, null);
        //368
        registerPacket(RPLEndOfBanListCodec.class, null);
        //324
        registerPacket(RPLChannelModeIsCodec.class, null);
        //433
        registerPacket(RPLNicknameInUseCodec.class, null);
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

    @Override
    public String writeHeader(Representable client, MessageCodec<?> codec, String data) {
        return ":" + client.getRepresentation() + " " + codec.getOpcode() + " " + data;
    }
}
