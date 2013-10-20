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
package com.greatmancode.ircserver.server.net.packet.handler;

import com.greatmancode.ircserver.api.client.Client;
import com.greatmancode.ircserver.api.net.interfaces.MessageHandler;
import com.greatmancode.ircserver.server.net.packet.msg.UserMessage;
import io.netty.handler.codec.string.StringEncoder;

import java.net.InetSocketAddress;

public class UserHandler extends MessageHandler<UserMessage> {
    @Override
    public void handle(Client session, UserMessage message) {
        session.setUsername(message.getUsername());
        session.setRealName(message.getRealName());
        System.out.println(session.getSocket().channel().remoteAddress());
        session.setHostname(((InetSocketAddress)session.getSocket().channel().remoteAddress()).getHostName());
    }
}
