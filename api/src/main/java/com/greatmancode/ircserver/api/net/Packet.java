package com.greatmancode.ircserver.api.net;

import com.greatmancode.ircserver.api.client.Client;

public interface Packet {

    public void handlePacket(Client sender, String[] args);

    public void sendPacket(Client receiver, String[] args);

    public int minArgs();
    public int maxArgs();
}
