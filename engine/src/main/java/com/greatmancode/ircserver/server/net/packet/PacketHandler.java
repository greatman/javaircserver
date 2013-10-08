package com.greatmancode.ircserver.server.net.packet;

import com.greatmancode.ircserver.api.net.Packet;

import java.util.HashMap;
import java.util.Map;

public class PacketHandler {

    private static Map<String, Packet> packetList = new HashMap<String, Packet>();


    public static void addPacket(String command, Packet packet) {
        packetList.put(command, packet);
    }

    public static Packet getPacket(String command) {

    }
}
