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

import com.greatmancode.ircserver.api.net.interfaces.Message;

public class RPLMyInfoMessage extends MessageUsername {

    private final String serverName, version, availableUserMode, availableChannelMode;
    public RPLMyInfoMessage(String nickname, String serverName, String version, String availableUserMode, String availableChannelMode) {
        super(nickname);
        this.serverName = serverName;
        this.version = version;
        this.availableUserMode = availableUserMode;
        this.availableChannelMode = availableChannelMode;
    }

    public String getServerName() {
        return serverName;
    }

    public String getAvailableChannelMode() {
        return availableChannelMode;
    }

    public String getAvailableUserMode() {
        return availableUserMode;
    }

    public String getVersion() {
        return version;
    }
}
