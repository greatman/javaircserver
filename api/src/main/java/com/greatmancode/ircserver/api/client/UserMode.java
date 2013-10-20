/*
 * This file is part of IRCServer API.
 *
 * Copyright (c) 2013-2013, Greatmancode <http://www.greatmancode.com/>
 *
 * IRCServer API is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Lesser General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * IRCServer API is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public License
 * along with IRCServer API.  If not, see <http://www.gnu.org/licenses/>.
 */
package com.greatmancode.ircserver.api.client;

import java.util.HashMap;
import java.util.Map;

public enum UserMode {

    AWAY("a"),
    INVISIBLE("i"),
    WALLOPS("w"),
    RESTRICTED("r"),
    OPERATOR("o"),
    LOCAL_OPERATOR("O"),
    SERVER_NOTICE("s");

    private static final Map<String, UserMode> modeList = new HashMap<String, UserMode>();
    private String value;

    UserMode(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }


    public static UserMode getValue(String letter) {
       return modeList.get(letter);
    }

    static {
        for (UserMode mode : UserMode.values()) {
            modeList.put(mode.getValue(), mode);
        }
    }

    public static String getConnectString() {
        String value = "";

        for (Map.Entry<String, UserMode> mode : modeList.entrySet()) {
            value += mode.getKey();
        }
        return value;
    }

}
