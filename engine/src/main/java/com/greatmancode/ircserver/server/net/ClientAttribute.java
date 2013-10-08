package com.greatmancode.ircserver.server.net;

import com.greatmancode.ircserver.api.client.Client;
import io.netty.util.Attribute;
import io.netty.util.AttributeKey;

public class ClientAttribute implements Attribute<Client> {

    private Client client = null;
    @Override
    public AttributeKey<Client> key() {
        return new AttributeKey<>("client");
    }

    @Override
    public Client get() {
        return client;
    }

    @Override
    public void set(Client value) {
       client = value;
    }

    @Override
    public Client getAndSet(Client value) {
        Client oldValue = get();
        client = value;
        return oldValue;
    }

    @Override
    public Client setIfAbsent(Client value) {
        if (get() == null) {
            client = value;
        }
        return client;
    }

    @Override
    public Client getAndRemove() {
        Client value = client;
        remove();
        return value;
    }

    @Override
    public boolean compareAndSet(Client oldValue, Client newValue) {
        if (client.equals(oldValue)) {
            client = newValue;
            return true;
        }
        return false;
    }

    @Override
    public void remove() {
        client = null;
    }
}
