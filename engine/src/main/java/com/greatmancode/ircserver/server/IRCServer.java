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
package com.greatmancode.ircserver.server;

import com.greatmancode.ircserver.api.Server;
import com.greatmancode.ircserver.api.client.ClientManager;
import com.greatmancode.ircserver.api.net.Protocol;
import com.greatmancode.ircserver.api.parameters.ServerParameter;
import com.greatmancode.ircserver.server.net.IRCChannelInitializer;
import com.greatmancode.ircserver.server.net.IRCServerNetworkHandler;
import com.greatmancode.ircserver.server.net.packet.IRCProtocol;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.logging.LogLevel;
import io.netty.handler.logging.LoggingHandler;

import java.util.List;

public class IRCServer implements Server {

    public static final int PORT = 6667;
    public static final String serverName = "localhost.com";
    public static final String version = "0.0.1";
    private IRCProtocol protocol = new IRCProtocol();
    private ClientManager clientManager = new ClientManager();
    private static IRCServer instance;

    public IRCServer() {
        IRCServer.instance = this;
    }

    @Override
    public void onStart() {
        EventLoopGroup bossGroup = new NioEventLoopGroup();
        EventLoopGroup workerGroup = new NioEventLoopGroup();
        ServerBootstrap b = new ServerBootstrap();
            b.group(bossGroup, workerGroup)
            .channel(NioServerSocketChannel.class)
            .childHandler(new IRCChannelInitializer());
        try {
            b.bind(PORT).sync().channel().closeFuture().sync();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            bossGroup.shutdownGracefully();
            workerGroup.shutdownGracefully();
        }
    }

    @Override
    public void onStop() {

    }

    @Override
    public Protocol getProtocol() {
        return protocol;
    }

    public static IRCServer getInstance() {
        return instance;
    }

    @Override
    public ClientManager getClientManager() {
        return clientManager;
    }

    @Override
    public List<ServerParameter> getParameters() {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }
}
