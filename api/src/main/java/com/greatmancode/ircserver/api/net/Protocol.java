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
import com.greatmancode.ircserver.api.net.interfaces.MessageCodec;
import com.greatmancode.ircserver.api.net.interfaces.MessageHandler;

import java.lang.reflect.InvocationTargetException;
import java.net.InetSocketAddress;
import java.util.Collection;
import java.util.Collections;

public abstract class Protocol {

    private final CodecLookupService codecLookupService = new CodecLookupService(getClass().getClassLoader());
    private final HandlerLookupService handlerLookupService = new HandlerLookupService();

    public CodecLookupService getCodecLookupService() {
        return codecLookupService;
    }

    public HandlerLookupService getHandlerLookupService() {
        return handlerLookupService;
    }
    public <T extends Message, C extends MessageCodec<T>> C registerPacket(Class<C> codecClazz, MessageHandler<T> handler) {
        try {
            C codec = getCodecLookupService().bind(codecClazz);
            if (handler != null) {
                getHandlerLookupService().bind(codec.getType(), handler);
            }
            return codec;
        } catch (InstantiationException  | IllegalAccessException | InvocationTargetException e) {
            e.printStackTrace();
            return null;
        }
    }
    /**
     * Read a packet header from the buffer. If a codec is not available and packet length is known, skip ahead in the buffer and return null. If packet length is not known, throw a {@link
     * org.spout.api.exception.UnknownPacketException}
     *
     * @param buf The buffer to read from
     * @return The correct codec
     * @throws UnknownPacketException when the opcode does not have an associated codec and the packet length is unknown
     */
    public abstract MessageCodec<?> readHeader(String[] buffer);

    /**
     * Writes a packet header to a new buffer.
     *
     * @param codec The codec the message was written with
     * @param data The data from the encoded message
     * @return The buffer with the packet header
     */
    public abstract String writeHeader(MessageCodec<?> codec, String data);
}
