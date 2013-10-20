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
