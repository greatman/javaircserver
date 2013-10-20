/*
 * This file is part of IRCServer API.
 *
 * Copyright (c) 2013-2013, Greatmancode <http://www.greatmancode.com/>
 *
 * IRCServer API is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Lesser General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * IRCServer API is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public License
 * along with IRCServer API.  If not, see <http://www.gnu.org/licenses/>.
 */
package com.greatmancode.ircserver.api.net.interfaces;

import com.greatmancode.ircserver.api.client.Client;

public abstract class MessageHandler<T extends Message> {
    /**
     * Handles a message. If the message is a one way method, then this method can be overriden.
     *
     * Otherwise, it will call handleServer or handleClient as required.
     *
     * @param session the network session
     * @param message the message that was received
     */
    public abstract void handle(Client session, T message);
}
