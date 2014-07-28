package com.greatmancode.ircserver.server.net.packet.codec.responses;

import com.greatmancode.ircserver.api.net.interfaces.MessageCodec;
import com.greatmancode.ircserver.server.net.packet.msg.responses.RPLChannelModeIsMessage;

import java.io.IOException;

/**
 * Created by greatman on 14-07-27.
 */
public class RPLChannelModeIsCodec extends MessageCodec<RPLChannelModeIsMessage> {

    public RPLChannelModeIsCodec() {
        super(RPLChannelModeIsMessage.class, "324");
    }

    public String encode(RPLChannelModeIsMessage message) throws IOException {
        String result = message.getNickname() + " " + message.getChannelName();
        result += (message.getModes().equals("")) ? "" : " +" + message.getModes();
        return result;
    }
}
