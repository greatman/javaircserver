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
package com.greatmancode.ircserver.server.net.packet.handler;

import com.greatmancode.ircserver.api.client.Client;
import com.greatmancode.ircserver.api.net.interfaces.MessageHandler;
import com.greatmancode.ircserver.server.IRCServer;
import com.greatmancode.ircserver.server.net.packet.msg.NickMessage;
import com.greatmancode.ircserver.server.net.packet.msg.responses.RPLNicknameInUseMessage;

public class NickHandler extends MessageHandler<NickMessage>{
    @Override
    public void handle(Client session, NickMessage message) {
        System.out.println("HANDLING IT");
        if (IRCServer.getInstance().getClientManager().getClient(message.getNickname()) != null) {
            //Does the client have a current nick?
            if (session.getNickname() != null) {
                session.sendPacket(new RPLNicknameInUseMessage(session.getNickname(), message.getNickname()));
            } else {
                session.sendPacket(new RPLNicknameInUseMessage(message.getNickname(), message.getNickname()));
            }
            return;
        }
        session.setNickname(message.getNickname());
    }
}
