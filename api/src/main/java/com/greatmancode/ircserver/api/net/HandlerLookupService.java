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
package com.greatmancode.ircserver.api.net;

import com.greatmancode.ircserver.api.net.interfaces.Message;
import com.greatmancode.ircserver.api.net.interfaces.MessageHandler;

import java.util.HashMap;
import java.util.Map;

public class HandlerLookupService {

    private final Map<Class<? extends Message>, MessageHandler<?>> handlers = new HashMap<>();

    protected <T extends Message> void bind(Class<T> clazz, Class<? extends MessageHandler<T>> handlerClass) throws InstantiationException, IllegalAccessException {
        MessageHandler<T> handler = handlerClass.newInstance();
        handlers.put(clazz, handler);
    }

    protected <T extends Message> void bind(Class<T> clazz, MessageHandler<T> handler) {
        handlers.put(clazz, handler);
    }

    @SuppressWarnings ("unchecked")
    public <T extends Message> MessageHandler<T> find(Class<T> clazz) {
        return (MessageHandler<T>) handlers.get(clazz);
    }

    protected HandlerLookupService() {
    }
}
