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
package com.greatmancode.ircserver.api.client;

import com.greatmancode.ircserver.api.channel.Channel;
import io.netty.channel.ChannelHandlerContext;

import java.util.List;

public abstract class Client {

    public abstract ChannelHandlerContext getSocket();

    public abstract String getUsername();

    public abstract String setUsername(String username);

    public abstract String getHostname();

    public abstract String setHostname(String hostname);

    public abstract String getNickname();

    public abstract void setNickname(String nickname);

    public abstract List<Channel> getchannelList();

    public String getRepresentation() {
        return getNickname() + "!" + getUsername() + "@" + getHostname();
    }

    public void sendQuit(String quitMessage) {
        for (Channel channel : getchannelList()) {
            channel.sendMessage(this, "");
        }
    }
}
