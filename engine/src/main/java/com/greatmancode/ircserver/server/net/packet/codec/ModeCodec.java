package com.greatmancode.ircserver.server.net.packet.codec;

import com.greatmancode.ircserver.api.net.interfaces.MessageCodec;
import com.greatmancode.ircserver.server.net.packet.msg.ModeMessage;

import java.util.Arrays;

public class ModeCodec extends MessageCodec<ModeMessage>{

    public ModeCodec() {
        super(ModeMessage.class, "MODE");
    }

    public ModeMessage decode(String[] data) {
        System.out.println("THE DATA:" + Arrays.toString(data));
        return new ModeMessage(data[0]);
    }
}
