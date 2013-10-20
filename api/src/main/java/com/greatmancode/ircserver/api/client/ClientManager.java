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
package com.greatmancode.ircserver.api.client;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ClientManager {

    public List<Client> clientList = new ArrayList<>();

    public Client addClient(Client client) {
        clientList.add(client);
        return client;
    }

    public void removeClient(Client client) {
        clientList.remove(client);

        //client.disconnect("Disconnected");
    }

    public Client getClient(String nickname) {
        for (Client client : clientList) {
            if (client.getNickname().equals(nickname)) {
                return client;
            }
        }
        return null;
    }
}
