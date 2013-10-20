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
package com.greatmancode.ircserver.server.net.decoder;

import com.greatmancode.ircserver.api.net.interfaces.MessageCodec;
import com.greatmancode.ircserver.server.IRCServer;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToMessageDecoder;

import java.nio.charset.Charset;
import java.util.List;

@ChannelHandler.Sharable
public class MessageDecoder extends MessageToMessageDecoder<ByteBuf> {

    // TODO Use CharsetDecoder instead.
    private final Charset charset;

    /**
     * Creates a new instance with the current system character set.
     */
    public MessageDecoder() {
        this(Charset.defaultCharset());
    }

    /**
     * Creates a new instance with the specified character set.
     */
    public MessageDecoder(Charset charset) {
        if (charset == null) {
            throw new NullPointerException("charset");
        }
        this.charset = charset;
    }

    @Override
    protected void decode(ChannelHandlerContext ctx, ByteBuf msg, List<Object> out) throws Exception {
        String value = msg.toString(charset);
        System.out.println("VALUES:" + value);
        String[] splittedValue = value.split(" ");
        MessageCodec<?> codec = IRCServer.getInstance().getProtocol().readHeader(splittedValue);
        Object decoded;
        System.out.println("THE CODEC:" + codec);
        String[] values = new String[splittedValue.length - 1];
        System.arraycopy(splittedValue, 1, values, 0 , values.length);
        if (codec != null) {
            Object obj = codec.decode(values);
            out.add(obj);
        }

    }
}
