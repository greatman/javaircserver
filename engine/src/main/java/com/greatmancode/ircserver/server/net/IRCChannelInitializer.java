package com.greatmancode.ircserver.server.net;

import com.greatmancode.ircserver.api.client.Client;
import com.greatmancode.ircserver.server.client.IRCClient;
import io.netty.channel.Channel;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.LineBasedFrameDecoder;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.handler.codec.string.StringEncoder;
import io.netty.util.AttributeKey;
import io.netty.util.CharsetUtil;

public class IRCChannelInitializer extends ChannelInitializer<SocketChannel>{

    private static final StringDecoder DECODER = new StringDecoder(CharsetUtil.UTF_8);
    private static final StringEncoder ENCODER = new StringEncoder(CharsetUtil.UTF_8);
    private static final LineBasedFrameDecoder LINEDECODER = new LineBasedFrameDecoder(1000);
    private static final IRCServerNetworkHandler HANDLER = new IRCServerNetworkHandler();
    @Override
    protected void initChannel(SocketChannel ch) throws Exception {
        ch.pipeline().addLast("framer", LINEDECODER);
        ch.pipeline().addLast("decoder", DECODER);
        ch.pipeline().addLast("encoder", ENCODER);
        ch.pipeline().addLast("handler", HANDLER);
    }
}
