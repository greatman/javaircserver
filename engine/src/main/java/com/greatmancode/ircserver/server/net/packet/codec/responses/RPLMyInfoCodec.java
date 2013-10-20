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
package com.greatmancode.ircserver.server.net.packet.codec.responses;

import com.greatmancode.ircserver.api.net.interfaces.MessageCodec;
import com.greatmancode.ircserver.server.net.packet.msg.responses.RPLMyInfoMessage;

public class RPLMyInfoCodec extends MessageCodec<RPLMyInfoMessage> {

    public RPLMyInfoCodec() {
        super(RPLMyInfoMessage.class,"004");
    }

    @Override
    public String encode(RPLMyInfoMessage message) {
        return message.getNickname() + " " + message.getServerName() + " " + message.getVersion() + " " + message.getAvailableUserMode() + " " + message.getAvailableChannelMode();
    }
}
