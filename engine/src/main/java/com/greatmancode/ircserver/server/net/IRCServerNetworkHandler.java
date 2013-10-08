package com.greatmancode.ircserver.server.net;

import com.greatmancode.ircserver.api.client.Client;
import com.greatmancode.ircserver.server.client.IRCClient;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.util.AttributeKey;

public class IRCServerNetworkHandler extends SimpleChannelInboundHandler<String> {

    public static final AttributeKey<Client> CLIENT = new AttributeKey<Client>("client");

    @Override
    public void channelActive(ChannelHandlerContext ctx) {
        ctx.attr(IRCServerNetworkHandler.CLIENT).set(new IRCClient(ctx));
    }
    @Override
    public void channelReadComplete(ChannelHandlerContext ctx) {
        ctx.flush();
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) {
        ctx.close();
    }

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, String msg) throws Exception {
        System.out.println(msg);
    }
}
