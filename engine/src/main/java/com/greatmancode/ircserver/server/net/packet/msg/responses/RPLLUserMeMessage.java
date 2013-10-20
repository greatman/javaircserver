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
package com.greatmancode.ircserver.server.net.packet.msg.responses;

public class RPLLUserMeMessage extends MessageUsername{

    private final int clientCount, serverCount;
    public RPLLUserMeMessage(String nickname, int clientCount, int serverCount) {
        super(nickname);
        this.clientCount = clientCount;
        this.serverCount = serverCount;
    }

    public int getClientCount() {
        return clientCount;
    }

    public int getServerCount() {
        return serverCount;
    }
}
