package com.greatmancode.ircserver.server.net.packet.codec;

import com.greatmancode.ircserver.api.net.interfaces.MessageCodec;
import com.greatmancode.ircserver.server.net.packet.msg.ModeMessage;

import java.io.IOException;
import java.util.Arrays;

public class ModeCodec extends MessageCodec<ModeMessage>{

    public ModeCodec() {
        super(ModeMessage.class, "MODE");
    }

    public ModeMessage decode(String[] data) {
        System.out.println("THE DATA:" + Arrays.toString(data));
        if (data.length == 1) {
            return new ModeMessage(data[0]);
        } else if (data.length == 2) {
            //Channel or user update
            return new ModeMessage(data[0], data[1]);
        } else {
            return new ModeMessage(data[0], data[1], Arrays.copyOfRange(data,2,data.length));
        }
    }

    public String encode(ModeMessage message) throws IOException {
       return message.getChannel() + " " + message.getAction() + " " + message.getParameter();

    }
}
