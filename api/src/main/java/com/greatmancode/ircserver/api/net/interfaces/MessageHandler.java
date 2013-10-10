package com.greatmancode.ircserver.api.net.interfaces;

import com.greatmancode.ircserver.api.client.Client;

public abstract class MessageHandler<T extends Message> {
    /**
     * Handles a message. If the message is a one way method, then this method can be overriden.
     *
     * Otherwise, it will call handleServer or handleClient as required.
     *
     * @param session the network session
     * @param message the message that was received
     */
    public abstract void handle(Client session, T message);
}
