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

import com.greatmancode.ircserver.api.client.Client;
import com.greatmancode.ircserver.api.net.interfaces.Message;
import com.greatmancode.ircserver.api.net.interfaces.MessageCodec;
import com.greatmancode.ircserver.api.net.interfaces.MessageHandler;
import com.greatmancode.ircserver.server.IRCServer;
import com.greatmancode.ircserver.server.client.IRCClient;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.util.AttributeKey;

@ChannelHandler.Sharable
public class IRCServerNetworkHandler extends SimpleChannelInboundHandler<Message> {

    public static final AttributeKey<Client> CLIENT = new AttributeKey<Client>("client");

    @Override
    public void channelActive(ChannelHandlerContext ctx) {
        ctx.attr(IRCServerNetworkHandler.CLIENT).set(IRCServer.getInstance().getClientManager().addClient(new IRCClient(ctx)));
    }
    @Override
    public void channelReadComplete(ChannelHandlerContext ctx) {
        ctx.flush();
    }

    @Override
    public void channelInactive(ChannelHandlerContext ctx) {
        ctx.attr(IRCServerNetworkHandler.CLIENT).get().disconnect("Disconnected.");
        //ctx.close();
    }
    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) {
        ctx.close();
    }

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, Message message) throws Exception {
        MessageHandler<Message> handler = (MessageHandler<Message>) IRCServer.getInstance().getProtocol().getHandlerLookupService().find(message.getClass());

        if (handler != null) {
            try {
                handler.handle(ctx.attr(IRCServerNetworkHandler.CLIENT).get(), message);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
