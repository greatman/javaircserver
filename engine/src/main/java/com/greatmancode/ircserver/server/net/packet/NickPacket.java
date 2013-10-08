package com.greatmancode.ircserver.server.net.packet;

import com.greatmancode.ircserver.api.client.Client;
import com.greatmancode.ircserver.api.net.Packet;

public class NickPacket implements Packet {
    @Override
    public void handlePacket(Client sender, String[] args) {
        sender.set
    }

    @Override
    public void sendPacket(Client receiver, String[] args) {

    }

    @Override
    public int minArgs() {
        return 1;
    }

    @Override
    public int maxArgs() {
        return 1;
    }
}
