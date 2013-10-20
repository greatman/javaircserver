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
