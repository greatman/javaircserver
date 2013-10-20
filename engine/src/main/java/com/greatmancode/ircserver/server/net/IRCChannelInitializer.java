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
package com.greatmancode.ircserver.server.net;

import com.greatmancode.ircserver.server.net.decoder.MessageDecoder;
import com.greatmancode.ircserver.server.net.decoder.MessageEncoder;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.LineBasedFrameDecoder;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.util.CharsetUtil;

public class IRCChannelInitializer extends ChannelInitializer<SocketChannel>{

    private static final MessageEncoder ENCODER = new MessageEncoder(CharsetUtil.UTF_8);
    private static final MessageDecoder MESSAGE_DECODER = new MessageDecoder(CharsetUtil.UTF_8);
    //private static final LineBasedFrameDecoder LINEDECODER = ;
    private static final IRCServerNetworkHandler HANDLER = new IRCServerNetworkHandler();
    @Override
    protected void initChannel(SocketChannel ch) throws Exception {
        ch.pipeline().addLast("framer", new LineBasedFrameDecoder(1000));
        ch.pipeline().addLast("decoder", MESSAGE_DECODER);
        ch.pipeline().addLast("handler", HANDLER);

        ch.pipeline().addLast("encoder", ENCODER);
    }
}
