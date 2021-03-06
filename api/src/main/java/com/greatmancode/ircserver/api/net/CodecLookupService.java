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
package com.greatmancode.ircserver.api.net;

import com.greatmancode.ircserver.api.net.interfaces.Message;
import com.greatmancode.ircserver.api.net.interfaces.MessageCodec;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

public class CodecLookupService {

    private final ConcurrentMap<Class<? extends Message>, MessageCodec<?>> classTable = new ConcurrentHashMap<>();;
    private ConcurrentMap<String, MessageCodec<?>> codecList = new ConcurrentHashMap<String, MessageCodec<?>>();
    private ClassLoader loader;
    protected CodecLookupService(ClassLoader loader) {
        this.loader = loader;
    }
    protected <T extends Message, C extends MessageCodec<T>> C bind(Class<C> clazz) throws InstantiationException, IllegalAccessException, InvocationTargetException {
        Constructor<C> constructor;
        try {
            constructor = clazz.getConstructor();
        } catch (NoSuchMethodException e) {
            throw new IllegalStateException("No default constructor for class" + clazz.getName());
        }
        final C codec;
        codec = constructor.newInstance();
        final MessageCodec<?> prevCodec = find(codec.getOpcode());
        if (prevCodec != null) {
            throw new IllegalStateException("Trying to bind a static opcode where one already exists. Static: " + clazz.getSimpleName() + " Other: " + prevCodec.getClass().getSimpleName());
        }
        register(codec);
        return codec;
    }

    private <T extends Message, C extends MessageCodec<T>> void register(C codec) {
        codecList.put(codec.getOpcode(), codec);
        classTable.put(codec.getType(), codec);
    }

    /**
     * Retrieve the {@link MessageCodec} from the lookup table
     * @param opcode The opcode which the codec uses
     * @return The codec, null if not found
     */
    public MessageCodec<?> find(String opcode) {
        return codecList.get(opcode);
    }

    /**
     * Finds a codec by message class.
     *
     * @param clazz The message class.
     * @param <T> The type of message.
     * @return The codec, or {@code null} if it could not be found.
     */
    public <T extends Message> MessageCodec<T> find(Class<T> clazz) {
        return (MessageCodec<T>) classTable.get(clazz);
    }
}
